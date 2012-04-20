function train_set = getRandomSamples(file, percentage)

	points = load(file);

	x = points(:,1);
	y = points(:,2);
	answ = points(:,3);

	number_of_points = floor(percentage*length(x));

	train_set = {};

	for i = 1:number_of_points
		row = floor(rand() * (number_of_points -1) + 1);
		train_set{i} = {[x(row) y(row)]		answ(row)};
	end

endfunction