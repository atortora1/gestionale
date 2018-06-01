package server.persistenza;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import server.entity.EntityCliente;

public class ClientiDAO {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat df2 = new SimpleDateFormat("d-MMM-yyyy");
	SimpleDateFormat sdf2 = new SimpleDateFormat("d-MMM-yyyy");

	public boolean InserisciCliente(String CF, String Nome, String Cognome, String citta, String indirizzo,
			String email, String cap, String cell, String data, String note) {
		try {
			Connection connect = DBManager.getInstance().getConnection();
			String query = "insert into ph2z7xhp182hcqva.clienti (CF,Nome,Cognome,`Comune di Nascita`,Indirizzo,email,Cap,Tel,`Data di Nascita`,note) values (?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement pat = connect.prepareStatement(query);

			pat.setString(1, CF.toUpperCase());
			pat.setString(2, Nome.toUpperCase());
			pat.setString(3, Cognome.toUpperCase());
			pat.setString(4, citta.toUpperCase());
			pat.setString(5, indirizzo.toUpperCase());
			pat.setString(6, email);
			pat.setString(7, cap);
			pat.setString(8, cell);
			pat.setString(9, data);
			pat.setString(10, note);

			pat.execute();
			// connect.createStatement().execute("UNLOCK TABLES");

			pat.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean updateCliente(String CF, String Nome, String Cognome, String citta, String indirizzo, String email,
			String cap, String cell, String data, String note) {
		try {

			String query = "Update ph2z7xhp182hcqva.clienti set CF=? ,Nome = ?, Cognome= ?, `Comune di Nascita`=?, Indirizzo=?, email=?, Cap=?, Tel=?, `Data di Nascita` =?, note= ? where CF = ?;";
			PreparedStatement pat;
			pat = DBManager.getInstance().getConnection().prepareStatement(query);
			pat.setString(1, CF.toUpperCase());
			pat.setString(2, Nome.replace("\'", "\\'").toUpperCase());
			pat.setString(3, Cognome.replace("\'", "\\'").toUpperCase());
			pat.setString(4, citta);
			pat.setString(5, indirizzo.replace("\'", "\\'").toUpperCase());
			pat.setString(6, email);
			pat.setString(7, cap);
			pat.setString(8, cell);
			pat.setString(9, data);
			pat.setString(10, note.replace("\'", "\\'").toUpperCase());
			pat.setString(11, CF);
			pat.executeUpdate();
			pat.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public ArrayList<EntityCliente> getClienti(String Nome, String Cognome, String data) {
		ArrayList<EntityCliente> cliente = new ArrayList<EntityCliente>();
		EntityCliente dett = null;
		String nom, cog, dat;
		nom = cog = dat = "";

		try {

			Connection connect = DBManager.getInstance().getConnection();
			if (!Nome.isEmpty())
				nom = "Nome='" + Nome + "'";
			if (!Cognome.isEmpty()) {
				cog = "Cognome='" + Cognome + "'";
				if (!nom.isEmpty())
					cog = "AND " + cog;
			}
			if (!data.isEmpty()) {
				dat = "`Data di Nascita`='" + data + "'";
				if (!nom.isEmpty() || !cog.isEmpty())
					dat = "AND " + dat;
			}
			String query = "Select * from clienti where " + nom + " " + cog + " " + dat;
			Statement st = connect.createStatement();
			ResultSet res;
			res = st.executeQuery(query);

			while (res.next()) {

				dett = new EntityCliente();
				dett.setCF(res.getString("CF"));
				dett.setCAP(res.getString("Cognome"));
				dett.setCell(res.getString("Tel"));
				dett.setCognome(res.getString("Cognome"));
				dett.setData(sdf2.format(df.parse(res.getString("Data di Nascita"))));
				dett.setDomi(res.getString("Indirizzo"));
				dett.setEmail(res.getString("email"));
				dett.setNome(res.getString("Nome"));
				dett.setNote(res.getString("note"));
				dett.setCAP(res.getString("Cap"));
				dett.setLuogodiNascita(res.getString("Comune di Nascita"));

				cliente.add(dett);

			}

			st.close();
			res.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(cliente.size());
		return cliente;

	}

	public ArrayList<EntityCliente> getClienti() {
		ArrayList<EntityCliente> cliente = new ArrayList<EntityCliente>();
		EntityCliente dett = null;
		try {
			Connection connect = DBManager.getInstance().getConnection();
			Statement stmt = connect.createStatement();
			String query = "select distinct * from clienti";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {

				dett = new EntityCliente();
				dett.setCF(res.getString("CF"));
				dett.setCAP(res.getString("Cognome"));
				dett.setCell(res.getString("Tel"));
				dett.setCognome(res.getString("Cognome"));
				dett.setData(sdf2.format(df.parse(res.getString("Data di Nascita"))));
				dett.setDomi(res.getString("Indirizzo"));
				dett.setEmail(res.getString("email"));
				dett.setNome(res.getString("Nome"));
				dett.setNote(res.getString("note"));
				dett.setCAP(res.getString("Cap"));
				dett.setLuogodiNascita(res.getString("Comune di Nascita"));

				cliente.add(dett);

			}

			stmt.close();
			res.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}

	public ArrayList<Object> autoCompleteClienti(String Tipologia, String cerca) throws RemoteException, SQLException {
		ArrayList<Object> risultato = new ArrayList<>();
		String query = "";
		if (Tipologia.equals("cf")) {
			query = "select cf from ph2z7xhp182hcqva.clienti where cf like '" + cerca + "%'";
		} else if (Tipologia.equals("nome")) {
			query = "select distinct nome from ph2z7xhp182hcqva.clienti where nome like '" + cerca + "%'";
		} else if (Tipologia.equals("cognome")) {
			query = "select distinct cognome from ph2z7xhp182hcqva.clienti where cognome like '" + cerca + "%'";
		} else if (Tipologia.equals("tipoVeicolo")) {
			query = "select distinct `Tipo Veicolo` from ph2z7xhp182hcqva.polizza where `Tipo Veicolo` like '" + cerca
					+ "%'";
		}
		try {
			Connection connect = DBManager.getInstance().getConnection();
			Statement stmt = connect.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				risultato.add(res.getString(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Eccezione: " + e);
		}

		return risultato;

	}

	public int deleteCliente(String id_cliente) {

		try {
			PreparedStatement st = DBManager.getInstance().getConnection()
					.prepareStatement("delete from ph2z7xhp182hcqva.clienti where CF =?");

			st.setString(1, id_cliente);
			st.executeUpdate();
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return -2;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
}