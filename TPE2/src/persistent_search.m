function net = main(operator_name, N, hidden_layers, epochs, trans_name, lrn_base, lrn_type_name, error)

	% Invoque main like main("AND", N, 500, [2 4], "SIGMOID", 0.02, "CONSTANT", 0.001)
	% This network will try to learn the AND operator with N bits, with an architecture of 2 hidden
	% layers with 2 and 4 neurons in each layer respectively.
	% Algorithm will run 500 epochs using the SIGMOID transfer function with a 0.02 etha and
	% a constant learning rate.
	% The last parameter specifies the desired error: algorithm will restart until the desired error is adquired

	if (strcmp(tolower(operator_name), 'help'))
		printGreenColor();
		printf('\n***Invoque main like main("AND", N, HIDDEN_LAYERS, epochs, TRANSFORMATION, eta, LEARN_TYPE, error)***\n')
		printf('\nHIDDEN_LAYERS = [2 4] for two layers with 2 and 4 neurons in each layer respectively\n');
		printf('\nTRANSFORMATION = [Sg, Linear, Sigmoid]\n');
		printf('\nLEARN_TYPE = [Constant, Annealed, Dynamic]\n');
		releasePrintColor();
		return;
	endif
	
	train_set = feval(strcat(tolower(operator_name), '_', num2str(N)));

	train_set_len = length(train_set);
	vals = train_set;

	test_set_len = train_set_len;
	tests = train_set;
	
	if(strcmp(tolower(trans_name), 'sg'))
		printGreenColor();
		printf('\nUsing THRESHOLD transfer function\n');
		releasePrintColor();
		trans = 1;
	elseif (strcmp(tolower(trans_name), 'linear'))
		printGreenColor();
		printf('\nUsing LINEAR transfer function\n');
		releasePrintColor();
		trans = 2;
	elseif (strcmp(tolower(trans_name), 'sigmoid'))
		printGreenColor();
		printf('\nUsing SIGMOID transfer function\n');
		releasePrintColor();
		trans = 3;
	else
		printRedColor();
		printf('\nError: Unrecognized transfer function.\n\tUsing default transfer function = THRESHOLD\n');
		releasePrintColor();
		trans = 1;
	endif

	if(strcmp(tolower(lrn_type_name), 'constant'))
		printGreenColor();
		printf('\nUsing CONSTANT learning type\n');
		releasePrintColor();		
		lrn_type = 1;
	elseif (strcmp(tolower(lrn_type_name), 'annealed'))
		printGreenColor();
		printf('\nUsing ANNEALED learning type\n');
		releasePrintColor();
		lrn_type = 2;
	elseif (strcmp(tolower(lrn_type_name), 'dynamic'))
		printGreenColor();
		printf('\nUsing DYNAMIC learning type\n');
		releasePrintColor();
		lrn_type = 3;
	else
		printRedColor();
		printf('\nError: Unrecognized learning type.\n\tUsing default learning type = CONSTANT\n');
		releasePrintColor();
		lrn_type = 1;
	endif

	% Create the network with i inputs and o outputs
	inp = length(train_set{1}{1});
	out = length(train_set{1}{2});
	curr_error = Inf;
	times = 0;
	while( curr_error > error)
		net = crt_nr_nw([inp,hidden_layers,out],lrn_type,lrn_base,trans);
		errors = zeros(epochs,1);
		for i = 1:epochs
			vec = get_randorder(train_set_len); % Shuffle trainset
			err = 0;
			for j = 1:train_set_len
				
				%Ev	aluate the input
				net = eval_input(net, vals{vec(j)}{1});

				%get the delta values of  the network
				deltas  = get_deltas(net, vals{vec(j)}{2});
				
				%update weights with the delta values
				net = update_weights(net, deltas);

				%Calculate error 
				err += get_error(net, vals{vec(j)}{2});
			end
			net = update_lrn_rate(net, err / train_set_len);
			curr_error = 0;
			for  j = 1:test_set_len
				net = eval_input(net, tests{j}{1});
				curr_error += get_error(net, tests{j}{2});
			end
			if(curr_error <= error)
				break;
			endif
		end
		times +=1;
	end
		printGreenColor();
		printf('\nNetwork stats:\n\n');
		printYellowColor();
		printf('\tElapsed epochs: %d\n', i);
		printf('\tTotal tries: %d\n', times);
		printf('\tTotal error: %f\n', curr_error);
		printf('\tFinal outputs:');
		for i = 1:size(train_set,1)
			printf('\t[');
			for j = 1:size(train_set{i}{1},2)
				printf('%d,',train_set{i}{1}(j));
			end
			net = eval_input(net,train_set{i}{1});
			printf(']\tResult:%g Excpected: %g\n', net.values{size(net.values,1)}(1), train_set{i}{2}(1));
		end
		releasePrintColor();
endfunction
