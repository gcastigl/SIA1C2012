package geneticalgorithm.breakcriteria;

import geneticalgorithm.Configuration;

public abstract class BreakCriteria {

	protected Configuration config;
	
	public BreakCriteria(Configuration config) {
		this.config = config;
	}
	
	public abstract boolean isFinished();

}
