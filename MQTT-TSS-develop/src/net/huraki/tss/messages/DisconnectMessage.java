/**
 * 
 */
package net.huraki.tss.messages;


/**
 * @author Raphael Huber
 *
 */
public class DisconnectMessage extends AbstractMessage {
	
	public DisconnectMessage(){
		this.messageType = DISCONNECT;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("DisconnectMessage [messageType=%s]", messageType);
	}

}
