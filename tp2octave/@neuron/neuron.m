function neur = neuron(link_no)
	%creates a new neuron.
	%Requires number of previous connections.
	%initialize weights and biases with numbers between -0.25 and 0.25 
	if(nargin == 0)
		error("neuron: expecting number of previous links as argument");
	endif
	neur.weights = (rand(link_no,1) - 0.5) / 2;
	neur.bias = (rand() - 0.5 ) / 2;
	neur = class(neur, "neuron");
endfunction