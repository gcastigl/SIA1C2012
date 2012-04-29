function new_net = add_noise ( net) 
% Updates the newtork with some noise
	for k = 1:size(net.weights,1) % For each layer
		n = size(net.weights{k}); % n = number of weights in the layer 
		lrn_rt = get_lrn_rt(net);
		
		for i = 1:n(1) % For each weight 
			for j = 1:n(2)
				if( rand() < 0.33)
					net.weights{k}(i,j) += 2*(rand() - 1/2);
				endif
				if( rand() < 0.33)
					net.weights{k}(i,j) *= 0.5 + (rand() /2);
				endif
			end
		end
	end
	new_net = net;


endfunction