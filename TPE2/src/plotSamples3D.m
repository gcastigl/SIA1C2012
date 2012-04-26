function ret = plotSamples3D(vec, z, points, vals)
  % Plot the network stats
  clf;
  x = points(:,1);
  y = points(:,2);
  z = points(:,3);
  % plot original values
  plot3(x,y,z, '*3');
  % plot net approximated values
  hold on;
  for i=1:length(vec)
	 xtemp(i) = vals{vec(i)}{1}(1);
	 ytemp(i) = vals{vec(i)}{1}(2);
	 ztemp(i) = z(i);
  end
  plot3(xtemp, ytemp, ztemp, '@1')
  drawnow()
endfunction