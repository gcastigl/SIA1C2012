function new_net = eval_input ( net, new_input)
	
	% Updates the values of the net given a new input
	% does NOT modify weights, so it can be called with test inputs.
	% check net.values{2} for output vector.

	n = size(net.values{1}, 2) - 1;
	for i = 1:n
		net.values{1}(i) = new_input(i);
	end

	
		
	for i = 1:(size(net.values,1)-1)
		%calculate output of (i+1)th layer
		auxvalues = net.values{i} * net.weights{i};
		for j = 1:size(auxvalues,2)
			% copy it to our value matrix. Notice bias isn't changed. Also apply function.
			value = auxvalues(j);
			if( net.trans_type == 1 || (net.trans_type == 2 && abs(value) > 1))
				value = sign(value);
			endif
			if(net.trans_type == 3)
				value = tanh(net.beta * value);
			endif
			net.values{i+1}(j) = value;
		end
	end
	new_net = net;
endfunction