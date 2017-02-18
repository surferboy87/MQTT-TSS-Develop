/**
 * 
 */
package net.huraki.testing;

import java.io.UnsupportedEncodingException;

import net.huraki.tss.utils.Base90Converter;

/**
 * @author Raphael
 *
 */
public class Base90ConverterTester {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		

		long[] values = {0l, 1234l, 745639573l, 987654321123l, Long.MAX_VALUE};
		
		Base90Converter bc = new Base90Converter();
		
		for(long v : values){
			System.out.println("-------------------------------------------------");
			System.out.println("Maximum value = " + v);
			
			System.out.println();
			String shortTopic = bc.getShortTopic(v);
			System.out.println("encode to shortTopic string representation: " + shortTopic);
			
			System.out.println();
			System.out.println("Byte representation of " + shortTopic);
			byte[] by = shortTopic.getBytes("UTF-8");
			for( byte b : by){
				System.out.print(b + " ");
			}
			System.out.println();
			System.out.println();
			
			System.out.println("Decode string representation to number value: ");
			System.out.println(shortTopic + " = " + bc.getNumericRepresentation(shortTopic));
			System.out.println("-------------------------------------------------");
		}
		
		
		
		

	}

}
