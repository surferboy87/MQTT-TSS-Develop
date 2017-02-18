/**
 * 
 */
package net.huraki.tss.comm;

import java.io.ByteArrayInputStream;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.ConAckMessage;
import net.huraki.tss.messages.ConnectMessage;
import net.huraki.tss.messages.DisconnectMessage;
import net.huraki.tss.messages.RegAckMessage;
import net.huraki.tss.messages.RegisterMessage;
import net.huraki.tss.parser.TSSDecoder;
import net.huraki.tss.utils.TssConstants;

/**
 * @author Raphael Huber
 *
 */
public class ProtocolProcessorShortening {
	

	private TSSDecoder decoder = new TSSDecoder();

	public ProtocolProcessorShortening() {

	}
	
	public AbstractMessage processMsg(ByteArrayInputStream bais) {
		
		AbstractMessage messageIn = this.decoder.decode(bais);
		AbstractMessage messageOut = null;
		
		switch (messageIn.getMessageType()) {
		case AbstractMessage.CONNECT:
			messageOut = processConnect(messageIn);
			break;
		case AbstractMessage.CONNACK:
			messageOut = processConAck(messageIn);
			break;
		case AbstractMessage.REGISTER:
			System.out.println("Process: RegisterMessage received");
			messageOut = processRegister(messageIn);
			break;
		case AbstractMessage.REGACK:
			System.out.println("Process: RegAckMessage received");
			messageOut = processRegAck(messageIn);
			break;
		case AbstractMessage.DISCONNECT:
			System.out.println("Process: DisconnectMessage received");
			messageOut = processDisconnect();
			break;
		default:
			System.out.println("Something went wrong");
			break;
		}

		return messageOut;

	}


	private AbstractMessage processConnect(AbstractMessage messageIn) {
		
		ConnectMessage conMsg = (ConnectMessage) messageIn;
		System.out.println("ConnectMessage received from <" + conMsg.getClientID() + ">");
		ConAckMessage conAck = new ConAckMessage();
		conAck.setReturnCode(TssConstants.ACCEPTED);
		return conAck;

	}
	
	private AbstractMessage processConAck(AbstractMessage messageIn) {
		ConAckMessage conAck = (ConAckMessage) messageIn;
		if(conAck.getReturnCode() == TssConstants.ACCEPTED){
			System.out.println("Process ConAck: Connection etablished");
		}
		return conAck;
	}

	private AbstractMessage processRegister(AbstractMessage messageIn) {
		RegisterMessage regMsg = (RegisterMessage) messageIn;
		System.out.println("Process: " + regMsg.toString());
		RegAckMessage regAckMsg = new RegAckMessage("short", 5, regMsg.getMsgId(), TssConstants.ACCEPTED);
		return regAckMsg;
	}

	private AbstractMessage processRegAck(AbstractMessage messageIn) {
		RegAckMessage regAck = (RegAckMessage) messageIn;
		return regAck;
		
	}

	private AbstractMessage processDisconnect() {
		DisconnectMessage disConn = new DisconnectMessage();
		return disConn;
		
	}

	

}
