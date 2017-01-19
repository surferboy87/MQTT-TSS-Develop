/**
 * 
 */
package net.huraki.tss.parser;


import java.io.ByteArrayInputStream;
import java.util.HashMap;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.utils.Utils;

/**
 * @author Raphael Huber
 *
 */
public class TSSDecoder {

	private HashMap<Byte, AbstractDecoder> decoderMap = new HashMap<Byte, AbstractDecoder>();
	
	public TSSDecoder(){
		this.decoderMap.put(AbstractMessage.CONNECT, new ConnectDecoder());
		this.decoderMap.put(AbstractMessage.CONNACK, new ConAckDecoder());
		this.decoderMap.put(AbstractMessage.REGISTER, new RegisterDecoder());
		this.decoderMap.put(AbstractMessage.REGACK, new RegAckDecoder());
		this.decoderMap.put(AbstractMessage.DISCONNECT, new DisconnectDecoder());
	}
	
	public AbstractMessage decode(ByteArrayInputStream bais){
		
		//bais.mark(0);
		byte messageType = Utils.readMessageType(bais);
		//bais.reset();
		
		AbstractDecoder decoder = this.decoderMap.get(messageType);
		
		return decoder.decode(bais);
	}
}
