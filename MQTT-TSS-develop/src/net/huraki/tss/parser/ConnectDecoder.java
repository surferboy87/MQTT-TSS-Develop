/**
 * 
 */
package net.huraki.tss.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.ConnectMessage;

/**
 * @author Raphael Huber
 *
 */
public class ConnectDecoder extends AbstractDecoder{

	public ConnectDecoder(){
		
	}

	@Override
	public AbstractMessage decode(ByteArrayInputStream bais) {
		String clientID = null;
		byte[] id = new byte[bais.available()];
		try {
			bais.read(id);
			clientID = new String(id, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ConnectMessage(clientID);
	}
	
}
