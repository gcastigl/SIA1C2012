function ret = get_val ( neuron, vals, fn)
	%evaluates the neuron.
	%requires the neuron, inputs and activation function as string.
	ret = dot(vals, neuron.weights) - neuron.bias;
	ret = feval(fn, ret);
endfunction