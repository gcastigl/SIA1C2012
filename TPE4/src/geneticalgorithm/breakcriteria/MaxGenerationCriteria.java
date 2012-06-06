package geneticalgorithm.breakcriteria;

import geneticalgorithm.Configuration;

public class MaxGenerationCriteria extends BreakCriteria {

	public MaxGenerationCriteria(Configuration config) {
		super(config);
	}

	@Override
	public boolean isFinished() {
		return config.elapsedGen > config.maxGen;
	}
	
}
