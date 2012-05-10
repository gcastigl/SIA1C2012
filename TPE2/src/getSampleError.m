function ret = getSampleError(net, test_set)
	q = size(test_set,2);
	for i=1:q
		output(i) = test_set{i}{2};
		vals = eval_input(net, test_set{i}{1});
		expected(i) = vals{size(vals,2)}(1);
	end
	% ret = sumsq(output, expected) /q;
	ret = sumsq(output - expected) / q;
endfunction