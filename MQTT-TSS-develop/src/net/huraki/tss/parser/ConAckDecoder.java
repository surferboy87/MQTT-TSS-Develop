/**
 * 
 */
package net.huraki.tss.parser;

import java.io.ByteArrayInputStream;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.ConAckMessage;

/**
 * @author Raphael Huber
 *
 */
public class ConAckDecoder extends AbstractDecoder{

	public ConAckDecoder(){
		
	}

	@Override
	public AbstractMessage decode(ByteArrayInputStream bais) {
		ConAckMessage msg = new ConAckMessage();
		msg.setReturnCode((byte) bais.read());
		return msg;
	}
	
}
