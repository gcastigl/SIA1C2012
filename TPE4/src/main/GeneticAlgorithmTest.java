package main;

import geneticalgorithm.Configuration;
import geneticalgorithm.GeneticAlgorithm;

import java.io.IOException;

import neuronalnetwork.NetConfiguration;
import neuronalnetwork.function.TanhFunction;

public class GeneticAlgorithmTest {
	
	public static void main(String[] args) {
		// Genetic Algorithm creation
		Configuration config;
		try {
			config = createConfiguration();
			config.initialize();
			GeneticAlgorithm ga = new GeneticAlgorithm(config);
			ga.getSolution();
		} catch (IOException e) {
			System.out.println("Could not start Genetic Algorithm: " + e.getMessage());
			System.out.println("Program terminated.");
		}
	}
	
	private static Configuration createConfiguration() throws IOException {
		Configuration config = new Configuration();
		config.N = 20;
		config.G = 0.6f;
		config.maxGen = 100;
		config.mp = 0.01f;
		config.cp = 0.7f;
		config.backpropp = 0.05f;
		// Seteo de metodos a utilizar
		config.breakCriteriaType 	= Configuration.BREAKCRITERIA_MAX_GEN;
		config.selectionType 		= Configuration.SELECTOR_ELITE;
		config.crossOverType 		= Configuration.CROSSOVER_CLASICO;
		config.mutationType			= Configuration.MUTATION_CLASICO;
		config.replacementType 		= Configuration.SELECTOR_ELITE;
		// Set up net configuration
		config.netConfig = new NetConfiguration();
		config.netConfig.structure = new int[] {2, 5, 1};
		config.netConfig.p = 0.7f;
		config.netConfig.eta = 0.1f;
		config.netConfig.f = new SgFunction();
		return config;
	}

}
