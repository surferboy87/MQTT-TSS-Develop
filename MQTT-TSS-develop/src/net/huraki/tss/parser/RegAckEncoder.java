/**
 * 
 */
package net.huraki.tss.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.RegAckMessage;
import net.huraki.tss.utils.*;

/**
 * @author Raphael Huber
 *
 */
public class RegAckEncoder extends AbstractEncoder{

	public RegAckEncoder() {

	}

	@Override
	public byte[] encode(AbstractMessage msg) {
		byte[] out = null;
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		
		RegAckMessage regAck = (RegAckMessage) msg;
		//System.out.println(regAck.toString());
		try {
			temp.write(TssConstants.REGACK_MSG_TYPE);
			
			if (regAck.getShortTopic().isEmpty()) {
				temp.write(TssConstants.REG_SHORT_TOPIC_LENGTH);
			} else {
				byte[] shortTopic;
				shortTopic = regAck.getShortTopic().getBytes("UTF-8");
				Utils.encodeLength(shortTopic.length).writeTo(temp);
				temp.write(shortTopic);
			}
			byte[] msgid = Utils.encodeMsgIdToByte(regAck.getMsgId());
//			for(byte m : msgid){
//				System.out.println(m);
//			}
			temp.write(msgid);
			temp.write(regAck.getReturnCode());
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
