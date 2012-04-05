function net = main(trans, lrn_base, lrn_type)
	epochs = 500;
	t1 = { [-1,-1,-1,1] , 1};
	t2 = { [-1,-1,-1,-1] , -1};
	t3 = {[1,1,1,1], 1};
	t4 = {[-1,1,-1,1],1};
	t5 = {[1,1,-1,-1] , 1};
	t6 = {[1,-1,-1,-1], 1};
	t7 = {[-1,1,-1,-1] , 1};
	t8 = {[-1,-1,1,-1] , 1};
	vals = {t1,t2,t3,t4,t5,t6,t7,t8};
	i1 = {[-1,-1,-1,1], 1};
	i2 = {[1, -1, 1, 1],1};
	i3 = {[-1,-1,1,1],1};
	i4 = {[-1,1,1,-1],1};
	test_set = 4;
	tests = {i1,i2,i3,i4};
	train_set = 8;
	net = crt_nr_nw(4,1,lrn_type,lrn_base,trans);
	errors = zeros(epochs,1);
	test_errors = zeros(epochs,1);
	lrn_rates = zeros(epochs,1);
	for i = 1:epochs
		vec = get_randorder(train_set);
		err = 0;
		for j = 1:train_set
			net = eval_input(net, vals{vec(j)}{1});
			net = update_weights(net, vals{vec(j)}{2});
			err += get_error(net, vals{vec(j)}{2});
		end
		errors(i) = err / train_set;
		terror = 0;
		for  j = 1:test_set
			net = eval_input(net, tests{j}{1});
			terror += get_error(net, tests{j}{2});
		end
		test_errors(i) = terror;
		net = update_lrn_rate(net, errors(i));
		lrn_rates(i) = net.lrn_rt;
	end
	plot(1:epochs, errors, 1:epochs, test_errors, 1:epochs, lrn_rates);
endfunction