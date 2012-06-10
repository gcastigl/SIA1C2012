package main;

import geneticalgorithm.Configuration;
import geneticalgorithm.GeneticAlgorithm;
import geneticalgorithm.breakcriteria.BreakCriteriaType;
import geneticalgorithm.crossover.CrossoverType;
import geneticalgorithm.mutation.MutationType;
import geneticalgorithm.selector.SelectorType;

import java.io.IOException;

public class GeneticAlgorithmTest {
	
	public static void main(String[] args) {
		// Genetic Algorithm creation
		Configuration config;
		try {
			config = createConfiguration();
			config.initialize();
			GeneticAlgorithm ga = new GeneticAlgorithm(config);
			System.out.println("**************************************");
			ga.getSolution();
		} catch (IOException e) {
			System.out.println("Could not start Genetic Algorithm: " + e.getMessage());
			System.out.println("Program terminated.");
		}
	}
	
	private static Configuration createConfiguration() throws IOException {
		Configuration config = new Configuration();
		config.N = 30;
		config.G = 0.5f;
		config.maxGen = 100;
		config.pMutate = 0.02f;
		config.cp = 0.7f;
		config.backpropp = 0.01f;
		config.pCross_uniform = 0.4f;
		// Seteo de metodos a utilizar
		config.breakCriteriaType 	= BreakCriteriaType.ENT_ERROR;
		config.selectionType 		= SelectorType.ELITE;
		config.crossOverType 		= CrossoverType.CLASICO;
		config.mutationType			= MutationType.MUTATION_CLASICO;
		config.replacementType 		= SelectorType.RULETA;
		config.ke_mixted = (int) (config.N * 0.5f);
		config.eliteSelection = SelectorType.ELITE == config.selectionType;
		return config;
	}

}
