function new_net = add_noise ( net) 
% Updates the newtork with some noise
	for k = 1:size(net.weights,1) % For each layer
		n = size(net.weights{k});

		%pick a link at random
		i = ceil(rand() * n(1));
		j = ceil(rand() * n(2));
		%add a value-based noise
		net.weights{k}(i,j) *= 0.5 + (rand()/2);
	end
	new_net = net;


endfunction