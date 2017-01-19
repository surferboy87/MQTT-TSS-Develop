
package net.huraki.tss.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.RegisterMessage;
import net.huraki.tss.utils.Utils;

/**
 * @author Raphael Huber
 *
 */
public class RegisterDecoder extends AbstractDecoder {
	
	public RegisterDecoder(){
		
	}

	@Override
	public AbstractMessage decode(ByteArrayInputStream bais) {
		int shortTopicLength = 0;
		int msgId = 0;
		String shortTopic = null;
		String mqttTopic = null;
		
		try {
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
			
			temp = new byte[bais.available()];
			bais.read(temp);
			mqttTopic = new String(temp, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			//NB every Java platform has got UTF-8 encoding by default, so this 
            //exception are never raised.
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new RegisterMessage(shortTopicLength, shortTopic, msgId, mqttTopic);
	}
	

}
