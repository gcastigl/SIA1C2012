package geneticalgorithm;

import neuronalnetwork.NeuralNetwork;

public class GeneticAlgorithm {
	
	private Configuration config;

	public GeneticAlgorithm(Configuration config) {
		this.config = config;
	}

	/**
	 * <pre>
	 * 	Inicializar Población 							- population 
	 * 	Mientras no se alcance cond. de corte
	 * 		Evaluar fitness								- evaluate
	 * 		Seleccionar, 								- select
	 * 		Cruzar. 									- cross
	 * 		Mutación 									- mutate
	 * 		Reemplazar.									- replace
	 * </pre>
	 */
	public NeuralNetwork getSolution() {
		initPopulation();
		while (!config.breakCriteria.isFinished(config)) {
			config.selectionMethod.evaluate(config);
			int[] selected = config.selectionMethod.select(config);
			Chromosome[] childs = config.crossoverMethod.cross(config, selected);
			config.crossoverMethod.mutate(config, childs);
			config.replaceMethod.replace(config, childs);
			config.elapsedGen++;
		}
		return null;
	}

	private void initPopulation() {
		config.population = new Chromosome[config.N];
		for (int i = 0; i < config.N; i++) {
			config.population[i] = new Chromosome(
				new NeuralNetwork(config.netConfig.structure).getLayers());
		}
	}

}
