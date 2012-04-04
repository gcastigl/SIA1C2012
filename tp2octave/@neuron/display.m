function display(neuron)
	%Displays the neuron object.
	fprintf("%s =", inputname(1));
	fprintf("\nweights = {");
	for f = neuron.weights
		fprintf("%g,", f);
	end
	fprintf("}\nbias= %g\n", neuron.bias);
endfunction