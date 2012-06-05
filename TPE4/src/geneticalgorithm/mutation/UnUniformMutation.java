package geneticalgorithm.mutation;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

public class UnUniformMutation extends ClasicMutation {
	
	public UnUniformMutation(Configuration config) {
		super(config);
		System.out.println("Usando Metodo No uniforme de mutacion");
		System.out.println("C: " + config.c_unUniform);
		System.out.println("N: " + config.n_unUniform);
	}
	
	@Override
	public void mutate(Chromosome c) {
		if (config.elapsedGen%config.n_unUniform == 0) {
			config.pMutate *= config.c_unUniform;
		}
		super.mutate(c);
	}
	

}
