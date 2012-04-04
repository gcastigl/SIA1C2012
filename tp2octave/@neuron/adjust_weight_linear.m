function  adjust_weight_linear( neuron, lrn_rt, delta)
	for i = 1 : size( neuron.weights)
		neuron.weights(i) = neuron.weights(i) * delta * lrn_rt;
	end
	neuron.bias = delta * lrn_rt * -1;
endfunction
