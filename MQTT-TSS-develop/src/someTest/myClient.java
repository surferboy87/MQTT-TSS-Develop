package someTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class myClient {

	public static void main(String[] args) {
		
		String hostName = "localhost";
		int portNumber = 18833;
		
		try (
				Socket clientSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
			)  {
			String userInput;
			
			Thread t = new Thread(new myClientWorkerThread(clientSocket));
			t.start();
			
			System.out.println("Client is listening");
			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
			}
			System.out.println("Now Client will vanish");
			System.exit(0);
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}

	}

}
