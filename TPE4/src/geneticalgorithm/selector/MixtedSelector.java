package geneticalgorithm.selector;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

public class MixtedSelector extends CandidateSelector {
	
	private EliteSelector elite;
	private CandidateSelector s2;
	
	public MixtedSelector(Configuration config, CandidateSelector s2) {
		super(config);
		if (s2 == null) {
			throw new IllegalArgumentException("Invalid second selector");
		}
		elite = new EliteSelector(config);
		this.s2 = s2;
		System.out.println("ke_mixted: " + config.ke_mixted);
	}

	@Override
	public int[] select() {
		int[] bests1 = elite.selectBestK(config.ke_mixted);
		int[] bests2 = s2.select();
		int[] selected = new int[(int) (config.N * config.G)];
		int i = 0;
		// choose all k_e candidates selected by the elite selector
		for (; i < bests1.length; i++) {
			selected[i] = bests1[i];
		}
		// choose the first #childs - k_e candidates selected by the other selector
		for (int index = 0; i < selected.length; i++) {
			selected[i] = bests2[index++];
		}
		return selected;
	}

	@Override
	public void replace(Chromosome[] childs) {
		int[] eliteSelection = elite.selectBestK(config.ke_mixted);
		int neededToCompleteN = config.N - config.ke_mixted - childs.length;
		int[] otherSelection = s2.selectBestK(neededToCompleteN);
		int[] allselected = new int[eliteSelection.length + otherSelection.length];
		System.arraycopy(eliteSelection, 0, allselected, 0, eliteSelection.length);
		System.arraycopy(otherSelection, 0, allselected, eliteSelection.length, otherSelection.length);
		createNewPopulation(allselected, childs);
	}

}
