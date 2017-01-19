package someTest;

public class myProtocol {
	
	private static final int WAITING = 0;
	private static final int CONNECTED = 1;
	private static final int SHORTENING = 2;
	private static final int FINISH = 3;
	
	private int state = WAITING;
	
	public String processInput(String theInput, String threadName){
		String theOutput = null;
		
//		if(theInput == null){
//			theOutput = "Welcome, enter connect to start";
//		}
		
		if (state == WAITING) {
			theOutput = threadName + ": Waiting for connection";
			state = CONNECTED;
		} else if (state == CONNECTED) {
			if (theInput.equalsIgnoreCase("connect")) {
				theOutput = threadName + ": is now connected";
				state = SHORTENING;
			} else {
				theOutput = threadName + ": You're supposed to enter \"connect\"! ";
			}
		} else if (state == SHORTENING){
			if(theInput.length() > 0 && !theInput.equalsIgnoreCase("stop")){
				theOutput = threadName + ": Given String has " + theInput.length() + " characters";
			} else {
				theOutput = threadName + ": has stopped sending shortening requests";
				state = FINISH;
			}
		} else if (state == FINISH){
			theOutput = threadName + ": Bye";
			state = WAITING;
		}
		
		return theOutput;
	}

}
