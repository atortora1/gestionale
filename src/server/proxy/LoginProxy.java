package server.proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces_rmi.LoginInterface;
import server.control.ControlLogin;;

public class LoginProxy extends UnicastRemoteObject implements LoginInterface {

	/**
	 * 
	 */

	private static final long serialVersionUID = -6533196437139402848L;

	protected LoginProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int Effettualogin(String User, String Password) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Login proxy server");
		ControlLogin cr = new ControlLogin();
		return cr.EffettuaLogin(User, Password);

	}

}
