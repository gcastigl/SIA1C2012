function new_net = eval_input ( net, new_input)
	%updates the values of the net given a new input
	n = size(net.values{1},2 ) - 1;
	for i= 1:n
		net.values{1}(i) = new_input(i);
	end
	net.values{2} = net.values{1} * net.weights;
	for i = 1:size(net.values{2},2)
		%TODO: cableo por ahora primer tipo
		net.values{2}(i) = sign(net.values{2}(i));
	endfor
	new_net = net;
endfunction