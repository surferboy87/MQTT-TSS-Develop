/**
 * 
 */
package net.huraki.tss.comm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.DisconnectMessage;
import net.huraki.tss.parser.TSSEncoder;
import net.huraki.tss.utils.Utils;

/**
 * @author Raphael Huber
 *
 */
public class TssClient {
	
	private String host;
	private int port;
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private boolean requestedShutdown = false;
	
	private ProtocolProcessorShortening pps = new ProtocolProcessorShortening();
	private TSSEncoder encoder = new TSSEncoder();
	
	/**
	 * @param hostName
	 * @param portNumber
	 */
	public TssClient(String hostName, int portNumber){
		this.host = hostName;
		this.port = portNumber;
	}
	
	/**
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void connect() throws UnknownHostException, IOException{
		this.socket = new Socket(this.host, this.port);
		this.dis = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
		this.dos = new DataOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
	}
	
	public void sendData(AbstractMessage message) throws IOException{
		System.out.println("sending now data to server...");
		byte[] dataOut = this.encoder.encode(message);
		this.dos.write(dataOut);
		this.dos.flush();
		System.out.println("data transmit finished");
		
	}
	
	public AbstractMessage receiveData() throws IOException{
		System.out.println("receiving data from server");

		//byte[] data = null;
		byte[] payload = null;
		
		int payloadLength = Utils.decodePayloadLenght(dis);
		payload = new byte[payloadLength];
		dis.read(payload, 0, payloadLength);
		
		for(byte b : payload){
			System.out.println("Client received: " + b);
		}

		//data = pps.processMsg(new ByteArrayInputStream(payload));
		AbstractMessage messageIn = pps.processMsg(new ByteArrayInputStream(payload));
		if(messageIn.getMessageType() == AbstractMessage.DISCONNECT && !requestedShutdown){
			shutdown(requestedShutdown);
		}
		
		return messageIn;
	}
	
	public void shutdown(boolean regularShutdown) {
		
		try {
			if(regularShutdown){
				System.out.println("sending disconnect request...");
				this.requestedShutdown = true;
				sendData(new DisconnectMessage());
				AbstractMessage msg = receiveData();
				if(msg.getMessageType() == AbstractMessage.DISCONNECT){
					System.out.println("Disconnect accepted by Server. Closing now socket...");
				}
			} else {
				System.out.println("Client: Shutting down. Server has sent disconnect. Closing now socket...");
			}
			
			this.socket.close();
			System.out.println("Client shutdown completed");
			
		} catch (IOException e) {
			//ignored
		}
	}
	
}
