function p = get_precision( vec, len, err)
	p = 0;
	for i = 1:len
		if(vec(i) < err)
			p +=1;
		endif
	end
	p/=len;
endfunction