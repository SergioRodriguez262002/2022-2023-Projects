/**
 * Class with methods to implement the basic arithmetic operations by operating
 * on bit fields.
 *
 * This is the skeleton code form COMP2691 Assignment #2.
 */
public class BinaryCalculator {
	public static BitField add(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		BitField result = new BitField(a.size());

		/*
		 * Boolean A is the bit at position i, B is the bit at position i R is the
		 * result bit to be placed at result bitfield at bit i C is the carry bit
		 * 
		 * The loop will loop through every bit in bitfields a and b.
		 */
		boolean A, B, R = false, C = false;
		for (int i = 0; i < a.size(); i++) {

			A = a.get(i);
			B = b.get(i);
			/*
			 * case 1 all false then all false or all true then all true ABC RC 000 00 111
			 * 11
			 */
			if (A && B && C || (!A && !B && !C)) {
				R = A;
				C = A;
			} else

			/*
			 * Case 2 if only a or b is true then the result is true ABC RC 100 10 010 10
			 * 001 10
			 */
			if ((A ^ B ^ C) && !(A && B && C)) {
				R = true;
				C = false;
			} else
			/*
			 * If only a or b is true and c is true then carry is true ABC RC 101 01 011 01
			 */

			if (((A ^ B) && C)) {
				R = false;
				C = true;
			} else
			/*
			 * if and only if a and b and not c then only carry is true ABC RC 110 01
			 */
			if (((A && B) && !C)) {
				R = false;
				C = true;
			}
			result.set(i, R);

		}
		return result;

		// for testing
		/*
		 * System.out.println("");
		 * System.out.println(" Case 4 "+toBit(A)+" "+toBit(B)+" "+toBit(Cb)+" = R "
		 * +toBit(R)+" C "+toBit(C));
		 */
	}

	public static BitField subtract(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		// Subtraction is done by adding the negative
		BitField result = twoComplement(b); // Negative of b stored in result
		result = add(a, result); // Adding a and the twos complement of b
		return result;
	}

	public static BitField multiply(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}

		// The product multiplier and multiplicand need to be doubled in size.
		// a is stored in the multiplier and is left padded by its size.
		// b is stored in the multiplicand and is left padded by its size.
		BitField prod = new BitField(a.size() * 2);
		BitField mult = leftPadding(a, a.size());
		BitField mcand = leftPadding(b, b.size());

		for (int i = 0; i < a.size(); i++) {
			// For the number of bits in the bitfields, if msb of the multiplier is 1 then
			// add the product to the multiplicand
			if (mult.getMSB()) {
				prod = add(prod, mcand);
			}
			// Else shift multiplicand right once and the multiplier left once.
			mcand = RSR(mcand, 1);
			mult = LSL(mult, 1);
		}
		prod = RSR(prod, 1); // I don't know why the result needs to be right shifted one more time
		prod = trimMSB(prod, a.size()); // Chopping off the first half of the product

