/**
 * 
 */
package net.huraki.tss.comm;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Raphael
 *
 */
public class ClientListeningThread implements Runnable {

	private Socket socket;
	private DataInputStream dis;
	private ConcurrentLinkedQueue<byte[]> queue;
	private int lengthCounter;
	private boolean run = true;
	
	
	public ClientListeningThread(Socket s, DataInputStream dis, ConcurrentLinkedQueue<byte[]> q){
		this.socket = s;
		this.dis = dis;
		this.queue = q;
		this.lengthCounter = 0;
	}
	
	@Override
	public void run() {
		
		try {
			String message;
			
			while((message = this.dis.readUTF()) != "disconnect"){
				System.out.println(message);
			}

		} catch (EOFException eof) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("closing socket from client");
				
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
//			try {
//				while ((this.dis.readByte() & 0x80) == 0x80 && this.lengthCounter <= 3) {
//					lengthCounter++;
//				}
//
//				byte[] lengthBytes = new byte[lengthCounter];
//				this.dis.read(lengthBytes);
//
//				int messageLength = Utils.decodeRemainingLenght(new ByteArrayInputStream(lengthBytes));
//				byte[] temp = new byte[messageLength - lengthCounter];
//				this.dis.read(temp, 0, messageLength);
//				this.queue.add(temp);
//			
//			} catch (IOException ioe){
//				Thread.currentThread().interrupt();
//				ioe.printStackTrace();
//			}
			
		
	}

}
