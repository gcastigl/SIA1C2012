package geneticalgorithm;

import neuronalnetwork.NeuralNetwork;

public class GeneticAlgorithmTest {
	
	public static void main(String[] args) {
		// Genetic Algorithm creation
		Configuration config = createConfiguration();
		config.initialize();
		GeneticAlgorithm ga =  new GeneticAlgorithm(config);
		NeuralNetwork best = ga.getSolution();
		System.out.println(best);
	}
	
	private static Configuration createConfiguration() {
		Configuration config = new Configuration();
		config.N = 50;
		config.G = 0.6f;
		config.maxGen = 100;
		config.mp = 0.01f;
		config.cp = 0.7f;
		// Selteo de metodos
		config.breakCriteriaType 	= Configuration.BREAKCRITERIA_MAX_GEN;
		config.selectionType 		= Configuration.SELECTOR_ELITE;
		config.crossOverType 		= Configuration.CROSSOVER_CLASICO;
		config.replacementType 		= Configuration.SELECTOR_ELITE;
		return config;
	}
	
}
