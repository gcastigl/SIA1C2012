package geneticalgorithm;

import geneticalgorithm.breakcriteria.BreakCriteria;
import geneticalgorithm.breakcriteria.MaxGenerationCriteria;
import geneticalgorithm.crossover.Clasic;
import geneticalgorithm.crossover.CrossoverMethod;
import geneticalgorithm.selector.CandidateSelector;
import geneticalgorithm.selector.EliteSelector;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import neuronalnetwork.NetConfiguration;

public class Configuration {
	
	private static Map<String, BreakCriteria> breakCriteriaMethods;
	private static Map<String, CandidateSelector> selectorMethods;
	private static Map<String, CrossoverMethod> crossoverMethods;
	
	public static final String SELECTOR_ELITE 		= "elite";
	public static final String SELECTOR_RULETA 		= "ruleta";
	public static final String SELECTOR_UNIVERSAL 	= "universal";
	public static final String SELECTOR_BOLTZMAN 	= "boltzman";
	public static final String SELECTOR_MIXTO 		= "mixto";
	
	public static final String CROSSOVER_CLASICO	= "clasico";
	public static final String CROSSOVER_MULTIPLE 	= "multiple";
	public static final String CROSSOVER_UNIFORME 	= "uniforme";
	public static final String CROSSOVER_ANULAR 	= "anular";
	
	public static final String BREAKCRITERIA_MAX_GEN 	= "maxGen";
	public static final String BREAKCRITERIA_ESTRUCTURA = "estructura";
	public static final String BREAKCRITERIA_CONTENIDO 	= "contenido";
	public static final String BREAKCRITERIA_ENT_OPTIMO = "entOptimo";
	
	public String selectionType;
	public String crossOverType;
	public String replacementType;
	public String breakCriteriaType;
	
	public int N;				// Poblacion
	public float G;				// Brecha generacional
	public int maxGen;			// Numero maximo de generaciones
	public float mp;			// Probabilidad de mutacion
	public float cp;			// Probabilidad de cruce
	public int elapsedGen;
	// Para metodo MIXTO solamente. Cantidad de individuos utilizando Elite
	public int ke_mixted;
	// Para metodo Elite solamente. Cantidad de individuos mas aptos a seleccionar.
	public int k_elite;
	
	public Chromosome[] population;
	
	public BreakCriteria 		breakCriteria;		// Metodo de corte
	public CandidateSelector 	selectionMethod;	// Metodo de seleccion
	public CrossoverMethod 		crossoverMethod;	// Metodo de crossover
	public CandidateSelector 	replaceMethod;		// Metodo de reemplazo
	
	public NetConfiguration netConfig;
	
	
	public void initialize() throws IOException {
		initMethods(this);
		breakCriteria = breakCriteriaMethods.get(breakCriteriaType);
		selectionMethod = selectorMethods.get(selectionType);			
		crossoverMethod = crossoverMethods.get(crossOverType);
		replaceMethod = selectorMethods.get(replacementType);
		elapsedGen = 0;
		netConfig.initialize();
	}

	private static void initMethods(Configuration instance) {
		// Break criteria
		breakCriteriaMethods= new HashMap<String, BreakCriteria>();
		breakCriteriaMethods.put(Configuration.BREAKCRITERIA_MAX_GEN, new MaxGenerationCriteria());
		// Candidate selector + replacement
		selectorMethods = new HashMap<String, CandidateSelector>();
		selectorMethods.put(Configuration.SELECTOR_ELITE, new EliteSelector(instance));
		// Crossover
		crossoverMethods = new HashMap<String, CrossoverMethod>();
		crossoverMethods.put(Configuration.CROSSOVER_CLASICO, new Clasic(instance));
	}

}
