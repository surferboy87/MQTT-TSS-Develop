package net.huraki.tss.comm;


public class ServerTester {

	public static void main(String[] args) throws InterruptedException {
		
		ThreadPoolTssServer server = new ThreadPoolTssServer(18833);
		server.start();
		//Thread.sleep(15000);
		//server.shutdown();
		

	}

}
