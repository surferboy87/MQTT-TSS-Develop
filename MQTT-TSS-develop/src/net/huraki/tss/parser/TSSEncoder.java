/**
 * 
 */
package net.huraki.tss.parser;

import java.util.HashMap;

import net.huraki.tss.messages.AbstractMessage;

/**
 * @author Raphael Huber
 *
 */
public class TSSEncoder {

	private HashMap<Byte, AbstractEncoder> encoderMap = new HashMap<Byte, AbstractEncoder>();
	
	public TSSEncoder(){
		this.encoderMap.put(AbstractMessage.CONNECT, new ConnectEncoder());
		this.encoderMap.put(AbstractMessage.CONNACK, new ConAckEncoder());
		this.encoderMap.put(AbstractMessage.REGISTER, new RegisterEncoder());
		this.encoderMap.put(AbstractMessage.REGACK, new RegAckEncoder());
		this.encoderMap.put(AbstractMessage.DISCONNECT, new DisconnectEncoder());
	}
	
	public byte[] encode(AbstractMessage message){
		
		AbstractEncoder encoder = this.encoderMap.get(message.getMessageType());
		
		return encoder.encode(message);
	}
}
