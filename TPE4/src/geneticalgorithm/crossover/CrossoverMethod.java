package geneticalgorithm.crossover;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import geneticalgorithm.NetModificator;

public abstract class CrossoverMethod extends NetModificator {
	
	protected Chromosome[] childs;
	
	public CrossoverMethod(Configuration config) {
		super(config);
		childs = new Chromosome[2];
		System.out.println("Crossover method: " + getClass().getSimpleName());
	}

	/**
	 * <pre>
	 * cross() + mutate() son los operadores de busqueda del algoritmo. 
	 * Se corresponden, respectivamente, con la reproduccion y las mutantes biologicas, 
	 * y consisten en la combinacion aleatoria de dos (generalmente) o mas soluciones y 
	 * la variacion aleatoria de las mismas. Estos procesos general una nueva generacion
	 * de un tama�o determinado.
	 * <pre>
	 */
	public Chromosome[] cross(int[] selected) {
		Chromosome[] newIndividuals = new Chromosome[selected.length]; 
		boolean oddSelection = selected.length % 2 == 1;
		int maxLen = selected.length;
		if (oddSelection) {
			// Si hay un numero impar, cruzo el ultimo con el primero
			// y luego todas las parejas desde 0 hasta N - 1 (par)
			maxLen--;
			cross(newIndividuals, maxLen, selected[0], selected[maxLen], 1);
		}
		for (int i = 0; i < maxLen; i += 2) {
			cross(newIndividuals, i, selected[i], selected[i + 1], 2);
		}
		return newIndividuals;
	}

	/**
	 * Cruza los chromosomas s1 con s2 con una probabilidad cp. Si el cruce no se produce, se agregan
	 * ambos chormosomas intactos a la nueva generacion.
	 */
	private void cross(Chromosome[] newIndividuals, int index, int s1, int s2, int childsToCreate) {
		Chromosome[] population = config.population;
		Chromosome c1, c2;
		if (config.cp > Math.random()) {
			// Make a crossover only with probability cp
			cross(population[s1], population[s2]);
			c1 = childs[0];
			c2 = childs[1];
		} else {
			// otherwise leave Chromosomes without change
			c1 = population[s1];
			c2 = population[s2];
		}
		newIndividuals[index] = c1;
		if (childsToCreate == 2) {
			newIndividuals[index + 1] = c2;
		}
	}

	/**
	 * Cruza c1 con c2 y devuelve un vector con los dos hijos generados 
	 */
	protected abstract void cross(Chromosome c1, Chromosome c2);
	
}
