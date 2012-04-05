function weights = init_weights( in_len, out_len )
	%returns a matrix with random values between -0.25 and 0.25
	weights = rand( in_len, out_len);
	weights= weights .- 0.5;
	weights = weights ./2;
endfunction