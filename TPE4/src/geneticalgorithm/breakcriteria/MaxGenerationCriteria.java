package geneticalgorithm.breakcriteria;

import geneticalgorithm.Configuration;

public class MaxGenerationCriteria extends BreakCriteria {

	private int elapsedGenerations;
	
	public MaxGenerationCriteria() {
		elapsedGenerations = 0;
	}

	@Override
	public boolean isFinished(Configuration config) {
		return elapsedGenerations++ > config.maxGen;
	}
	
	
}
