package server.proxy;

import java.awt.HeadlessException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import interfaces_rmi.Cliente;
import interfaces_rmi.ClienteInterface;
//import interfaces_rmi.Comuni;
import interfaces_rmi.PolizzaClienti;
import server.control.ControlClienti;
import server.control.ControlPolizza;
import server.control.GeneratoreCF;

public class ClienteProxy extends UnicastRemoteObject implements ClienteInterface {

	protected ClienteProxy() throws RemoteException {
		super();

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5072757658926104883L;

	@Override
	public boolean inserisciCliente(String CF, String Nome, String Cognome, String citta, String indirizzo,
			String email, String cap, String cell, String data, String note) throws RemoteException {

		boolean inserito = false;
		ControlClienti cr = new ControlClienti();
		inserito = cr.InserisciCliente(CF, Nome, Cognome, citta, indirizzo, email, cap, cell, data, note);

		return inserito;
	}

	@Override
	public String calcolaCF(String nome, String cognome, int giorno, int mese, int anno, String sesso,
			String comuneDiNascita) throws RemoteException {
		GeneratoreCF calcolaCF = new GeneratoreCF();
		String result = "";
		try {
			result = calcolaCF.calcolaCF(nome, cognome, giorno, mese, anno, sesso, comuneDiNascita);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Object> getNomeComuni(String cerca) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		ControlClienti cr = new ControlClienti();

		return cr.getNomeComuni(cerca);
	}

	@Override
	public boolean updateCliente(String CF, String Nome, String Cognome, String citta, String indirizzo, String email,
			String cap, String cell, String data, String note) throws RemoteException {
		ControlClienti cr = new ControlClienti();
		return cr.updateCliente(CF, Nome, Cognome, citta, indirizzo, email, cap, cell, data, note);

	}

	@Override
	public ArrayList<Cliente> getClienti(String Nome, String Cognome, String data) throws RemoteException {
		ControlClienti cr = new ControlClienti();
		ArrayList<Cliente> array = null;
		try {
			array = cr.getClienti(Nome, Cognome, data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;

	}

	@Override
	public ArrayList<Cliente> getClienti() throws RemoteException, SQLException {
		ControlClienti cr = new ControlClienti();

		return cr.getClienti();

	}

	@Override
	public int inserisciPolizza(String CF, String NomeCompagnia, String TipoVeicolo, String NumeroPraticaAgenzia,
			String data_inizio, String data_fine, String note, String Targa, String importo, String NumeroPolizza,
			String Referente, String Rateazione, String data_rinnovo, byte[] bFile)
			throws RemoteException, SQLException {

		ControlPolizza cr = new ControlPolizza();
		return cr.inserisciPolizza(CF, NomeCompagnia, TipoVeicolo, NumeroPraticaAgenzia, data_inizio, data_fine, note,
				Targa, importo, NumeroPolizza, Referente, Rateazione, data_rinnovo, bFile);

	}

	@Override
	public ArrayList<PolizzaClienti> getPolizza(String CF, String numeroPolizza, String Targa)
			throws RemoteException, HeadlessException, SQLException {
		ControlPolizza cr = new ControlPolizza();
		// JOptionPane.showMessageDialog(null, "Cliente Proxy "+cr.getPolizza(CF,
		// numeroPolizza, Targa).size());
		return cr.getPolizza(CF, numeroPolizza, Targa);
	}

	@Override
	public ArrayList<PolizzaClienti> getTrovaStorico(String CF, String Targa, String idPolizza) throws RemoteException {
		// TODO Auto-generated method stub
		ControlPolizza cr = new ControlPolizza();
		return cr.getTrovaStorico(CF, Targa, idPolizza);
	}

	@Override
	public int inserisciStoricoPolizza(int id_polizza, String CF, String NomeCompagnia, String TipoVeicolo,
			String NumeroPraticaAgenzia, String data_inizio, String data_fine, String note, String Targa,
			String importo, String NumeroPolizza, String Referente, String Rateazione, String data_rinnovo)
			throws RemoteException, SQLException {
		ControlPolizza cr = new ControlPolizza();
		return cr.inserisciStoricoPolizza(id_polizza, CF, NomeCompagnia, TipoVeicolo, NumeroPraticaAgenzia, data_inizio,
				data_fine, note, Targa, importo, NumeroPolizza, Referente, Rateazione, data_rinnovo);
	}

	@Override
	public int updatePolizza(String CF, String NomeCompagnia, String TipoVeicolo, String NumeroPraticaAgenzia,
			String data_inizio, String data_fine, String note, String Targa, String importo, String NumeroPolizza,
			String Referente, String Rateazione, String data_rinnovo, String TargaPrec, String data_sospensione,
			String TipologiaUpdate, byte[] bFile)
			throws AccessException, RemoteException, NotBoundException, SQLException {
		ControlPolizza cr = new ControlPolizza();
		return cr.updatePolizza(CF, NomeCompagnia, TipoVeicolo, NumeroPraticaAgenzia, data_inizio, data_fine, note,
				Targa, importo, NumeroPolizza, Referente, Rateazione, data_rinnovo, TargaPrec, data_sospensione,
				TipologiaUpdate, bFile);
	}

	@Override
	public ArrayList<PolizzaClienti> trovaScandenze(int data_1, int data_2)
			throws RemoteException, SQLException, ParseException {
		ControlPolizza cr = new ControlPolizza();

		return cr.trovaScandenze(data_1, data_2);
	}

	@Override
	public ArrayList<Object> autoCompleteClienti(String Tipologia, String cerca) throws RemoteException, SQLException {
		ControlClienti c = new ControlClienti();
		return c.autoCompleteClienti(Tipologia, cerca);
	}

	@Override
	public byte[] getDocumenti(int id_polizza)
			throws AccessException, RemoteException, NotBoundException, SQLException {
		ControlPolizza cr = new ControlPolizza();
		return cr.getDocumenti(id_polizza);
	}

	@Override
	public int deletePolizza(int id_polizza) throws AccessException, RemoteException, NotBoundException, SQLException {
		ControlPolizza cr = new ControlPolizza();
		return cr.deletePolizza(id_polizza);
	}

	@Override
	public int deleteCliente(String id_cliente)
			throws AccessException, RemoteException, NotBoundException, SQLException {
		ControlClienti cr = new ControlClienti();
		return cr.deleteCliente(id_cliente);
	}

}
