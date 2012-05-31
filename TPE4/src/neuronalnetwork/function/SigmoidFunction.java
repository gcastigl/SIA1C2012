package neuronalnetwork.function;

public class SigmoidFunction extends TransferFunction {

	private float p;
	
	public SigmoidFunction() {
		this(0.5f);
	}
	
	public SigmoidFunction(float p) {
		setP(p);
	}
	
	public void setP(float p) {
		this.p = p;
	}
	
	public float valueAt(float x) {
		return (float) (1 / (1 + Math.exp(-x * p)));
	}
	
	public float valueAtDerivated(float x) {
		float y = valueAt(x); 
		return p * y * (1 - y); 
	}
}
