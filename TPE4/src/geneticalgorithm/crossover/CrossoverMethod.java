package geneticalgorithm.crossover;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

public abstract class CrossoverMethod {

	/**
	 * <pre>
	 * cross() + mutate() son los operadores de busqueda del algoritmo. 
	 * Se corresponden, respectivamente, con la reproduccion y las mutantes biologicas, 
	 * y consisten en la combinacion aleatoria de dos (generalmente) o mas soluciones y 
	 * la variacion aleatoria de las mismas. Estos procesos general una nueva generacion
	 * de un tamaño determinado.
	 * <pre>
	 */
	public abstract Chromosome[] cross(Configuration config, int[] selected);
	}

	/**
	 * Cruza c1 con c2 y devuelve un vector con los dos hijos generados 
	 */
	protected abstract Chromosome[] cross(Chromosome c1, Chromosome c2);
	
	public abstract Chromosome[] mutate(Configuration config, Chromosome[] childs);

}
