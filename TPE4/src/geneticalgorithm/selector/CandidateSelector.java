package geneticalgorithm.selector;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import neuronalnetwork.NeuralNetwork;

public abstract class CandidateSelector {

	/**
	 * <pre>
	 * Represena la bondad de una determianda solucion (es decir, el valor de la
	 * funcion a optimizar; f: S -> R, para los parametros representados por la
	 * solucion). Esto se corresponde con la adecuacion al medio de un individuo
	 * en el simil biologico.
	 * </pre>
	 */
	public void evaluate(Configuration config) {
		for (Chromosome c : config.population) {
			calcFitness(c);
		}
	}

	/**
	 * Setea el fitness del invididuo.
	 */
	protected void calcFitness(Chromosome c) {
		NeuralNetwork n = new NeuralNetwork(c.getLayers());
		
	}

	/**
	 * <pre>
	 * Consiste en escoger (de manera generalmene probabilistica) las mejores
	 * soluciones, correspondiente en la naturaleza con la seleccion de los mas
	 * aptos.
	 * </pre>
	 */
	public abstract int[] select(Configuration config);

	/**
	 * <pre>
	 * Consiste en sutituir a los individuos de la generacion t por los de la
	 * nueva generacion para que la poblacion permanezca constante. 
	 * La nueva generacion puede reemplazar totalmente a la anterior (G = 1) o bien, en
	 * el extremo contrario, en cada generacion puede necesariamente procesarse
	 * un solo individuo que sustituye generalmente al peor.
	 * </pre>
	 */
	public abstract void replace(Configuration config);
}
