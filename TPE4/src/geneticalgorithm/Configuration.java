package geneticalgorithm;

import geneticalgorithm.breakcriteria.BreakCriteria;
import geneticalgorithm.breakcriteria.BreakCriteriaType;
import geneticalgorithm.crossover.CrossoverMethod;
import geneticalgorithm.crossover.CrossoverType;
import geneticalgorithm.mutation.MutationMethod;
import geneticalgorithm.mutation.MutationType;
import geneticalgorithm.selector.CandidateSelector;
import geneticalgorithm.selector.SelectorType;

import java.io.IOException;

import main.ExamplesUtils;
import neuronalnetwork.NetConfiguration;
import neuronalnetwork.function.SigmoidFunction;
import neuronalnetwork.function.TanhFunction;

public class Configuration {
	
	public BreakCriteriaType breakCriteriaType;
	public SelectorType selectionType;
	public CrossoverType crossOverType;
	public MutationType mutationType;
	public SelectorType replacementType;
	
	/** Poblacion */
	public int N; 
	/** Brecha generacional */
	public float G;
	/** Numero maximo de generaciones */
	public int maxGen;
	/** Probabilidad de cruce */
	public float cp;
	/** Probabilidad de mutacion */
	public float pMutate;
	/** Probabilidad de realizar backpropagation */
	public float backpropp;
	
	public int elapsedGen;
		
	/** Para metodo MIXTO solamente. Cantidad de individuos utilizando Elite */
	public int ke_mixted;
	/** Para metodo CRUCE UNIFORME PARAMETRIZADO solamente. probabilidad de cruce de alelo. */
	public float pCross_uniform;
	/** Para metodo de MUTACION NO UNIFORME solamente. Decaimiento de la robabilidad de mutacion */
	public float c_unUniform;
	/** Para metodo de MUTACION NO UNIFORME solamente. Cantidad de generaciones tras cada decaimeiento */
	public float n_unUniform;
	/** Para metodo de CORTE POR ERROR MINIMO solamente. Error minimo LMS a alcanzar para la condicion de corte */
	public float minError_breakCriteria;
	
	public Chromosome[] population;
	
	public BreakCriteria 		breakCriteria;		// Metodo de corte
	public CandidateSelector 	selectionMethod;	// Metodo de seleccion
	public CrossoverMethod 		crossoverMethod;	// Metodo de crossover
	public MutationMethod		mutationMethod;		// Metodo de mutacion
	public CandidateSelector 	replaceMethod;		// Metodo de reemplazo
	
	/** Configuracion de las variables a usar para la red */
	public NetConfiguration netConfig;

	/**
	 * Crea una nueva configuracion y la inicizliza co valores iniciales para
	 * facilitar su contruccion
	 */
	public Configuration() {
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		// Seteo de metodos a utilizar
		breakCriteriaType 	= BreakCriteriaType.MAX_GEN;
		selectionType 		= SelectorType.ELITE;
		crossOverType 		= CrossoverType.CLASICO;
		mutationType		= MutationType.MUTATION_CLASICO;
		replacementType 	= SelectorType.ELITE;
		
		N = 20;
		G = 0.8f;
		maxGen = 100;
		pMutate = 0.02f;
		cp = 0.7f;
		backpropp = 0.05f;
		pCross_uniform = 0.4f;
		ke_mixted = (int) (N * 0.5f);
		// Set up net configuration
		netConfig = new NetConfiguration();
		netConfig.structure = new int[] {2, 15, 15, 1};
		netConfig.epochs = 200;
		netConfig.p = 0.7f;
		netConfig.eta = 0.1f;
		netConfig.f = new TanhFunction(0.5f);
		
		c_unUniform = 0.9f;
		n_unUniform = 20;
		
		minError_breakCriteria = 0.0002f;
	}
	
	public void initialize() throws IOException {
		breakCriteria 	= breakCriteriaType.getInstance(this);
		selectionMethod = selectionType.getInstance(this);			
		crossoverMethod = crossOverType.getInstance(this);
		mutationMethod 	= mutationType.getInstance(this);
		replaceMethod 	= replacementType.getInstance(this);
		elapsedGen = 0;
		// set up net configuration
		netConfig.initialize();
		// normalize examples!
		if (netConfig.f instanceof TanhFunction) {
			ExamplesUtils.normalizeTanh(netConfig.allexamples);
		} else if (netConfig.f instanceof SigmoidFunction) {
			ExamplesUtils.normalizeSigmoid(netConfig.allexamples);
		} else {
			System.out.println("Warning: examples could not be normalized!");
		}
	}
	
}
