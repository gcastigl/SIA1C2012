import hopfield.test.AsynchHopfieldTest;

public class Main {
	
	public static void main(String[] args) {
		AsynchHopfieldTest asynchTest = new AsynchHopfieldTest();
		boolean testStatus = asynchTest.reconocerPatronConRuido();
		System.out.println(testStatus);
	}
	
}
