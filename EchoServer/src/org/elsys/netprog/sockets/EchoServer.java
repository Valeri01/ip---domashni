package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10001);
		    Socket clientSocket = serverSocket.accept();
			Thread th = new Thread(new Runnable() {
				private Socket sc;
				public Runnable init(Socket s) {
					this.sc = s;
					return this;
				}
				@Override
				public void run() {
					try {
						BufferedReader in = new BufferedReader(
						        new InputStreamReader(clientSocket.getInputStream()));
						String clientinput;
						while((clientinput = in.readLine()) != null) {
							System.out.println("Client response: "+ clientinput);
						}
					} catch (Exception e) {}
					
					
				}
			}.init(clientSocket));
			th.start();		    
		    System.out.println("client connected from " + clientSocket.getInetAddress());
		    PrintWriter out =
		        new PrintWriter(clientSocket.getOutputStream(), true);
		    BufferedReader stdIn = new BufferedReader(
				    new InputStreamReader(System.in));
		    String inputLine;
		    	
		    while ((inputLine = stdIn.readLine()) != null) {
		        out.println(inputLine);
		    }
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			
			System.out.println("Server closed");
		}
	}

}
