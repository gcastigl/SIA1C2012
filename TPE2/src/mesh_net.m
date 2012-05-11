function mesh_net( net, set)
	
	clf;
	q = size(set,2);
	for i = 1:q
		x(i) = set{i}{1}(1);
		y(i) = set{i}{1}(2);
		vals = eval_input(net, [x(i), y(i)]);
		z(i) = vals{size(vals,2)}(1);
	end
	dx = 0.1;
	dy = 0.1;
	xlin = [floor(min(x)):dx:ceil(max(x))];
	ylin = [floor(min(y)):dy:ceil(max(y))];
	
	[X Y ] = meshgrid(xlin, ylin);
	Z = griddata(x,y,z,X,Y);
	mesh(X,Y,Z);
	axis([-3 3 -4 3 0 1.4]);
endfunction