package server.control;

import server.persistenza.DBManager;

public class ControlLogin {

	public int EffettuaLogin(String User, String Password) {
		System.out.println("Login Control");
		// DBManager db = new DBManager();
		return DBManager.getInstance().login(User, Password);
	}
}
