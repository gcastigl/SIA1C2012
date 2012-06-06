package geneticalgorithm.breakcriteria;

import geneticalgorithm.Configuration;

public abstract class BreakCriteria {

	protected Configuration config;
	
	public BreakCriteria(Configuration config) {
		this.config = config;
		System.out.println("Break criteria: " + getClass().getSimpleName());
	}
	
	public abstract boolean isFinished();

}
