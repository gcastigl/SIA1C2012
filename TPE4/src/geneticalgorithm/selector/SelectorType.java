package geneticalgorithm.selector;

import geneticalgorithm.Configuration;

public enum SelectorType {

	ELITE {
		@Override
		public CandidateSelector getInstance(Configuration config) {
			return new EliteSelector(config);
		}
	}, 
	BOLTZMAN {
		@Override
		public CandidateSelector getInstance(Configuration config) {
			return new BoltzmanSelector(config);
		}
	}, 
	UNIVERSAL {
		@Override
		public CandidateSelector getInstance(Configuration config) {
			return new UniversalSelector(config);
		}
	}, 
	RULETA {
		@Override
		public CandidateSelector getInstance(Configuration config) {
			return new RouletteSelector(config);
		}
	}, 
	MIXED_BOLTZMAN {
		@Override
		public CandidateSelector getInstance(Configuration config) {
			CandidateSelector s2 = new BoltzmanSelector(config);
			return new MixtedSelector(config, s2);
		}
	},
	MIXED_RULETA {
		@Override
		public CandidateSelector getInstance(Configuration config) {
			CandidateSelector s2 = new RouletteSelector(config);
			return new MixtedSelector(config, s2);
		}
	},
	TURNAMENT {
		@Override
		public CandidateSelector getInstance(Configuration config) {
			return new TournamentSelector(config);
		}
	};
	
	public abstract CandidateSelector getInstance(Configuration config);
}
