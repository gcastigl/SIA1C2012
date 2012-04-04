function  adjust_weight_linear( neuron, lrn_rt, delta, deriv)
	for i = 1 : size( neuron.weights)
		neuron.weights(i) = neuron.weights(i) * delta * deriv * lrn_rt;
	end
	neuron.bias = delta * lrn_rt * deriv * -1;
endfunction
