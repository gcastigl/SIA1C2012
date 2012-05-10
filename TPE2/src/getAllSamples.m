function test_set = getAllSamples(points)
	x = points(:,1);
	y = points(:,2);
	answ = points(:,3);
	totalPoints = length(x);
	printGreenColor();
	printf("\nUsing %d points fot testing the generalization power of the network\n\n", totalPoints);
	releasePrintColor();

	test_set = {};
	for i = 1:totalPoints
		test_set{i} = {[x(i) y(i)]		answ(i)};
	end



endfunction