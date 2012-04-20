function weights = init_weights( arch )
	%returns a matrix with random values between -0.25 and 0.25
	weights = cell(size(arch,2) -1,1);
	for i = 1:(size(arch,2)-1)
		weights{i} = rand( arch(i) +1 , arch(i+1));
		weights{i}= weights{i} .- 0.5;
	end
endfunction