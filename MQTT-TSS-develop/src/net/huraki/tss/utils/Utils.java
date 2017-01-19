/**
 * 
 */
package net.huraki.tss.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Raphael Huber
 *
 */
public class Utils {
	
	
	
	public static byte readMessageType(ByteArrayInputStream in) {
        byte messageType = (byte) in.read();
        return messageType;
    }
	
	/**
	 * Returns the message ID in binary representation
	 * 
	 * @param msgIdInt the message ID
	 * @return the message ID encoded into a byte array with a length of 2
	 */
	public static byte[] encodeMsgIdToByte(int msgIdInt) {
		byte[] msgId = new byte[2];
		msgId[0] = (byte) (msgIdInt >> 8); 
		msgId[1] = (byte) (msgIdInt >> 0);
		return msgId;
		
	}
	
	/**
	 * Returns the message ID as a number ({@code int})
	 * 
	 * @param in A ByteArrayInputStream from which the message ID will be build
	 * @return the message ID as a {@code int}
	 */
	public static int decodeMsgId(ByteArrayInputStream in){
		int first = (in.read() << 8) & 0xFF00;
		int second = (in.read() << 0) & 0x00FF;
		return first + second;
	}
	
	
	/**
     * Returns the number of bytes to encode the length value
     */
	public static int numOfBytesToEncode(int length) {
		
		if (0 <= length && length <= 127) return 1;
        if (128 <= length && length <= 16383) return 2;
        if (16384 <= length && length <= 131074) return 3;
        throw new IllegalArgumentException("value should be in the range [0..131074]");
		
	}
	
    public static ByteArrayOutputStream encodeLength(int value) {
        ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        byte digit;
        do {
            digit = (byte) (value % 128);
            value = value / 128;
            // if there are more digits to encode, set the top bit of this digit
            if (value > 0) {
                digit = (byte) (digit | 0x80);
            }
            encoded.write(digit);
        } while (value > 0);
        
        return encoded;
    }
    
    public static ByteArrayOutputStream addLengthField(ByteArrayOutputStream payload){
    	
    	int payloadLength = payload.size();
		int lengthField = numOfBytesToEncode(payloadLength);
		int length = lengthField + payloadLength;
		ByteArrayOutputStream encoded = new ByteArrayOutputStream();
		
		try {
			encodeLength(length).writeTo(encoded);
			payload.writeTo(encoded);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encoded;
		
    }
    
    /**
     * Decode the variable remaining length as defined in MQTT v3.1 specification 
     * (section 2.1).
     * 
     * @return the decoded length or -1 if needed more data to decode the length field.
     */
    public static int decodeRemainingLenght(ByteArrayInputStream in) {
        int multiplier = 1;
        int value = 0;
        byte digit;
        do {
            if (in.available() < 1) {
                return -1;
            }
            digit = (byte) in.read();
            value += (digit & 0x7F) * multiplier;
            multiplier *= 128;
        } while ((digit & 0x80) != 0);
        return value;
    }
    
    public static int decodePayloadLenght(DataInputStream dis) throws IOException{
    	byte digit;
		int bytesToRead = 0;
		int multiplier = 1;
		int lengthField = 0;
		
		dis.mark(3);
		do {
            digit = dis.readByte();
            lengthField++;
            bytesToRead += (digit & 0x7F) * multiplier;
            multiplier *= 128;
        } while ((digit & 0x80) != 0);
		
		//System.out.println("Bytes to read:" + bytesToRead);
		System.out.println("Utils: LengthField is " + lengthField + " byte long");
		dis.reset();
		dis.skipBytes(lengthField);

		return bytesToRead-lengthField;
    }

}
