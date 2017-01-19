/**
 * 
 */
package net.huraki.tss.utils;

/**
 * @author Raphael Huber
 *
 */
public class TssConstants {
	
	// MQTT given constants
	public final static int MAX_TOPIC_BYTELENGTH = 65535; // [MQTT-4.7.3-3]
	
	//Return codes
	public final static byte ACCEPTED = 0X00;
	
	
	
	/**
	 * Indicates that there is no short topic and so the length is 0. Represented
	 * as one {@code byte}: {@code 0x00}
	 */
	public final static byte REG_SHORT_TOPIC_LENGTH = 0x00;
	
	/**
	 * A {@code RegisterMessage} has a maximal length of {@code 131074} bytes.
	 */
	public final static int REG_MSG_MAX_LENGTH = 131074;
	
	// Register message
	public final static byte REG_MSG_TYPE = 0x0A;
	
	//RegAck message
	public final static byte REGACK_MSG_TYPE = 0x0B;

	//Connect message
	public final static byte CONNECT_MSG_TYPE = 0x04;
	
	//ConAck message
	public final static byte CONACK_MSG_TYPE = 0x05;
	
	//Disconnect message
	public final static byte DISCONNECT_MSG_TYPE = 0x18;

}
