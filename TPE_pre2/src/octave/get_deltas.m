function deltas = get_deltas(net, expected)
	layers = size(net.arch,2);
	%first calculate delta for output layer.
	for i= 1:net.arch(layers)
		d = expected(i) - net.values{layers}(i);
		if( net.trans_type == 3)
					d = d * net.beta * ( 1 - net.values{layers}(i) * net.values{layers}(i));
		endif
		deltas{layers-1}(i) = d;
	end
	%back-propagate deltas to previous layers
	if ( layers == 2)
		%dont bother propagating; is a simple perceptron
		return;
	endif
	l = layers - 2;
	while(l != 0)
		%first calculate g'(h). Notice that in some trans functions this does not apply (i.e.: deriv = 1)
		for i = 1:net.arch(l+1)
			deriv = 1;
			if( net.trans_type == 3 && i != size(net.values{l+1},2))
					deriv = deriv * net.beta * ( 1 - net.values{l+1}(i) * net.values{l+1}(i));
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

