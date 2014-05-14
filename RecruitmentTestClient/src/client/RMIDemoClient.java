package client;
import interfaces.TestServerInterface;
import interfaces.LoginInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RMIDemoClient {
	private static final String SERVER_URL = "rmi://localhost/TestLoginServer";
	private static TestServerInterface server;
	private static LoginInterface loginServer;
	private static Scanner scan;
	
	public static void main(String[] args) {
		try {
			loginServer = (LoginInterface) Naming.lookup(SERVER_URL);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
		attemptLogin();
		
		try {
			String message = server.addUser("Ed", "Cheba");
			
			System.out.println(message);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println(e.getMessage());
		}
		
		/*
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
		
		boolean connected = false;
		
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
	
	private static void attemptLogin() {
		System.out.println("-------- TRYING LOGIN --------");
		try {
			server = (TestServerInterface) loginServer.login("Charlie", "letmein");
			System.out.println("---------- LOGIN OK ----------\n");
		} catch (SecurityException e) {
			System.out.println("Password is incorrect! Exiting...");
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
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
	*/
}
