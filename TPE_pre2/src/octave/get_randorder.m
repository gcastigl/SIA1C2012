function vec = get_randorder(len)
	vec = zeros(len,1);
	for i = 1:len
		vec(i) = i;
	end

	for i = 1:len
		rand1 = ceil(rand() * len);
		rand2 = ceil(rand() * len);
		aux = vec(rand1);
		vec(rand1) = vec(rand2);
		vec(rand2) = aux;
	end
	
endfunction