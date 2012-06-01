package geneticalgorithm;

import neuronalnetwork.NeuralNetwork;


public class GeneticAlgorithm {

	private static final int[] structure = {5, 3, 1}; 
	
	private Configuration config;
	private BreakCriteria breakCriteria;
	
	public GeneticAlgorithm(Configuration config) {
		this.config = config;
	}
	
	/**
	 * <pre>
	 * 	Inicializar Población 							-	population 
	 * 	Mientras no se alcance cond. de corte, 
	 * 		Evaluar la función de aptitud ( f ) para todos los individuos nuevos.	- Fitness
	 * 		Seleccionar, 															- select
	 * 		Aparear. 																- doChilds
	 * 		Recombinación, Mutación 												- mutate
	 * 		Reemplazar.
	 * </pre>
	 */
	public NeuralNetwork getSolution() {
		initPopulation();
		while (!breakCriteria.isFinished(config)) {
			if (!config.isMixtedSet()) {
				int[] selected = config.selectionMethod[0].select(config);
				Chromosome[] childs = config.crossoverMethod.cross(config, selected);
			}
			
		}
		return null;
	}

	private void initPopulation() {
		config.population = new Chromosome[config.N];
		for (int i = 0; i < config.N; i++) {
			config.population[i] = new Chromosome(new NeuralNetwork(structure).getLayers());
		}
	}
}
