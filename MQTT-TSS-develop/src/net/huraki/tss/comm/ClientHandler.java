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
import java.net.SocketException;
import java.net.SocketTimeoutException;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.DisconnectMessage;
import net.huraki.tss.parser.DisconnectEncoder;
import net.huraki.tss.parser.TSSEncoder;
import net.huraki.tss.utils.Utils;


/**
 * @author Raphael Huber
 *
 */
public class ClientHandler implements Runnable {

	/**
	 * The socket connected to the client
	 */
	private Socket clientSocket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	
	private ProtocolProcessorShortening pps = new ProtocolProcessorShortening();
	private TSSEncoder encoder = new TSSEncoder();
	
	/**
	 * Creates a new {@code CliendHandler} thread for the socket provided.
	 * @param clientSocket the socket to the client
	 */
	public ClientHandler(Socket clientSocket){
		
		try {
			this.clientSocket = clientSocket;
			this.clientSocket.setSoTimeout(30000);
			
			this.dis = new DataInputStream(new BufferedInputStream(this.clientSocket.getInputStream()));
			this.dos = new DataOutputStream(new BufferedOutputStream(this.clientSocket.getOutputStream()));
			System.out.println("Listening for data from " + this.clientSocket.getRemoteSocketAddress());
		} catch (SocketException e) {
			//ignored here
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error by using IO.");
			e.printStackTrace();
		}
	}
	
	/**
	 * The run method is invoked by the {@code ExecutorService}
	 */
	@Override
	public void run() {
		
		try {
			
			while(!Thread.currentThread().isInterrupted()){

				int payloadLength = Utils.decodePayloadLenght(dis);
				byte[] payload = new byte[payloadLength];
				dis.read(payload, 0, payloadLength);
				
				for(byte b : payload){
					System.out.println("Received: " + b);
				}
				
//				byte[] dataForClient = pps.processMsg(new ByteArrayInputStream(payload));
//				for(byte b : dataForClient){
//					System.out.println(b);
//				}
				AbstractMessage messageOut = pps.processMsg(new ByteArrayInputStream(payload));
				byte[] data = this.encoder.encode(messageOut);
				
				dos.write(data);
				dos.flush();
				
				if(messageOut.getMessageType() == AbstractMessage.DISCONNECT){
					System.out.println("disconnect from client");
					Thread.currentThread().interrupt();
					System.out.println(Thread.currentThread().isInterrupted());
				}
			}
				
		} catch (SocketTimeoutException ste){
			System.out.println("No Data received from " + this.clientSocket.getRemoteSocketAddress());
			try {
				this.dos.write(new DisconnectEncoder().encode(new DisconnectMessage()));
				this.dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.currentThread().interrupt();
		} catch (IOException ioe){
			System.out.println("Error by using IO. Client may have close the socket");
			ioe.printStackTrace();
		} finally {
			try {
				System.out.println("Closing now the socket");
				this.clientSocket.close();
				Thread.currentThread().interrupt();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
