package neuronalnetwork.function;

public class TanhFunction extends TransferFunction {

	private float p;
	
	public TanhFunction() {
		this(0.5f);
	}
	
	public TanhFunction(float p) {
		this.p = p;
	}

	@Override
	public float valueAt(float x) {
		return (float) Math.tanh(p * x);
	}

	@Override
	public float valueAtDerivated(float x) {
		float fx = valueAt(x);
		return p * (1 - fx * fx);
	}
}
