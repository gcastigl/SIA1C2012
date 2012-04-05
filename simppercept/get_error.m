function toterr = get_error(net, expected)
	%Returns the total error for the last input.
	%Returns the Hamming distance for step functions,
	%else returns SUMSQ
	toterr = 0;
	if(net.trans_type == 1)
		for i = 1:size(net.values{2},2)
			if( net.values{2}(i) != expected(i))
				toterr = toterr +1;
			endif
		end
	else
		toterr = sumsq( net.values{2} - expected);
	endif