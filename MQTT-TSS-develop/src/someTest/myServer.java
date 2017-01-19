package someTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class myServer {

	public static void main(String[] args) {
		
		int portNumber = 18833;
		boolean listening = true;
		ExecutorService executor = Executors.newFixedThreadPool(1);//creating a pool of 5 threads
		
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {

        	while (listening) {
        		Runnable worker = new myServerWorkerThread(serverSocket.accept());
        		executor.execute(worker);
        	}
        	
        	executor.shutdown();
        	
        } catch (IOException e) {
        	System.out.println("Exception caught when trying to listen on port "
        			+ portNumber + " or listening for a connection");
        	System.out.println(e.getMessage());
        	System.exit(-1);
        }

	}

}
