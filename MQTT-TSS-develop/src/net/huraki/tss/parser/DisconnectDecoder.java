/**
 * 
 */
package net.huraki.tss.parser;

import java.io.ByteArrayInputStream;

import net.huraki.tss.messages.DisconnectMessage;

/**
 * @author Raphael Huber
 *
 */
public class DisconnectDecoder extends AbstractDecoder {

	public DisconnectDecoder(){
		
	}
	
	public DisconnectMessage decode(ByteArrayInputStream bais){
		return new DisconnectMessage();
	}
}
