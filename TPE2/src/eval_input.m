function values = eval_input ( net, new_input)
	
	% Updates the values of the net given a new input
	% does NOT modify weights, so it can be called with test inputs.
	% check net.values{2} for output vector.
	n = net.arch(1);
	for i = 1:n
		values{1}(i) = new_input(i);
	end
	values{1}(n+1) = -1;

	
	s = size(net.arch,2)-1;
	for i = 1:s
		% Calculate output of (i+1)th layer
		auxvalues = values{i} * net.weights{i};
		for j = 1:size(auxvalues,2)
			value = auxvalues(j);
			if( i == s && ( net.trans_type == 1 || (net.trans_type == 2 && abs(value) > 1)))
				value = sign(value);
			else if(net.trans_type ==  1) % Threshold function
				value = tanh(net.beta * value);
			elseif(net.trans_type == 3)  % Sigmoid (tanh) function
				if(i == s) % Use tanh() except in the output layer
					value = 1 / (1 + exp(- net.beta * value));
				else
					value = tanh(net.beta * value);
				end
			elseif(net.trans_type == 4) % Sigmoid (exponential) function
				value = 1 / (1 + exp(- net.beta * value));
			end
			endif
			values{i+1}(j) = value;
		end
		%bias
		if( i != s)
			values{i+1}(size(auxvalues,2) + 1) = -1;
		endif
	end
	
endfunction