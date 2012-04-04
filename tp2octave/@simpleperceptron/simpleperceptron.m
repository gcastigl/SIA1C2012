function sp = simpleperceptron(in_len, out_len)
	sp.neurons = Array(out_len);
	for i = 1:in_len;
		sp.neurons(1) = neuron(in_len)
	end
	sp = class(sp, "simpleperceptron");
endfunction