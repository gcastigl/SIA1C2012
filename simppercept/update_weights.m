function new_net = update_weights(net, expected)
	deltas = net.values{2} - expected;
	n = size(net.weights);
	lrn_rt = get_lrn_rt(net);
	for i = 1:n(1)
		for j = 1:n(2)
			dw = lrn_rt * deltas(j) * net.values{1}(i);
			net.weights(i,j) = net.weights(i,j) + dw;
		end
	end
	new_net = net;
endfunction