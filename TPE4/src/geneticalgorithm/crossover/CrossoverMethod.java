package geneticalgorithm.crossover;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import geneticalgorithm.NetModificator;
import geneticalgorithm.selector.EliteSelector;

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
	 * de un tamaño determinado.
	 * 
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
		if (config.selectionMethod instanceof EliteSelector) {
			return eliteCrossover(newIndividuals, selected);
		} else {
			return newIndividuals;
		}
	}

	private Chromosome[] eliteCrossover(Chromosome[] newIndividuals, int[] selected) {
		Chromosome[] individualPool = new Chromosome[selected.length + newIndividuals.length];
		for (int i = 0; i < selected.length; i++) {
			individualPool[i] = config.population[selected[i]];
		}
		for (int i = selected.length; i < selected.length + newIndividuals.length; i++) {
			individualPool[i] = newIndividuals[i - selected.length];
		}
		int N = individualPool.length;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N - i; j++) {
				if (individualPool[j].getFitness() < individualPool[j + 1].getFitness()) {
					Chromosome aux = individualPool[j];
					individualPool[j] = individualPool[j + 1];
					individualPool[j + 1] = aux;
				}
			}
		}
		for (int i = 0; i < newIndividuals.length; i++) {
			newIndividuals[i] = individualPool[i];
		}
		return newIndividuals;
	}

	/**
	 * Cruza los chromosomas s1 con s2 con una probabilidad cp. Si el cruce no
	 * se produce, se agregan ambos chormosomas intactos a la nueva generacion.
	 */
	private void cross(Chromosome[] newIndividuals, int index, int s1, int s2,
			int childsToCreate) {
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
