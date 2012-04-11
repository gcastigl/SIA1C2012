function new_net = eval_input ( net, new_input)
	
	% Updates the values of the net given a new input
	% does NOT modify weights, so it can be called with test inputs.
	% check net.values{2} for output vector.

	n = size(net.values{1}, 2) - 1;
	for i = 1:n
		net.values{1}(i) = new_input(i);
	end

	net.values{2} = net.values{1} * net.weights;

	for i = 1:size(net.values{2}, 2)
		
		if( net.trans_type == 1 || (net.trans_type == 2 && abs(net.values{2}(i)) > 1))
			net.values{2}(i) = sign(net.values{2}(i));
		endif

		if(net.trans_type == 3)
			net.values{2}(i) = tanh(net.beta * net.values{2}(i));
		endif

	endfor
	new_net = net;
endfunction