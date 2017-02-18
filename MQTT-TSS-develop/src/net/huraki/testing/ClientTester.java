package net.huraki.testing;

import java.io.IOException;
import java.net.UnknownHostException;

import net.huraki.tss.comm.TssClient;
import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.ConAckMessage;
import net.huraki.tss.messages.ConnectMessage;
import net.huraki.tss.messages.RegAckMessage;
import net.huraki.tss.messages.RegisterMessage;
import net.huraki.tss.utils.TssConstants;

public class ClientTester {
	
	private static RegisterMessage regMsg = new RegisterMessage(254, "TestBlaTestBlaTestBlaTestBlaTestBlaTestBlaTestBlaTestBlaTestBlaTestBlaTestBlaTestBla");
	
	private static ConnectMessage conMsg = new ConnectMessage("Client ID");

	public static void main(String[] args) throws InterruptedException {
		
		
		TssClient client = new TssClient("localhost", 18833);
		try {
			client.connect();
			System.out.println("Tester: sending connectMessage");
			System.out.println("Tester:" + conMsg.toString());
			client.sendData(conMsg);
			
			AbstractMessage msg = client.receiveData();
			if(msg.getMessageType() == AbstractMessage.CONNACK){
				System.out.println("Tester: MessageType = ConAck");
				ConAckMessage conAck = (ConAckMessage) msg;
				System.out.println("Tester: " + conAck.toString() + "\n");
				System.out.println("Tester: Connection accepted: " + (conAck.getReturnCode() == TssConstants.ACCEPTED));
				System.out.println();
				System.out.println("Tester: sending registerMessage");
				System.out.println(regMsg.toString());
				client.sendData(regMsg);
			}
			msg = client.receiveData();
			RegAckMessage regAck = (RegAckMessage) msg;
			System.out.println("Tester: " + regAck.toString());
			client.shutdown(true);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	

}
