package geneticalgorithm.breakcriteria;

import geneticalgorithm.Configuration;

public class MaxGenerationCriteria extends BreakCriteria {

	@Override
	public boolean isFinished(Configuration config) {
		return config.elapsedGen > config.maxGen;
	}
	
}
