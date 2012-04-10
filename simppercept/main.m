function net = main(operator_name, N, epochs, trans_name, lrn_base, lrn_type_name)

	% Invoque main like main("AND", N, 500, "SIGMOID", 0.02, "COSNTANT")
	% This network will try to learn the AND operator with N bits in
	% 500 epochs using the SIGMOID transfer function with a 0.02 etha and
	% a constant learning rate.

	train_set = feval(strcat(tolower(operator_name), '_', num2str(N)));

	train_set_len = length(train_set);
	vals = train_set;

	test_set_len = train_set_len;
	tests = train_set;

	if(strcmp(tolower(trans_name), 'sg'))
		trans = 1;
	elseif (strcmp(tolower(trans_name), 'lineal'))
		trans = 2;
	elseif (strcmp(tolower(trans_name), 'sigmoid'))
		trans = 3;
	else
		printf('Using default transfer function = threshold\n');
		trans = 1;
	endif

	if(strcmp(tolower(lrn_type_name), 'constant'))
		lrn_type = 1;
	elseif (strcmp(tolower(lrn_type_name), 'annealed'))
		lrn_type = 2;
	elseif (strcmp(tolower(lrn_type_name), 'dynamic'))
		lrn_type = 3;
	else
		printf('Using default learning type = constant\n');
		lrn_type = 1;
	endif

	% Create the network with i inputs and o outputs
	i = length(train_set{1}{1});
	o = length(train_set{1}{2});
	net = crt_nr_nw(i,o,lrn_type,lrn_base,trans);


	errors = zeros(epochs,1);
	test_errors = zeros(epochs,1);
	lrn_rates = zeros(epochs,1);

	% Backpropagation over the network
	for i = 1:epochs
		vec = get_randorder(train_set_len); % Shuffle trainset
		err = 0;
		for j = 1:train_set_len
			
			%Evaluate the input
			net = eval_input(net, vals{vec(j)}{1});

			%Compare output with desired value
			net = update_weights(net, vals{vec(j)}{2});

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

	plot(1:epochs, errors, 1:epochs, test_errors, 1:epochs, lrn_rates);
endfunction