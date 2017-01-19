/**
 * 
 */
package net.huraki.tss.messages;

import net.huraki.tss.utils.TssConstants;

/**
 * @author Raphael Huber
 *
 */
public abstract class AbstractMessage {
	
	public static final byte CONNECT = TssConstants.CONNECT_MSG_TYPE;
	public static final byte CONNACK = TssConstants.CONACK_MSG_TYPE;
	public static final byte REGISTER = TssConstants.REG_MSG_TYPE;
	public static final byte REGACK = TssConstants.REGACK_MSG_TYPE;
	public static final byte DISCONNECT = TssConstants.DISCONNECT_MSG_TYPE;
	
	protected byte messageType;
	
	public byte getMessageType() {
        return messageType;
    }
}
