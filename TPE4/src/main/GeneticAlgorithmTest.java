package main;

import geneticalgorithm.Configuration;
import geneticalgorithm.GeneticAlgorithm;
import geneticalgorithm.breakcriteria.BreakCriteriaType;
import geneticalgorithm.crossover.CrossoverType;
import geneticalgorithm.mutation.MutationType;
import geneticalgorithm.selector.SelectorType;

import java.io.IOException;

import neuronalnetwork.NeuralNetwork;

public class GeneticAlgorithmTest {
	
	private static int N;
	private static float G;
	private static int maxGen;
	private static float mp;
	private static float cp;
	private static SelectorType selectionType;
	private static SelectorType replacementType;
	
	private static int TOTAL_ARGS = 7;
	
	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			printHelp();
			return;
		} else {
			if (args.length == TOTAL_ARGS) {
				parseArgs(args);				
			} else if (args.length == 1 && "default".equals(args[0])) {
				initDefaultArgsValues();
			} else {
				printHelp();
				return;
			}
		}
		// Genetic Algorithm creation
		Configuration config;
		try {
			config = createConfiguration();
			config.initialize();
			GeneticAlgorithm ga = new GeneticAlgorithm(config);
			System.out.println("**************************************");
			NeuralNetwork nw = ga.getSolution();
			nw.persist("out.nw", config);
		} catch (IOException e) {
			System.out.println("Could not start Genetic Algorithm: " + e.getMessage());
			System.out.println("Program terminated.");
		}
	}

	private static void printHelp() {
		System.out.println("******* Program usage *******");
		System.out.println("Invocation parameters: N G maxGen mp cp selectionType replacementType");
		System.out.println("\tN\t= population size");
		System.out.println("\tG\t= Generation gap [0 - 1]");
		System.out.println("\tmaxGen\t= Max generation to run [available only with break criteria = BreakCriteriaType.MAX_GEN]");
		System.out.println("\tmp\t= mutation probability [0 - 1]");
		System.out.println("\tcp\t= crossover probability [0 - 1]");
		System.out.println("\tselectionType\t= ELITE - BOLTZMAN - UNIVERSAL - RULETA - MIXED_BOLTZMAN - MIXED_RULETA - TURNAMENT");
		System.out.println("\treplacementType = ELITE - BOLTZMAN - UNIVERSAL - RULETA - MIXED_BOLTZMAN - MIXED_RULETA - TURNAMENT");
		System.out.println("**************************");
		System.out.println("==> Call with a signle parameter \"default\" to start G.A. with deafualt values.");
		System.out.println("==> For advanced parameter Tune up, checkout class GeneticAlgorithmTest(main class) and set up any other desired paramer @createConfiguration() method");
		System.out.println("\tConfiguracion class has all available parameters with the documentation explaining what it does.");
	}

	private static void parseArgs(String[] args) {
		try {
			N = Integer.parseInt(args[0]);
			G = Float.parseFloat(args[1]);
			maxGen = Integer.parseInt(args[2]);
			mp = Float.parseFloat(args[3]);
			cp = Float.parseFloat(args[4]);
			selectionType = SelectorType.valueOf(args[5]);
			replacementType = SelectorType.valueOf(args[6]);
		} catch (NumberFormatException e) {
			System.out.println("Could not parse initial config values.");
			System.out.print("A numeric value could not be parsed. " + e.getMessage());
			throw e;
		}
	}
	
	private static void initDefaultArgsValues() {
		N = 30;
		G = 0.5f;
		maxGen = 100;
		mp = 0.02f;
		cp = 0.7f;
		selectionType = SelectorType.ELITE;
		replacementType = SelectorType.RULETA;
	}
	
	public static Configuration createConfiguration() throws IOException {
		Configuration config = new Configuration();
		config.N = N;
		config.G = G;
		config.maxGen = maxGen;
		config.pMutate = mp;
		config.cp = cp;
		config.backpropp = 0.01f;
		config.pCross_uniform = 0.4f;
		// Seteo de metodos a utilizar
		config.breakCriteriaType 	= BreakCriteriaType.MAX_GEN;
		config.selectionType 		= selectionType;
		config.crossOverType 		= CrossoverType.ANULAR;
		config.mutationType			= MutationType.MUTATION_CLASICO;
		config.replacementType 		= replacementType;
		config.ke_mixted = 6;
		return config;
	}

}
