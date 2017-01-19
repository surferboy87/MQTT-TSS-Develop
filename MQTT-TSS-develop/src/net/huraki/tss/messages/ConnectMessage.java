/**
 * 
 */
package net.huraki.tss.messages;


/**
 * @author Raphael Huber
 *
 */
public class ConnectMessage extends AbstractMessage {
	
	private String clientID;
	
	public ConnectMessage(String clientID){
		this.clientID = clientID;
		this.messageType = CONNECT;
	}

	/**
	 * @return the clientID
	 */
	public String getClientID() {
		return clientID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("ConnectMessage [clientID=%s, messageType=%s]",
				clientID, messageType);
	}

}
