function net = crt_nr_nw( arch, lrn_type, base_lrn_rate, trans_type)
	%create a new simple perceptron.
	% Requieres: input length, output length, learn type (static, annealed or dynamic),
	% and cost_type (threshold, linear or sigmoid)
	

	%initialize weights
	net.weights = init_weights(arch);

	%store net architecture. redundant info, but may be useful
	net.arch = arch;



	%initialize values
	% values{1} ENTRADAS
	% values[2..n-1} SALIDAS CAPAS OCULTAS
	% values{n} SALIDAS
	layers = size(arch,2);
	net.values = cell(layers,1);
	for i = 1:layers-1
		net.values{i} = zeros(1,arch(i) + 1); %bias
		net.values{i}(arch(i)+1) = -1;
	end	
	net.values{layers} = zeros(1,arch(layers)); %output layer, no bias


	%initialize other useful variables
	net.lrn_type = lrn_type;
	net.trans_type = trans_type;
	net.lrn_rt = base_lrn_rate;
	net.lrn_decay = 0.85;
	net.lrn_consist = 3;
	net.lrn_sum = 0.005;
	net.counter = 0;
	net.prev_err = 99999999;
	net.beta = 1;
endfunction
