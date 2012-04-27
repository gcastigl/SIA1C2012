function TrainAndTestSet = getRandomSamples(points, percentage)
	x = points(:,1);
	y = points(:,2);
	answ = points(:,3);
	totalPoints = length(x);
	number_of_points = floor(percentage * totalPoints);

	train_set = {};
	for i = 1:number_of_points
		row = floor(rand() * (number_of_points -1) + 1);
		train_set{i} = {[x(row) y(row)]		answ(row)};
		usedIndexes(i) = row;
	end
	
	k = 1;
	test_set = {};
	for i = 1:totalPoints
	  used = false;
	  for j = 1:number_of_points
		if (usedIndexes(j) == i)
		  used = true;
		end
	  end
	  if (!used)
		test_set{k++} = {[x(i) y(i)]		answ(i)};
	  end
	end

  TrainAndTestSet = {};
  TrainAndTestSet{1} = train_set;
  TrainAndTestSet{2} = test_set;
endfunction