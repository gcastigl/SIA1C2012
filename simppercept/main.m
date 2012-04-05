function net = main()
	epochs = 200;
	i1 = { [-1,-1,-1,1] , 1};
	i2 = { [-1,-1,-1,-1] , -1};
	i3 = {[1,1,1,1], 1};
	i4 = {[-1,1,-1,1],1};
	i5 = {[1,1,-1,-1] , 1};
	i6 = {[1,-1,-1,-1], 1};
	i7 = {[-1,1,-1,-1] , 1};
	i8 = {[-1,-1,1,-1] , 1};
	vals = {i1,i2,i3,i4,i5,i6,i7,i8};
	net = crt_nr_nw(4,1,1,1);
	for i = 1:epochs
		vec = get_randorder(8);
		for j = 1:8
			net = eval_input(net, vals{vec(j)}{1});
			net = update_weights(net, vals{vec(j)}{2});
		end
	end
endfunction