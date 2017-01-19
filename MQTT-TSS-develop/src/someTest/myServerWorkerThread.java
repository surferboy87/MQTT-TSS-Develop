package someTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class myServerWorkerThread implements Runnable {
	
	private Socket socket = null;
	private boolean listenClient = true;
	
	public myServerWorkerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		
		try (
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			) {
				String inputLine, outputLine;
				myProtocol mp = new myProtocol();
				outputLine = mp.processInput(null, Thread.currentThread().getName());
				out.println(outputLine);
				
				while ((inputLine = in.readLine()) != null && !Thread.currentThread().isInterrupted()) {
					outputLine = mp.processInput(inputLine, Thread.currentThread().getName());
					out.println(outputLine);
					System.out.println(outputLine);
					if (outputLine.equals(Thread.currentThread().getName() + ": Bye")) {
						Thread.currentThread().interrupt();
					}
				}
				System.out.println("Now shutdown socket");
				socket.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