		return prod;
	}

	public static BitField[] divide(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}

		// If the divisor is zero then just return null;
		if (b.toLongSigned() == 0) {
			return null;
		}

		// Flags determining if the divisor and remainder are negative.
		boolean aneg = false;
		boolean bneg = false;
		if (a.getMSB()) {
			// if a is negative then run twos complement to make positive.
			a = twoComplement(a);
			aneg = true;
		}
		if (b.getMSB()) {
			// if b is negative then run twos complement to make positive.
			b = twoComplement(b);
			bneg = true;
		}

		// Create double fields of the quotient remainder and divisor.
		BitField quotient = new BitField(a.size() * 2);
		// Divisor is b left padded by its size to make it double the length of bits.
		BitField divisor = leftPadding(b, b.size());
		// starts as the dividend left padded by its length and is stored in the
		// remainder, ends off being remainder
		BitField remainder = rightPadding(a, a.size());
		for (int i = 0; i < a.size() + 1; i++) {
			// the divisor will always be subtracted from the remainder.
			remainder = subtract(remainder, divisor);
			if (remainder.getMSB()) {
				// if the remainder is negative then reverse the subtraction by adding the
				// divisor back.
				// left shift quotient to mark it.
				remainder = add(remainder, divisor);
				quotient = LSL(quotient, 1);
			} else {
				// Else left shift quotient and mark lsb to a one.
				quotient = LSL(quotient, 1);
				quotient.set(0, true);
			}
			// Divisor will always be shifted right one.
			divisor = RSR(divisor, 1);
		}

		if (aneg ^ bneg) {
			// If a or b started as negative then the quotient is negative. Therefore
			// perform a
			// twosComplement on the quotient.
			quotient = twoComplement(quotient);
		}
		if (aneg && bneg) {
			// If a and b started as negative then the remainder is positive. Therefore
			// perform a
			// twosComplement on the remainder.
			remainder = twoComplement(remainder);
		}
		if (aneg && !bneg) {
			// If a and not b started as negative then the remainder is positive. Therefore
			// perform a
			// twosComplement on the remainder.
			remainder = twoComplement(remainder);
		}

		// Trimming the first half of the bitfield to get back to original size.
		quotient = trimMSB(quotient, a.size());
		remainder = trimMSB(remainder, a.size());

		// Return both the quotient and the remainder
		BitField[] out = new BitField[2];
		out[0] = quotient; // quotient
		out[1] = remainder; // remainder
		return out;
	}

	public static BitField RSR(BitField a, int shift) {
		// right shift of all bits.
		BitField result = new BitField(a.size());
		for (int i = 0; i < shift; i++) {
			// The outer loop peforms the shift n times according to param shift.
			for (int j = 0; j < a.size() - 1; j++) {
				// The inner loop sets the bit of empty bitfield result to the bit of a at
				// position j. J goes from length of a to 0.
				result.set(j, a.get(j + 1));
			}
		}
		return result;
	}

	public static BitField LSL(BitField a, int shift) {
		// Left shift of all bits.
		BitField result = new BitField(a.size());
		for (int i = 0; i < shift; i++) {
			// The outer loop peforms the shift n times according to param shift.
			for (int j = 0; j < a.size() - 1; j++) {
				// The inner loop sets the next bit of empty bitfield result to the bit of a at
				// position j.
				result.set(j + 1, a.get(j));
			}
		}
		return result;
	}

	public static BitField absoluteValue(BitField a) {
		// If a is negative and the msb is 1 then return twos complement of a, will be
		// returned as a new object. If a is positive then just return a.
		if (a.getMSB()) {
			return twoComplement(a);
		}
		return a;
	}

	public static BitField complement(BitField a) {
		// NOT 2S COMPLEMENT, returns new bitfield with every bit of a flipped.
		BitField result = new BitField(a.size());
		for (int i = 0; i < a.size(); i++) {
			result.set(i, !a.get(i));
		}
		return result;
	}

	public static BitField twoComplement(BitField a) {
		// Creates a new bitfield and stores the complement of a in it. Then a new
		// bitfield of value one is created and added to the final result.
		BitField result = complement(a);
		BitField add1 = new BitField(a.size());
		add1.set(0, true);
		result = add(result, add1);
		return result;
	}

	public static BitField leftPadding(BitField a, int size) {
		// This method returns a new bitfield of length a plus size copies a and pads
		// the left side with zeros
		BitField result = new BitField(a.size() + size);
		for (int i = a.size() - 1; i >= 0; i--) {
			result.set(i + size, a.get(i));
		}
		return result;
	}

	public static BitField rightPadding(BitField a, int size) {
		// This method returns a new bitfield of length a plus size copies a and pads
		// the right side with zeros
		BitField result = new BitField(a.size() + size);
		for (int i = 0; i < a.size(); i++) {
			result.set(i, a.get(i));
		}
		return result;
	}

	public static BitField trimMSB(BitField a, int n) {
		// Returns a new bitfield with the most significant bits cut according to n
		BitField result = new BitField(a.size() - n);
		for (int i = 0; i < a.size() - n; i++) {
			result.set(i, a.get(i));
		}
		return result;
	}

	public static char toBit(boolean a) {
		// Prints the bit
		if (a) {
			return '1';
		}
		return '0';
	}

}
