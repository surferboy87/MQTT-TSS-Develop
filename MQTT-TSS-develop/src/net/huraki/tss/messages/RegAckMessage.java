/**
 * 
 */
package net.huraki.tss.messages;

/**
 * @author Raphael Huber
 *
 */
public class RegAckMessage extends AbstractMessage {
	
	private String shortTopic = "";
	private int shortTopicLength;
	private int msgId;
	private byte returnCode;
	
	/**
	 * Initializes a newly created {@code RegAckMessage}
	 * 
	 * @param shortTopic
	 * @param shortTopicLength
	 * @param msgId
	 * @param returnCode
	 */
	public RegAckMessage(String shortTopic, int shortTopicLength, int msgId, byte returnCode){
		this.messageType = REGACK;
		this.shortTopic = shortTopic;
		this.shortTopicLength = shortTopicLength;
		this.msgId = msgId;
		this.returnCode = returnCode;
	}

	/**
	 * @return the shortTopic
	 */
	public String getShortTopic() {
		return shortTopic;
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
	 * @return the returnCode
	 */
	public byte getReturnCode() {
		return returnCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("RegAckMessage [shortTopic=%s, shortTopicLength=%s, msgId=%s, returnCode=%s, messageType=%s]",
						shortTopic, shortTopicLength, msgId, returnCode,
						messageType);
	}
	
}
