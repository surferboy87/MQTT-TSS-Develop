/**
 * 
 */
package net.huraki.tss.messages;


/**
 * @author Raphael Huber
 *
 */
public class ConAckMessage extends AbstractMessage {
	
	private byte returnCode;

	public ConAckMessage(){
		this.messageType = CONNACK;
	}

	/**
	 * @return the returnCode
	 */
	public byte getReturnCode() {
		return returnCode;
	}

	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(byte returnCode) {
		this.returnCode = returnCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("ConAckMessage [returnCode=%s, messageType=%s]",
				returnCode, messageType);
	}
	
	
}
