package neuronalnetwork.function;


public class SgFunction extends TransferFunction {

	@Override
	public float valueAt(float x) {
		return x >= 0 ? 1 : -1;
	}

	@Override
	public float valueAtDerivated(float x) {
		throw new IllegalStateException("Sg function does not have derivate function");
	}

}
