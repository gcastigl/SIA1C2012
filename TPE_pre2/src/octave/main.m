function net = main(operator_name, N, hidden_layers, epochs, trans_name, lrn_base, lrn_type_name)

	% Invoque main like main("AND", N, 500, [2 4], "SIGMOID", 0.02, "CONSTANT")
	% This network will try to learn the AND operator with N bits, with an architecture of 2 hidden
	% layers with 2 and 4 neurons in each layer respectively.
	% Algorithm will run 500 epochs using the SIGMOID transfer function with a 0.02 etha and
	% a constant learning rate.

	if (strcmp(tolower(operator_name), 'help'))
		printGreenColor();
		printf('\n***Invoque main like main("AND", N, HIDDEN_LAYERS, epochs, TRANSFORMATION, eta, LEARN_TYPE)***\n')
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
	i = length(train_set{1}{1});
	o = length(train_set{1}{2});
	net = crt_nr_nw([i,hidden_layers,o],lrn_type,lrn_base,trans);

	errors = zeros(epochs,1);
	test_errors = zeros(epochs,1);
	lrn_rates = zeros(epochs,1);

	% Backpropagation over the network
	printBlueColor();
	printf('\nBackpropagation algorithm started: epoch 1 of %d, learning \"%s\" with \"%s\" transfer function\n', epochs, operator_name, lrn_type_name);
	releasePrintColor();
	
	for i = 1:epochs % Iterate over epochs
		vec = get_randorder(train_set_len); % Shuffle trainset
		err = 0;
		for j = 1:train_set_len % Iterate over each training pattern
			
			%Evaluate the input
			net = eval_input(net, vals{vec(j)}{1});

			%Get the delta values of the network
			deltas  = get_deltas(net, vals{vec(j)}{2});
			
			%Update weights with the delta values
			net = update_weights(net, deltas);

			%Calculate error 
			err += get_error(net, vals{vec(j)}{2});
		end
		errors(i) = err / train_set_len;
		terror = 0;

		for  j = 1:test_set_len
			net = eval_input(net, tests{j}{1});
			terror += get_error(net, tests{j}{2});
		end

		test_errors(i) = terror;
		net = update_lrn_rate(net, errors(i));
		lrn_rates(i) = net.lrn_rt;
	end

	final_error = test_errors(epochs);

	printGreenColor();
	printf('\nNetwork stats:\n\n');
	printYellowColor();
	printf('\tElapsed epochs: %d\n', epochs);
	printf('\tTotal error: %f\n', final_error);
		if(final_error < (10**(-5)) ) 
				printGreenColorNB();
				printf('\tError is smaller than 10^{-5}\n');
		else
			if(test_errors(epochs) < (10**(-3)))
				printGreenColorNB();
				printf('\tError is smaller than 10^{-3}\n');
			else
				printRedColorNB();
				printf('\tError is grater than 10^{-3} - try using a different \"eta\" or incrementing the number of epochs\n');
			endif
		endif
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
	
	% Plot the network stats
	clf;
	hold on;
	% plot(1:epochs, errors, '-4; Step Error;'); % No sense to plot this error if we have the same training and eval set
	plot(1:epochs, test_errors, '-1; Error total;')
	plot(1:epochs, lrn_rates, '*3; Tasa de aprendizaje;');
	title("Evolución de la Red Neuronal", 'FontSize', 25);
	xlabel("Número de épocas", 'FontSize', 20);
	%ylabel("Error", 'FontSize', 20);
	mkdir("./images/");
	print('-dpng', './images/evolution.png');
endfunction
