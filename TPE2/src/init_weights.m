function [weights, boolweights] = init_weights( arch , symmetry)
	%returns a matrix with random values between -0.25 and 0.25
	weights = cell(size(arch,2) -1,1);
	boolweights = cell(size(arch,2) -1,1);
	for i = 1:(size(arch,2)-1)
		weights{i} = rand( arch(i) +1 , arch(i+1));
		weights{i}= weights{i} .- 0.5;
		boolweights{i} = zeros ( arch(i) + 1, arch(i+1)) + 1;
	end
	%break the symmetry
	coef = 2;
	for k = 2:arch(1)
		for i = 1:arch(2)
			if( mod(i,coef) == 0)
				boolweights{1}(k,i) = symmetry;
			endif
		end
		coef +=1;
	end
	
endfunction