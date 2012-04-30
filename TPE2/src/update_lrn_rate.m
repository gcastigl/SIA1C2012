function new_net = update_lrn_rate ( net, error, target_error)
	%Updates the learn rate according to learn strategy
	%1 = CONSTANT, does nothing.
	%2 = ANNEALED, exponential decay over time
	%3 = SMART, if error is being consistently reduced, increases learn rate
	%else, learn rate is exponentialy reduced
	%max learn rate is 1/2
	if(net.lrn_type ==2)
		net.counter +=1;
		if(net.counter == 50)
			net.counter =0;
			net.lrn_rt = net.lrn_decay * net.lrn_rt;
		endif
	endif
	if(net.lrn_type == 3)
		if(error > net.prev_err)
			net.counter = 0;
			net.lrn_rt = net.lrn_decay * net.lrn_rt;
			if( net.lrn_rt < 0.02)
				net.lrn_rt = 0.02;
			endif
		elseif( error < net.prev_err)
			net.counter +=1;
			if(net.counter >= net.lrn_consist)
				net.lrn_rt = net.lrn_rt + net.lrn_sum;
				if(net.lrn_rt > 0.5)
					net.lrn_rt = 0.5;
				endif
			endif
		endif
		%check for local minimum
	endif
%	if(net.lrn_type == 3 && error > 5*target_error && net.lrn_rt < 0.05)
		%error is higher than 1, net lrn rate is LOW, local minimum spotted!
%		net.prev_err = Inf;
%		net.lrn_rt = 1;
%	else
		net.prev_err = error;
%	endif
	new_net = net;
endfunction