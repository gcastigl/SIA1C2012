function net = main(file_name, hidden_layers, epochs, trans_name, lrn_base, lrn_type_name, interactive, noise, symmetry)

	% Invoque main like main(filePath, 500, [2 4], "SIGMOID", 0.02, "CONSTANT", 1, 0)
	% This network will try to learn the file_path function, with an architecture of 2 hidden
	% layers with 2 and 4 neurons in each layer respectively.
	% Algorithm will run 500 epochs using the SIGMOID transfer function with a 0.02 etha and
	% a constant learning rate. Also it will be interactive and won't use noise.
	%
	% The interactive argument flag tells the algorihm if we want to plot the network learning in 
	% real time (interactive = 1) or if we want to only plot the final output (interactive = 0).
	% The noise argument flag is set to 1 if we want to use noise and is set to 0 if we dont.
	

	if (strcmp(tolower(file_name), 'help'))
		printGreenColor();
		printf('\n***Invoque main like main(filePath, HIDDEN_LAYERS, epochs, TRANSFORMATION, eta, LEARN_TYPE, interactive, noise, symmetry)***\n')
		printf('\nHIDDEN_LAYERS = [2 4] for two layers with 2 and 4 neurons in each layer respectively\n');
		printf('\nTRANSFORMATION = [Sigmoid, sigmoid_exp]\n');
		printf('\nLEARN_TYPE = [Constant, Annealed, Dynamic]\n');
		printf('\ninteractive = [1, 0]\n');, , 
		printf('\nnoise = [1, 0]\n');
		printf('\nsymmetry = [1, 0]\n');
		releasePrintColor();
		return;
	endif
	
	points = load(file_name);
	
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
		%normalize net input to [-1:1] interval
		points(:,1)./abs(max(points(:,1)));
		points(:,2)./abs(max(points(:,2)));
	elseif (strcmp(tolower(trans_name), 'sigmoid_exp'))
		printGreenColor();
		printf('\nUsing SIGMOID EXPONENTIAL transfer function\n');
		releasePrintColor();
		trans = 4;
		%normalize net input to [0:1] interval
		points(:,1)./abs(max(points(:,1)));
		points(:,1).+1;
		points(:,1)./2;
		points(:,2)./abs(max(points(:,2)));
		points(:,2).+1;
		points(:,2)./2;
	else
		printRedColor();
		printf('\nError: Unrecognized transfer function.\n\tUsing default transfer function = tanh()\n');
		releasePrintColor();
		trans = 3;
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
	
	train_percentage = 0.4; % Percentage of the number of sampoles that will be used for training 
	testAndTrainSets = getRandomSamples(points, train_percentage);
	train_set = testAndTrainSets{1};
	train_set_len = length(train_set);
	vals = train_set;
	
	tests = testAndTrainSets{2};
	test_set_len = length(tests);
	
	% Create the network with i inputs and o outputs
	i = length(train_set{1}{1});
	o = length(train_set{1}{2});
	net = crt_nr_nw([i,hidden_layers,o],lrn_type,lrn_base,trans, symmetry);

	errors = zeros(epochs,1);
	test_errors = zeros(epochs,1);
	lrn_rates = zeros(epochs,1);

	% Backpropagation over the network
	printBlueColor();
	printf('\nBackpropagation algorithm started: epoch 1 of %d, learning \"%s\" with \"%s\" transfer function\n', epochs, file_name, lrn_type_name);
	releasePrintColor();
	clf;
	framesPerEpochs = 1;
	skippedFrames = 0;
	first = 1;
	batch_size = train_set_len/4; %size of batch
	noise_factor = 0.1;
	for i = 1:epochs % Iterate over epochs
		vec = get_randorder(train_set_len); % Shuffle trainset
		err = 0;
		for j = 1:train_set_len % Iterate over each training pattern
			%Evaluate the input
			values = eval_input(net, vals{vec(j)}{1});
			values_vector{mod(j - 1,batch_size) + 1} = values;
			%Get the delta values of  the network
			deltas  = get_deltas(net, values, vals{vec(j)}{2});
			delta_vector{mod(j - 1,batch_size) + 1} = deltas;
			
			%if batch_size has been reached...
			if( mod(j - 1, batch_size) == (batch_size - 1)  || j == train_set_len)
				if( first == 1)
					dws = get_weight_changes(net, delta_vector, values_vector);
					first = 0;
				else
					dws = get_weight_changes( net, delta_vector, values_vector,  dws);
				endif
				clear delta_vector;
				clear values_vector;
				net = update_weights(net, dws);
				if( noise == 1 && rand() < noise_factor)
					net = add_noise(net);
				endif
			endif
			%Calculate error 
			err += get_error(values ,vals{vec(j)}{2}, net.trans_type);
			% store calculated z for the net
			zeta(j) =  values{size(net.arch,2)};
		end
		errors(i) = err / train_set_len;
		terror = 0;
		for  j = 1:test_set_len
			values = eval_input(net, tests{j}{1});
			terror += get_error(values, tests{j}{2}, net.trans_type);
		end
		test_errors(i) = terror / test_set_len;
		net = update_lrn_rate(net, errors(i), 0.001);
		lrn_rates(i) = net.lrn_rt;
		if(interactive  == 1)
			if (skippedFrames == framesPerEpochs)
			skippedFrames = 0;
			figure(1);
			plotSamples3D(vec, zeta, points, vals);
			figure(2);
			plotStats2D(epochs, test_errors, errors, lrn_rates, i);
			else
			skippedFrames++;
			end
		endif
		
	end

	final_error = test_errors(epochs);
	train_errs = zeros(1,train_set_len);
	test_errs = zeros(1,test_set_len);
	img_size = size(vals{1}{2},2);
	m = size(net.arch, 2);
	for i = 1:train_set_len
		v = eval_input(net, vals{i}{1});
		aux = 0;
		for k = 1:img_size
			aux += abs( vals{i}{2} - v{m}(k)); 
		end
		aux /= img_size;
		train_errs(i) = aux;
	end
	for i = 1:test_set_len
		v = eval_input(net, tests{i}{1});
		aux = 0;
		for k = 1:img_size
			aux += abs( tests{i}{2} - v{m}(k)); 
		end
		aux /= img_size;
		test_errs(i) = aux;
	end
	printGreenColor();
	printf('\nNetwork stats:\n\n');
	printYellowColor();
	printf('\tElapsed epochs: %d\n', epochs);
	printf('\t Mean absolute value error for train set: %f\n', mean(train_errs));
	printf('\t Standard deviation for AVE in train set: %f\n', std(train_errs));
	printf('\t Mean absolute value error for test set: %f\n', mean(test_errs));
	printf('\t Standard deviation for AVE in test set: %f\n', std(test_errs));
	printf('\t Precision for 0.05 error: Train: %f, Generalization: %f \n', get_precision(train_errs, train_set_len, 0.05) , get_precision(test_errs, test_set_len, 0.05));
	printf('\t Precision for 0.01 error: Train: %f, Generalization: %f \n', get_precision(train_errs, train_set_len, 0.01) , get_precision(test_errs, test_set_len, 0.01));
	%plotSamples3D(vec, zeta, points, vals);
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
	releasePrintColor();
	plotStats2D(epochs, test_errors, errors,lrn_rates, epochs);
endfunction
