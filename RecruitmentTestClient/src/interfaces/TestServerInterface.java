package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.healthmarketscience.rmiio.RemoteInputStream;

import database.UserBean;


public interface TestServerInterface extends Remote {
	public String connectToServer(String userName) throws RemoteException;
	public String addUser(String firstName, String surname) throws RemoteException;
	public List<UserBean> listUsers() throws RemoteException;
	public boolean uploadFile(String fileName, RemoteInputStream fileData) throws RemoteException;
}
