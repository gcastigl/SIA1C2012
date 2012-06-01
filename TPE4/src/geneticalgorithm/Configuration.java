package geneticalgorithm;

import geneticalgorithm.crossover.CrossoverMethod;
import geneticalgorithm.selector.CandidateSelector;

public class Configuration {

	public static final String SELECTOR_ELITE = "elite";
	public static final String SELECTOR_RULETA = "ruleta";
	public static final String SELECTOR_UNIVERSAL = "universal";
	public static final String SELECTOR_BOLTZMAN = "boltzman";
	public static final String SELECTOR_MIXTO = "mixto";
	
	public static final String CROSSOVER_CLASICO = "clasico";
	public static final String CROSSOVER_MULTIPLE = "multiple";
	public static final String CROSSOVER_UNIFORME = "uniforme";
	public static final String CROSSOVER_ANULAR = "anular";
	
	/* Nombres de los metodos de seleccion, corrover y reemplazo elejidos */
	public String selectionType;
	public String crossOverType;
	public String replacementType;
	
	public int N;		// Poblacion
	public float G;	// Brecha generacional
	public int maxGen;	// Numero maximo de generaciones
	public float mp;	// Probabilidad de mutacion
	public float cp;	// Probabilidad de cruce
	// Para metodo MIXTO: Si candidateSelector.lenght == 2 => cantidad de individuos utilizando Elite
	// Elite =  candidateSelector[0];
	public int ke;
	public Chromosome[] population;
	
	public CandidateSelector[] selectionMethod;	// Metodo de seleccion
	public CrossoverMethod crossoverMethod;		// Metodo de crossover
	public CandidateSelector replaceMethod;		// Metodo de reemplazo
	
	public boolean isMixtedSet() {
		return SELECTOR_MIXTO.equals(selectionType);
	}
}
