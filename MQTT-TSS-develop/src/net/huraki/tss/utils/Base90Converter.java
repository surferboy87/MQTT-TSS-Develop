package net.huraki.tss.utils;

import java.math.BigDecimal;

public class Base90Converter {

	private final String alphabet;
	private final long base;
	
	public Base90Converter(){
		this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"%&'()*,-.:;<=>?@[\\]^_`{|}~";
		this.base = this.alphabet.length();
	}
	
	public String getShortTopic(long number) {
		return encodeToBase90(number);
	}

	public long getNumericRepresentation(String str) {
		return decodeFromeBase90(str);
	}

	private String encodeToBase90(long number) {

		long quotient = number / base;
		int remainder = (int) (number % base);

		// Recursive
		if (quotient == 0) { // base case
			return "" + this.alphabet.charAt(remainder);
		} else {
			return encodeToBase90(quotient) + this.alphabet.charAt(remainder);
		}
	}

	
	private long decodeFromeBase90(String str) {
		
		//Recursive
		if (str.length() == 1) {
			return getNumberValue(str.charAt(0), str.length() - 1);
		} else {
			return decodeFromeBase90(str.substring(1)) + getNumberValue(str.charAt(0), str.length() - 1);
		}

	}


	private long getNumberValue(char c, int pow) {

		BigDecimal b1 = new BigDecimal(Integer.valueOf(alphabet.indexOf(c)));
		BigDecimal b2 = new BigDecimal(base);
		BigDecimal b4 = b1.multiply(b2.pow(pow));

		return b4.longValueExact();
	}

}
