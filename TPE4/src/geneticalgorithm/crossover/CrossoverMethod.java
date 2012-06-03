package geneticalgorithm.crossover;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import neuronalnetwork.NetSerializer;

public abstract class CrossoverMethod {

	protected float[] netAsArray;
	protected Configuration config;
	
	public CrossoverMethod(Configuration config) {
		this.config = config;
		int connections = NetSerializer.totalConnections(config.netConfig.structure);
		netAsArray = new float[connections];
	}
		
	/**
	 * <pre>
	 * cross() + mutate() son los operadores de busqueda del algoritmo. 
	 * Se corresponden, respectivamente, con la reproduccion y las mutantes biologicas, 
	 * y consisten en la combinacion aleatoria de dos (generalmente) o mas soluciones y 
	 * la variacion aleatoria de las mismas. Estos procesos general una nueva generacion
	 * de un tamaño determinado.
	 * <pre>
	 */
	public Chromosome[] cross(int[] selected) {
		Chromosome[] population = config.population;
		Chromosome[] newIndividuals = new Chromosome[selected.length]; 
		boolean oddSelection = selected.length % 2 == 1;
		int maxLen = selected.length;
		if (oddSelection) {
			// Si hay un numero impar, cruzo el ultimo con el primero
			// y luego todas las parejas desde 0 hasta N - 1 (par)
			maxLen--;
			Chromosome[] childs = cross(
				population[selected[0]], population[selected[maxLen]]);
			newIndividuals[maxLen] = childs[0];
		}
		for (int i = 0; i < maxLen; i += 2) {
			Chromosome c1 = population[selected[i]];
			Chromosome c2 = population[selected[i + 1]];
			Chromosome[] childs = cross(c1, c2);
			newIndividuals[i] = childs[0];
			newIndividuals[i + 1] = childs[1];
		}
		return newIndividuals;
	}


	/**
	 * Cruza c1 con c2 y devuelve un vector con los dos hijos generados 
	 */
	protected abstract Chromosome[] cross(Chromosome c1, Chromosome c2);
	
}
