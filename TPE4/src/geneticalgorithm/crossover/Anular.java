package geneticalgorithm.crossover;

import neuronalnetwork.NetSerializer;
import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

public class Anular extends CrossoverMethod {

	public Anular(Configuration config) {
		super(config);
	}

	@Override
	protected void cross(Chromosome c1, Chromosome c2) {
		int totalConnections = NetSerializer.totalConnections(c1.getLayers());
		int p = (int) (Math.random() * totalConnections);
		int l = p + (int) (Math.random() * (totalConnections / 2));
		childs[0] = anularCross(c1, c2, totalConnections, p, l);
		childs[1] = anularCross(c2, c1, totalConnections, p, l);
	}

	private Chromosome anularCross(Chromosome c1, Chromosome c2, int totalConnections, int from, int to) {
		NetSerializer.toArray(c1.getLayers(), netAsArray);
		to = to%totalConnections;
		from = from%totalConnections;
		if (from <= to) {
			NetSerializer.toArray(c2.getLayers(), netAsArray, from, to);
		} else {
			NetSerializer.toArray(c2.getLayers(), netAsArray, from);
			NetSerializer.toArray(c2.getLayers(), netAsArray, 0, to);
		}
		return buildChormosome();
	}
}
