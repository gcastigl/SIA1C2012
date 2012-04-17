function new_net = update_weights(net,deltas)
	
	% Updates the newtorks weight matrix (learning.)
	for k = 1:size(net.weights,1) % For each layer
		n = size(net.weights{k}); % n = number of weights in the layer 
		lrn_rt = get_lrn_rt(net);
		
		for i = 1:n(1) % For each weight 
			for j = 1:n(2)
				dw = lrn_rt * deltas{k}(j) * net.values{k}(i);
				net.weights{k}(i,j) = net.weights{k}(i,j) + dw;
			end
		end
	end
	new_net = net;
endfunction