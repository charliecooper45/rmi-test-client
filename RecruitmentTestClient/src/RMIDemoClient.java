import java.rmi.Naming;

public class RMIDemoClient {
	public static void main(String[] args) {
		String url = "rmi://localhost/TestServer";
		TestServerInterface rmiDemo;
		try {
			rmiDemo = (TestServerInterface) Naming.lookup(url);
			String serverReply = String.valueOf(rmiDemo.connectToServer("Charlie"));
			System.out.println("Server reply: " + serverReply);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
