function test_net(net_file, samples_file)

	% Invoque test_net like test_net('best_net.mat', 'samples.txt')
	% Where 'best_net.mat' is a file containing all the architecture of a previously saved network
	% and 'samples.txt' is  a file with points (or samples) of the function that the network will
	% try to generalize.

	points = load(samples_file);

	% NORMALIZE POINTS ????????
	
	test_set = getSamples(points); % We will use ALL the new points for testing the generalization power of the net

	load(net_file); % Load all the data of the saved network

	printGreenColor();
	printf("Using the following architecture (including input and ouput layers):\n\n");
	printRedColor();
	net.arch
	releasePrintColor();



endfunction