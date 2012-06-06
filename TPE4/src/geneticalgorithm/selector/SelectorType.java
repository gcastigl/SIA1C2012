package geneticalgorithm.selector;

import geneticalgorithm.Configuration;

public enum SelectorType {

	ELITE {
		@Override
		public CandidateSelector getInstance(Configuration config) {
			return new EliteSelector(config);
		}
	}, 
	RULETA {
		@Override
		public CandidateSelector getInstance(Configuration config) {
			return new RouletteSelector(config);
		}
	}, 
	MIXED {
		@Override
		public CandidateSelector getInstance(Configuration config) {
			CandidateSelector s2 = config.mixtedSelectionType.getInstance(config);
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
