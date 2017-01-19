/**
 * 
 */
package net.huraki.tss.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.RegAckMessage;
import net.huraki.tss.utils.Utils;

/**
 * @author Raphael Huber
 *
 */
public class RegAckDecoder extends AbstractDecoder {

	public RegAckDecoder() {

	}

	@Override
	public AbstractMessage decode(ByteArrayInputStream bais) {
		String shortTopic = null;
		int shortTopicLength = 0;
		int msgId = 0;
		byte returnCode = 0x00;
		
		try {
			//bais.skip(1); //TODO: delete when implemented
			shortTopicLength = Utils.decodeRemainingLenght(bais);
			
			byte[] temp;
			if(shortTopicLength > 0){
				temp = new byte[shortTopicLength];
				bais.read(temp);
				shortTopic = new String(temp, "UTF-8");
			} else {
				shortTopic ="";
			}
			
			msgId = Utils.decodeMsgId(bais);
			returnCode = (byte) bais.read();
			
		} catch (UnsupportedEncodingException e) {
			//NB every Java platform has got UTF-8 encoding by default, so this 
            //exception are never raised.
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new RegAckMessage(shortTopic, shortTopicLength, msgId, returnCode);
	}
	
}
