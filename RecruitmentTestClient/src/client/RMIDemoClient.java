package client;
import interfaces.TestServerInterface;

import java.rmi.Naming;
import java.util.Scanner;

public class RMIDemoClient {
	private static final String SERVER_URL = "rmi://localhost/TestServer";
	private static TestServerInterface serverAPI;
	private static Scanner scan;
	
	public static void main(String[] args) {
		boolean connected = false;
		
		scan = new Scanner(System.in);
		
		while(!connected) {
			System.out.print("Enter your name: ");
			String name = scan.nextLine();
			connected = connectToServer(name);
		}
	}
	
	private static boolean connectToServer(String name) {
		boolean reply = false;
		
		try {
			serverAPI = (TestServerInterface) Naming.lookup(SERVER_URL);
			reply = serverAPI.connectToServer(name);
			if(reply) {
				System.out.println("Connected to the server.");
			} else {
				System.out.println("Name is in use, please retry with another");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return reply;
	}
}
