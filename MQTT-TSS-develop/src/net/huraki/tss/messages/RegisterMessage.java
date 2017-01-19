/**
 * 
 */
package net.huraki.tss.messages;

import java.io.UnsupportedEncodingException;

import net.huraki.tss.utils.TssConstants;


/**
 * @author Raphael Huber
 *
 */
public class RegisterMessage extends AbstractMessage {

	private int shortTopicLength;
	private int msgId;
	private String shortTopic = "";
	private String mqttTopic = "";
	
	/**
	 * Initializes a newly created {@code RegisterMessage}
	 * 
	 * @param msgId
	 * @param mqttTopic
	 */
	public RegisterMessage(int msgId, String mqttTopic){
		this.shortTopicLength = TssConstants.REG_SHORT_TOPIC_LENGTH;
		this.msgId = msgId;
		this.mqttTopic = mqttTopic;
		this.messageType = REGISTER;
	}
	
	/**
	 * Initializes a newly created {@code RegisterMessage}
	 * 
	 * @param shortTopicLength
	 * @param shortTopic
	 * @param msgId
	 * @param mqttTopic
	 */
	public RegisterMessage(int shortTopicLength, String shortTopic, int msgId, String mqttTopic){
		this.shortTopicLength = shortTopicLength;
		this.msgId = msgId;
		this.shortTopic = shortTopic;
		this.mqttTopic = mqttTopic;
		this.messageType = REGISTER;
		
		try {
			this.shortTopicLength = shortTopic.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @return the shortTopicLength
	 */
	public int getShortTopicLength() {
		return shortTopicLength;
	}

	/**
	 * @return the msgId
	 */
	public int getMsgId() {
		return msgId;
	}

	/**
	 * @return the shortTopic
	 */
	public String getShortTopic() {
		return shortTopic;
	}

	/**
	 * @return the mqttTopic
	 */
	public String getMqttTopic() {
		return mqttTopic;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("RegisterMessage [shortTopicLength=%s, msgId=%s, shortTopic=%s, mqttTopic=%s, messageType=%s]",
						shortTopicLength, msgId, shortTopic, mqttTopic,
						messageType);
	}
	
	
}
