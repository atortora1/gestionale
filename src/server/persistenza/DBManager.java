package server.persistenza;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.scene.control.Alert;

public class DBManager {

	private Connection connect;
	private volatile static DBManager dbInstance;

	private DBManager() {
	}

	public static DBManager getInstance() {
		if (dbInstance == null)
			synchronized (DBManager.class) {
				if (dbInstance == null)
					dbInstance = new DBManager();
			}
		return dbInstance;
	}

	public Connection getConnection() {
		return connect;
	}

	public int login(String user, String password) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://u3y93bv513l7zv6o.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/ph2z7xhp182hcqva?" + "user="
							+ user + "&password=" + password);
			// JOptionPane.showMessageDialog(null,"Connessione al database Riuscita !!!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connect = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connect = null;
			return -1;
			// JOptionPane.showMessageDialog(null,"Nome utente e password errari!!!!");

			/*
			 * try { //long i; //for(i=0;i<1000000000;i++); try { Thread.sleep(15000); }
			 * catch (InterruptedException e1) { // TODO Auto-generated catch block
			 * e1.printStackTrace(); } //System.out.print(i); connect =
			 * DriverManager.getConnection("jdbc:mysql://localhost/ufficio?" +
			 * "user="+user+"&password="+password); } catch (SQLException e1) { // TODO
			 * Auto-generated catch block e1.printStackTrace(); } //e.printStackTrace();
			 */
		} catch (RuntimeException r) {
			r.printStackTrace();
			new Alert(Alert.AlertType.ERROR, "Attenzione!\nServer non collegato!").showAndWait();
		}
		return 0;
	}

	public void closeConnection() {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connect = null;
		}
	}
}
