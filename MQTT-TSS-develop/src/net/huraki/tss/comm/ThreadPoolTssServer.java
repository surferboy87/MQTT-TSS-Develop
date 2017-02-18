/**
 * 
 */
package net.huraki.tss.comm;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Raphael Huber
 *
 */
public class ThreadPoolTssServer extends Thread {
	
	/**
	 * Server socket on which to accept incoming client connections.
	 */
	private ServerSocket listenSocket;

	private ExecutorService executor = Executors.newFixedThreadPool(1);
	
	/**
	 * Flag if this server is running.
	 */
	private volatile boolean keepRunning = true;
	
	public ThreadPoolTssServer(int port){
		
		try {
			this.listenSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		System.out.println("Server: Accepting incoming connections on port " + this.listenSocket.getLocalPort() +"\n");
		
		while(this.keepRunning && !this.isInterrupted()){
			
			try {
				
				ClientHandler handler = new ClientHandler(this.listenSocket.accept());
				this.executor.execute(handler);
				
			} catch (IOException e) {
				this.interrupt();
				e.printStackTrace();
			}
			
		}
		
		try {
			// Make sure to release the port, otherwise it may remain bound for several minutes
			this.listenSocket.close();
		}catch(IOException ioe){
			// Ignored
		}
		System.out.println("Stopped accepting incoming connections.");
		
	}
	
	public void shutdown() throws InterruptedException {
		System.out.println("Server: Shutting down the server.");
		this.keepRunning = false;
		System.out.println("Server: Properly end all thread");
		this.executor.shutdown();
		System.out.println("Time elapsed?" + !this.executor.awaitTermination(30, TimeUnit.SECONDS));
		if(this.executor.isShutdown()){
			System.out.println("Server: All Threads are finished");
			this.join();
			System.out.println(this.getState());
		}
	}	
}
