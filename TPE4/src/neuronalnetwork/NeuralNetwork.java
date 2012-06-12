package neuronalnetwork;

import geneticalgorithm.Configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.List;

import main.ExamplesUtils;
import neuronalnetwork.function.TransferFunction;
public class NeuralNetwork {

	private Layer[] layers;
	
	public NeuralNetwork() {
	}
	
	/**
	 * Crea una red con input de largo structure[0].
	 * Todos los demas valores que se encuentren entre 1 y structure.length - 1 
	 * son creados como capas ocultas.
	 */
	public NeuralNetwork(int[] structure) {
		if (structure.length < 2) {
			throw new IllegalArgumentException("Net must have at least one layer");
		}
		layers = new Layer[structure.length - 1];
		for (int i = 1; i < structure.length; i++) {
			layers[i - 1] = new Layer(structure[i - 1], structure[i]);
		}
	}
	
	public NeuralNetwork(Layer[] layers) {
		setLayers(layers);
	}
	
	public void setLayers(Layer[] layers) {
		this.layers = layers;
	}
	
	public float[] evaluate(float[] input, TransferFunction f) {
		float[] aux = input;
		for(Layer l: layers) {
			aux = l.evaluate(aux, f);
		}
		return aux;
	}
	
	public Layer getLayer(int index) {
		return layers[index];
	}
	
	public Layer[] getLayers() {
		return layers;
	}
	
	public int getTotalLayers() {
		return layers.length;
	}

	public boolean equals(NeuralNetwork net) {
		if (layers.length != net.layers.length) {
			return false;
		}
		for (int i = 0; i < layers.length; i++) {
			if (!layers[i].equals(net.layers[i])) {
				return false;
			}
		}
		return true;
	}
	
	public boolean persist(String fileName, Configuration config){
		try{
			FileOutputStream fstream = new FileOutputStream(fileName);
			int connections = NetSerializer.totalConnections(config.netConfig.structure);
			float[] weights = new float[connections];
			NetSerializer.toArray(layers, weights);
			for( int i = 0 ; i < connections ; i ++){
				int a = Float.floatToIntBits(weights[i]);
				fstream.write(new byte[] {
			            (byte)(a >>> 24),
			            (byte)(a >>> 16),
			            (byte)(a >>> 8),
			            (byte)a});
				

			}
			fstream.close();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static NeuralNetwork recover(String filename, Configuration config){
		try{
			FileInputStream fstream = new FileInputStream(filename);
			int connections = NetSerializer.totalConnections(config.netConfig.structure);
			float[] weights = new float[connections];
			for( int i = 0 ; i < connections ; i ++){
				byte[] b = new byte[4];
				fstream.read(b, 0, 4);
				ByteBuffer bb = ByteBuffer.allocate(4);
				bb.put(b[0]);
				bb.put(b[1]);
				bb.put(b[2]);
				bb.put(b[3]);
				int a = bb.getInt(0);
				weights[i] = Float.intBitsToFloat(a);
			}
			return NetSerializer.fromArray(config.netConfig.structure, weights).createIndividual();
		}
		catch(Exception e){
			return null;
		}
	}
}
