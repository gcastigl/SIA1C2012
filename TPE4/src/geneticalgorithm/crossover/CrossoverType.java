package geneticalgorithm.crossover;

import geneticalgorithm.Configuration;

public enum CrossoverType {

	CLASICO {
		@Override
		public CrossoverMethod getInstance(Configuration config) {
			return new Clasic(config);
		}
	},
	ANULAR {
		@Override
		public CrossoverMethod getInstance(Configuration config) {
			return new Anular(config);
		}
	},
	MULTIPLE {
		@Override
		public CrossoverMethod getInstance(Configuration config) {
			return new Multiple(config);
		}
	},
	UNIFORME_PARAMETRIZADO {
		@Override
		public CrossoverMethod getInstance(Configuration config) {
			return new UniformParametrizied(config);
		}
	}; 
	
	public abstract CrossoverMethod getInstance(Configuration config);
}
