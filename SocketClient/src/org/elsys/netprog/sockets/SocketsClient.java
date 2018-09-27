package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.SynchronousQueue;

public class SocketsClient {

	public static void main(String[] args) throws IOException {
		Socket echoSocket = null;
		try {
			    
				echoSocket = new Socket("localhost", 10001);
			    Thread th  = new Thread(new Runnable() {
					private Socket s;
				    public Runnable init(Socket s) {
				        	this.s = s;
				        	return this;
				    }
					@Override
					public void run() {
						try {
							String userInput;
							PrintWriter out = new PrintWriter( this.s.getOutputStream(), true);
							BufferedReader stdIn = new BufferedReader(
						    new InputStreamReader(System.in));
							while ((userInput = stdIn.readLine()) != null) {
						        out.println(userInput);
						    }
						}catch (Exception e) {} 
								
					}
				}.init(echoSocket));
			    
				th.start();
			    BufferedReader in = new BufferedReader(
			            new InputStreamReader(echoSocket.getInputStream()));
			    String userInput;
			    while ((userInput = in.readLine()) != null) {
			        System.out.println("server response: " + userInput);
			    }
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (echoSocket != null && !echoSocket.isClosed()) {
				echoSocket.close();
			}
		}
	}

}
