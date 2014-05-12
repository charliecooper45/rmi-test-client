package client;
import interfaces.TestServerInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;

import com.healthmarketscience.rmiio.RemoteInputStreamServer;
import com.healthmarketscience.rmiio.SimpleRemoteInputStream;

public class RMIDemoClient {
	private static final String SERVER_URL = "rmi://localhost/TestServer";
	private static TestServerInterface serverAPI;
	private static Scanner scan;
	
	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		String file = "./test.txt";
		Path path = Paths.get(file);
		
		System.out.println(Files.exists(path));

		connectToServer("Charlie");
		
		try {
			// upload a file to the server - close streams!
			InputStream inputStream = new FileInputStream(file);
			RemoteInputStreamServer remoteFileData = new SimpleRemoteInputStream(inputStream); 
			
			boolean fileUploaded = serverAPI.uploadFile("test.txt", remoteFileData);
			System.out.println("File uploaded: " + fileUploaded);
		} catch (RemoteException | FileNotFoundException ex) {
			ex.printStackTrace();
		} 
		
		/*
		 * 		boolean connected = false;
		
		scan = new Scanner(System.in);
		
		while(!connected) {
			System.out.print("Enter your name: ");
			String name = scan.nextLine();
			connected = connectToServer(name);
		}
		// add a new user to the database
		System.out.print("First Name:");
		String firstName = scan.nextLine();
		System.out.print("Surname:");
		String surname = scan.nextLine();
		String response = null;
		try {
			response = serverAPI.addUser(firstName, surname);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("Server: " + response);
		
		
		// retrieve all users from the database
		System.out.println("Users:");
		try {
			List<UserBean> users = serverAPI.listUsers();
			for(UserBean user : users) {
				System.out.println("ID: " + user.getId() + " First Name: " + user.getFirstName() + " Surname: " + user.getSurname());
			}
			
			// upload a file to the server
			serverAPI.uploadFile("filename", null);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		*/
	}
	
	private static boolean connectToServer(String name) {
		String reply = "";
		
		try {
			serverAPI = (TestServerInterface) Naming.lookup(SERVER_URL);
			reply = serverAPI.connectToServer(name);
			if(reply == null) {
				System.out.println("Connected to the server.");
				return true;
			} else {
				System.err.println(reply);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
}
