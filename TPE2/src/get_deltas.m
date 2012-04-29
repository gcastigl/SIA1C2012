function deltas = get_deltas(net, values, expected)
	layers = size(net.arch,2); % Number of layers (including INPUT and OUTPUT)

	%first calculate delta for output layer.
	for i= 1:net.arch(layers)
		d = expected(i) - values{layers}(i);
		if( net.trans_type == 3)
					d = d * net.beta * ( 1 - values{layers}(i) * values{layers}(i));
		endif
		deltas{layers-1}(i) = d;
	end
	%back-propagate deltas to previous layers
	if ( layers == 2)
		%dont bother propagating; is a simple perceptron
		return;
	endif

	l = layers - 2;
	
	% For each layer "l", for each neuron in "l", calculate the delta for the layer "l+1"
	% Start from the end and go down to the input.

	while(l != 0)
		%first calculate g'(h). Notice that in some trans functions this does not apply (i.e.: deriv = 1)
		for i = 1:net.arch(l+1)
			deriv = 1;
			if( net.trans_type == 3 || net.trans_type == 1)
					deriv = deriv * net.beta * ( 1 - values{l+1}(i) * values{l+1}(i));
			endif
			%now calculate sumnation of weights * deltas of the previous layer to update current layer.
			acum = 0;
			for j = 1:net.arch(l+2)
				acum += deltas{l+1}(j) * net.weights{l+1}(i,j);
			end
			deltas{l}(i) = deriv * acum;
		end
	l -=1;
	end
endfunction

