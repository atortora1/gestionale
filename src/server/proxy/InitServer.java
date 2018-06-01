package server.proxy;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

import interfaces_rmi.ClienteInterface;
import interfaces_rmi.LoginInterface;

public class InitServer {
	public static Registry registry = null;

	public static void main(String[] args) {

		try {
			System.out.println("Init server...\n");

			/*
			 * System.setProperty("java.security.policy","src/server.policy"); if
			 * (System.getSecurityManager() == null) { System.setSecurityManager(new
			 * SecurityManager()); }
			 * 
			 */
			// System.setProperty("java.rmi.server.hostname", "192.168.0.6");

			registry = LocateRegistry.createRegistry(1099);

			ClienteInterface IECliente = new ClienteProxy();
			LoginInterface IElogin = new LoginProxy();
			System.out.println("Registrazione RMI...\n");
			registry.rebind("Cliente", IECliente);
			registry.rebind("EffettuaLogin", IElogin);
			System.out.println("Servizi RMI registrati con successo. \n");
			JOptionPane.showMessageDialog(null, "Servizi registrati");
		} catch (Exception e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}

}
