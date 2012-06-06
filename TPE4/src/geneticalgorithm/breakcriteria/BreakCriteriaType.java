package geneticalgorithm.breakcriteria;

import geneticalgorithm.Configuration;

public enum BreakCriteriaType {
	ENT_ERROR {
		@Override
		public BreakCriteria getInstance(Configuration config) {
			return new MinErrorBreakCriteria(config);
		}
	}, MAX_GEN {
		@Override
		public BreakCriteria getInstance(Configuration config) {
			return new MaxGenerationCriteria(config);
		}
	}, STRUCTURE {
		@Override
		public BreakCriteria getInstance(Configuration config) {
			return new StructureBreakCriteria(config);
		}
	};
	
	public abstract BreakCriteria getInstance(Configuration config);
}
