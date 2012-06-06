package geneticalgorithm.mutation;

import geneticalgorithm.Configuration;

public enum MutationType {
	MUTATION_CLASICO {
		@Override
		public MutationMethod getInstance(Configuration config) {
			return new ClasicMutation(config);
		}
	},
	MUTATION_NO_UNIFORME {
		@Override
		public MutationMethod getInstance(Configuration config) {
			return new UnUniformMutation(config);
		}
	};
	
	public abstract MutationMethod getInstance(Configuration config);
}
