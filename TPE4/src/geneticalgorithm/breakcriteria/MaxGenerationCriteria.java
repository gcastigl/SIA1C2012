package geneticalgorithm.breakcriteria;

import geneticalgorithm.Configuration;

public class MaxGenerationCriteria extends BreakCriteria {

	public MaxGenerationCriteria(Configuration config) {
		super(config);
		System.out.println("\tGeneraciones: " + config.maxGen);
	}

	@Override
	public boolean isFinished() {
		return config.elapsedGen > config.maxGen;
	}
	
}
