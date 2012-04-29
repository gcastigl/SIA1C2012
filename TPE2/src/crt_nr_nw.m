function net = crt_nr_nw( arch, lrn_type, base_lrn_rate, trans_type)
	%create a new simple perceptron.
	% Requieres: input length, output length, learn type (static, annealed or dynamic),
	% and cost_type (threshold, linear or sigmoid)
	

	%initialize weights
	net.weights = init_weights(arch);

	%store net architecture. redundant info, but may be useful
	net.arch = arch;





	%initialize other useful variables
	net.lrn_type = lrn_type;
	net.trans_type = trans_type;
	net.lrn_rt = base_lrn_rate;
	net.lrn_decay = 0.55;
	net.lrn_consist = 3;
	net.lrn_sum = 0.005;
	net.counter = 0;
	net.prev_err = 99999999;
	net.beta = 0.7;
	net.mom_coef = 0.9;
endfunction
