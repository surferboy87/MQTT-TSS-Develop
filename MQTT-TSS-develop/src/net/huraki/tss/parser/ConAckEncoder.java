/**
 * 
 */
package net.huraki.tss.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.ConAckMessage;
import net.huraki.tss.utils.Utils;

/**
 * @author Raphael Huber
 *
 */
public class ConAckEncoder extends AbstractEncoder{

	public ConAckEncoder(){
		
	}

	@Override
	public byte[] encode(AbstractMessage msg) {
		byte[] out = null;
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		
		ConAckMessage conAck = (ConAckMessage) msg;
		try {
			temp.write(conAck.getMessageType());
			temp.write(conAck.getReturnCode());
			temp.close();
			
			out = Utils.addLengthField(temp).toByteArray();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return out;
	}
}
