function new_net = update_weights(net, expected)
	deltas = expected - net.values{2};
	n = size(net.weights);
	lrn_rt = get_lrn_rt(net);
	for i = 1:n(1)
		for j = 1:n(2)
			dw = lrn_rt * deltas(j) * net.values{1}(i);
			if( net.trans_type == 3)
				dw = dw * net.beta * ( 1 - net.values{2}(j) * net.values{2}(j));
			endif
			net.weights(i,j) = net.weights(i,j) + dw;
		end
	end
	new_net = net;
endfunction