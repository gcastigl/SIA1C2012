function dws = get_weight_changes(net, deltas, values, prev)

	for k = 1:size(net.weights,1) % For each layer
		n = size(net.weights{k}); % n = number of weights in the layer 
		lrn_rt = get_lrn_rt(net);
		
		for i = 1:n(1) % For each weight 
			for j = 1:n(2)
				dws{k}(i,j) = 0;
				%batch, for each set of deltas add
				for l = 1:size(deltas,2)
					dw = lrn_rt * deltas{l}{k}(j);
					dw = dw *  values{l}{k}(i);
					dws{k}(i,j) +=  dw;
				end
				%add overall previous descent
				if(nargin == 4)
					dws{k}(i,j) += net.mom_coef * prev{k}(i,j);
				endif
				dws{k}(i,j) *= net.boolweights{k}(i,j);
			end
		end
	end


endfunction