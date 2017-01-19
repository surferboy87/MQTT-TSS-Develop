package net.huraki.tss.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.RegisterMessage;
import net.huraki.tss.utils.*;

/**
 * @author Raphael Huber
 *
 */
public class RegisterEncoder extends AbstractEncoder{

	public RegisterEncoder(){
		
	}
	
	/**
	 * Encodes a {@code RegisterMessage} into a byte array
	 * 
	 * @param message the RegisterMessage to encode
	 * @return byte array
	 */

	@Override
	public byte[] encode(AbstractMessage msg) {
		byte[] out = null;
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		
		RegisterMessage reg = (RegisterMessage) msg;
		try {
			temp.write(TssConstants.REG_MSG_TYPE);
			
			if(reg.getShortTopic().isEmpty()){
				temp.write(TssConstants.REG_SHORT_TOPIC_LENGTH);
			} else {
				byte[] shortTopic = reg.getShortTopic().getBytes("UTF-8");
				Utils.encodeLength(shortTopic.length).writeTo(temp);
				temp.write(shortTopic);
			}
			
			temp.write(Utils.encodeMsgIdToByte(reg.getMsgId()));
			temp.write(reg.getMqttTopic().getBytes("UTF-8"));
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
