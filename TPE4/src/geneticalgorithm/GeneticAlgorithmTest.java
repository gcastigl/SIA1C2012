package geneticalgorithm;

import neuronalnetwork.NeuralNetwork;
import geneticalgorithm.crossover.Clasic;
import geneticalgorithm.selector.CandidateSelector;
import geneticalgorithm.selector.EliteSelector;

public class GeneticAlgorithmTest {

	public static void main(String[] args) {
		Configuration config = new Configuration();
		config.N = 50;
		config.G = 0.6f;
		config.maxGen = 100;
		config.mp = 0.01f;
		config.cp = 0.7f;
		
		config.crossOverType = "clasico";
		config.crossoverMethod = new Clasic();
		
		config.selectionType = "elite";
		config.selectionMethod = new CandidateSelector[1];
		config.selectionMethod[0] = new EliteSelector();
		
		GeneticAlgorithm ga = new GeneticAlgorithm(config);
		NeuralNetwork best = ga.getSolution();
		System.out.println(best);
	}
}
