package someTest;

import net.huraki.tss.messages.AbstractMessage;
import net.huraki.tss.messages.RegisterMessage;

public class Bla {

	public static void main(String[] args) {
		

		AbstractMessage msg = getit();
		System.out.println(msg.getMessageType());
		RegisterMessage reg = (RegisterMessage) msg;
		System.out.println(reg.getMqttTopic());

	}
	
	
	public static AbstractMessage getit(){
		return new RegisterMessage(0, "Holla");
	}

}
