
public class Testing {

	public static void main(String[] args) {
		BitField test;
		BitField expected;
		
		
		System.out.println(true ^ false ^ false);
		System.out.println(true ^ true ^ false);
		System.out.println(true ^ true ^ true);
		
		System.out.println(true && true && true);
		System.out.println(false && false && false);
		
		
		BitField a = new BitField("1111");
		BitField b = new BitField("1111");
		BitField[] out = BinaryCalculator.divide(a, b);
		System.out.println("Quotient "+out[0]);
		System.out.println("Remainder "+out[1]);
		
		
		a = new BitField("1011010");
		b = new BitField("1101100");
		out = BinaryCalculator.divide(a, b);
		System.out.println("Quotient "+out[0]);
		System.out.println("Remainder "+out[1]);
		
		
		//System.out.println(BinaryCalculator.subtract(new BitField("00000111"), new BitField("00100000")));
		
		
		

	}

}
