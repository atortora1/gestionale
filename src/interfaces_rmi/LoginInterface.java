package interfaces_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginInterface extends Remote {

	public int Effettualogin(String User, String Password) throws RemoteException;
}
