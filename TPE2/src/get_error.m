function toterr = get_error(values, expected, trans_type)
	
	%Returns the total error for the last input.
	%Returns the Hamming distance for step functions,
	%else returns SUMSQ

	toterr = 0;
	if(trans_type == 1)
		for i = 1:size(values{size(values,2)},2)
			if( values{size(values,2)}(i) != expected(i))
				toterr = toterr +1;
			endif
		end
	else
		toterr = sumsq( values{size(values,2)} - expected);
	endif
endfunction 