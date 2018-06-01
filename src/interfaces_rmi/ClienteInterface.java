package interfaces_rmi;

import java.awt.HeadlessException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface ClienteInterface extends Remote {

	public boolean inserisciCliente(String CF, String Nome, String Cognome, String citta, String indirizzo,
			String email, String cap, String cell, String data, String note) throws RemoteException;

	public String calcolaCF(String nome, String cognome, int giorno, int mese, int anno, String sesso,
			String comuneDiNascita) throws RemoteException;

	public ArrayList<Object> getNomeComuni(String cerca) throws RemoteException, SQLException;

	public boolean updateCliente(String CF, String Nome, String Cognome, String citta, String indirizzo, String email,
			String cap, String cell, String data, String note) throws RemoteException;

	public ArrayList<Cliente> getClienti(String Nome, String Cognome, String data) throws RemoteException;

	public ArrayList<Cliente> getClienti() throws RemoteException, SQLException;

	public int inserisciPolizza(String CF, String NomeCompagnia, String TipoVeicolo, String NumeroPraticaAgenzia,
			String data_inizio, String data_fine, String note, String Targa, String importo, String NumeroPolizza,
			String Referente, String Rateazione, String data_rinnovo, byte[] bFile)
			throws RemoteException, SQLException;

	public ArrayList<PolizzaClienti> getPolizza(String CF, String numeroPolizza, String Targa)
			throws RemoteException, HeadlessException, SQLException;

	public ArrayList<PolizzaClienti> getTrovaStorico(String CF, String Targa, String idPolizza) throws RemoteException;

	public int inserisciStoricoPolizza(int id_polizza, String CF, String NomeCompagnia, String TipoVeicolo,
			String NumeroPraticaAgenzia, String data_inizio, String data_fine, String note, String Targa,
			String importo, String NumeroPolizza, String Referente, String Rateazione, String data_rinnovo)
			throws RemoteException, SQLException;

	public int updatePolizza(String CF, String NomeCompagnia, String TipoVeicolo, String NumeroPraticaAgenzia,
			String data_inizio, String data_fine, String note, String Targa, String importo, String NumeroPolizza,
			String Referente, String Rateazione, String data_rinnovo, String TargaPrec, String data_sospensione,
			String TipologiaUpdate, byte[] bFile)
			throws AccessException, RemoteException, NotBoundException, SQLException;

	public ArrayList<PolizzaClienti> trovaScandenze(int data_1, int data_2)
			throws RemoteException, SQLException, ParseException;

	public ArrayList<Object> autoCompleteClienti(String Tipologia, String cerca) throws RemoteException, SQLException;

	public byte[] getDocumenti(int id_polizza) throws AccessException, RemoteException, NotBoundException, SQLException;

	public int deletePolizza(int id_polizza) throws AccessException, RemoteException, NotBoundException, SQLException;

	public int deleteCliente(String id_cliente)
			throws AccessException, RemoteException, NotBoundException, SQLException;
}
