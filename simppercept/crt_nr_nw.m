function net = crt_nr_nw( in_len, out_len, lrn_type, trans_type)
	%create a new simple perceptron.
	% Requieres: input length, output length, learn type (static, annealed or dynamic),
	% and cost_type (threshold, linear or sigmoid)
	net.weights = init_weights(in_len + 1, out_len);
	net.values = { zeros(1, in_len + 1) , zeros(1, out_len) };
	net.values{1}(in_len+1) = -1;
	net.lrn_type = lrn_type;
	net.trans_type = trans_type;
	net.lrn_rt = 0.02;
	net.lrn_decay = 0.85;
	net.lrn_consist = 3;
	net.lrn_sum = 0.005;
	net.beta = 0.5;
endfunction