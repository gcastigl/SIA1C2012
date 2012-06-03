package main;

import neuronalnetwork.NetSerializer;
import neuronalnetwork.NeuralNetwork;

public class NetSerializerTest {

	public static void main(String[] args) {
		int[] structure = new int[] {2, 5, 5, 3};
		NeuralNetwork net = new NeuralNetwork(structure);
		float[] array = new float[NetSerializer.totalConnections(structure)];
		NetSerializer.toArray(net.getLayers(), array, 0);
		NeuralNetwork net2 = NetSerializer.fromArray(structure, array).createIndividual();
		System.out.println(net.equals(net2));
	}
}
