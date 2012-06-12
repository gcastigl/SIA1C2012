package main;

import geneticalgorithm.Configuration;

import java.util.List;

import neuronalnetwork.MSE;
import neuronalnetwork.NetConfiguration;
import neuronalnetwork.NeuralNetwork;
import neuronalnetwork.TrainItem;
import neuronalnetwork.function.SigmoidFunction;
import neuronalnetwork.function.TanhFunction;

public class NeuralNetworkErrorCalculator {
	
	public static void main(String[] args) {
		String filename = "out.nw";
		String examplesName = NetConfiguration.examplesFile;
		try{
			Configuration config = GeneticAlgorithmTest.createConfiguration();
			//config.initialize();
			NeuralNetwork nw = NeuralNetwork.recover(filename, config);
			int inputDim = config.netConfig.structure[0];
			int outputDim = config.netConfig.structure[config.netConfig.structure.length - 1];
			List<TrainItem> allexamples = ExamplesUtils.loadExamples(examplesName, inputDim, outputDim);
			if (config.netConfig.f instanceof TanhFunction) {
				ExamplesUtils.normalizeTanh(allexamples);
			} else if (config.netConfig.f instanceof SigmoidFunction) {
				ExamplesUtils.normalizeSigmoid(allexamples);
			}
			System.out.println("Error for net is:" + MSE.calc(nw, config.netConfig.f, allexamples));
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
