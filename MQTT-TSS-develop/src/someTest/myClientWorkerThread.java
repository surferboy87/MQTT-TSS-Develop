package someTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class myClientWorkerThread implements Runnable {
	
	Socket socket = null;

	public myClientWorkerThread(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
		try (
				BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			) {
				while (!Thread.currentThread().isInterrupted()) {
					String servermsg = in.readLine();
					System.out.println("Server: " + servermsg);
					
					if(servermsg == null){
						Thread.currentThread().interrupt();
					}
				}
				
				this.socket.close();
				System.out.println("Socket is closed: " + socket.isClosed());
				System.exit(0);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
