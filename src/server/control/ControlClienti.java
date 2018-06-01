package server.control;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import interfaces_rmi.Cliente;
import interfaces_rmi.Comuni;
import server.entity.EntityCliente;
import server.entity.EntityComuni;
import server.entity.EntityPolizza;

public class ControlClienti {

	public boolean InserisciCliente(String CF, String Nome, String Cognome, String citta, String indirizzo,
			String email, String cap, String cell, String data, String note) {
		boolean inserito = false;
		if (inserito == false) {
			EntityCliente c = new EntityCliente();
			inserito = c.InserisciCliente(CF, Nome, Cognome, citta, indirizzo, email, cap, cell, data, note);

		}
		return inserito;
	}

	public ArrayList<Object> getNomeComuni(String cerca) throws SQLException {
		// EntityComuni nomeComuni = new EntityComuni();

		ArrayList<Object> returnComuni = new ArrayList<>();
		// for(Object t: nomeComuni.getNomeComuni(cerca) ){
		// Comuni comuni = new Comuni();
		// comuni.setNome(t.getNome());
		// returnComuni.add(comuni);
		// }
		EntityComuni com = new EntityComuni();
		returnComuni = com.getNomeComuni(cerca);
		return returnComuni;
	}

	public boolean updateCliente(String CF, String Nome, String Cognome, String citta, String indirizzo, String email,
			String cap, String cell, String data, String note) {
		boolean inserito = false;
		if (inserito == false) {
			EntityCliente c = new EntityCliente();
			inserito = c.updateCliente(CF, Nome, Cognome, citta, indirizzo, email, cap, cell, data, note);

		}
		return inserito;
	}

	public ArrayList<Cliente> getClienti(String Nome, String Cognome, String data) throws SQLException {
		EntityCliente clienti = new EntityCliente();

		ArrayList<Cliente> returnClienti = new ArrayList<>();
		for (EntityCliente t : clienti.getClienti(Nome, Cognome, data)) {
			Cliente cliente = new Cliente();
			cliente.setNome(t.getNome());
			cliente.setCognome(t.getCognome());
			cliente.setCF(t.getCF());
			cliente.setCAP(t.getCAP());
			cliente.setCell(t.getCell());
			cliente.setData(t.getData());
			cliente.setDomi(t.getDomi());
			cliente.setEmail(t.getEmail());
			cliente.setLuogodiNascita(t.getLuogodiNascita());
			cliente.setNote(t.getNote());

			returnClienti.add(cliente);
		}
		return returnClienti;
	}

	public ArrayList<Cliente> getClienti() throws SQLException {
		EntityCliente clienti = new EntityCliente();
		ArrayList<Cliente> returnClienti = new ArrayList<>();
		for (EntityCliente t : clienti.getClienti()) {
			Cliente cliente = new Cliente();
			cliente.setNome(t.getNome());
			cliente.setCognome(t.getCognome());
			cliente.setCF(t.getCF());
			cliente.setCAP(t.getCAP());
			cliente.setCell(t.getCell());
			cliente.setData(t.getData());
			cliente.setDomi(t.getDomi());
			cliente.setEmail(t.getEmail());
			cliente.setLuogodiNascita(t.getLuogodiNascita());
			cliente.setNote(t.getNote());

			returnClienti.add(cliente);
		}
		return returnClienti;
	}

	public ArrayList<Object> autoCompleteClienti(String Tipologia, String cerca) throws RemoteException, SQLException {
		EntityCliente cliente = new EntityCliente();
		return cliente.autoCompleteClienti(Tipologia, cerca);
	}

	public int deleteCliente(String id_cliente)
			throws AccessException, RemoteException, NotBoundException, SQLException {

		EntityCliente p = new EntityCliente();
		return p.deleteCliente(id_cliente);

	}
}
