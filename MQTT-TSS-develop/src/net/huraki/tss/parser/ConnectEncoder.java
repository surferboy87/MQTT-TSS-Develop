/**
 * 
 */
package net.huraki.tss.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.ConnectMessage;
import net.huraki.tss.utils.Utils;

/**
 * @author Raphael Huber
 *
 */
public class ConnectEncoder extends AbstractEncoder{

	public ConnectEncoder(){
		
	}

	@Override
	public byte[] encode(AbstractMessage msg){
		
		byte[] out = null;
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		
		ConnectMessage conn = (ConnectMessage) msg;
		
		try {
			temp.write(conn.getMessageType());
			temp.write(conn.getClientID().getBytes("UTF-8"));
			temp.close();
			
			out = Utils.addLengthField(temp).toByteArray();

		} catch (UnsupportedEncodingException e) {
			//NB every Java platform has got UTF-8 encoding by default, so this 
            //exception are never raised.
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return out;
	}
	
	
}
