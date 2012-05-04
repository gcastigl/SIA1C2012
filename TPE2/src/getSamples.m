function test_set = getSamples(points)
	x = points(:,1);
	y = points(:,2);
	answ = points(:,3);
	totalPoints = length(x);
	printGreenColor();
	printf("\nUsing %d points fot testing the generalization power of the network\n\n", totalPoints);
	releasePrintColor();

	test_set = {};
	for i = 1:totalPoints
		row = floor(rand() * (totalPoints -1) + 1);
		test_set{i} = {[x(row) y(row)]		answ(row)};
	end
	


endfunction