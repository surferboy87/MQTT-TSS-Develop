/**
 * 
 */
package net.huraki.tss.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.DisconnectMessage;
import net.huraki.tss.utils.Utils;

/**
 * @author Raphael
 *
 */
public class DisconnectEncoder extends AbstractEncoder{
	
	public DisconnectEncoder(){
		
	}

	@Override
	public byte[] encode(AbstractMessage msg) {
		byte[] out = null;
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		
		DisconnectMessage disConn = (DisconnectMessage) msg;
		try {
			temp.write(disConn.getMessageType());
			temp.close();
			
			out = Utils.addLengthField(temp).toByteArray();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return out;
	}

}
