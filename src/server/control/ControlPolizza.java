package server.control;

import java.awt.HeadlessException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import interfaces_rmi.PolizzaClienti;
import server.entity.EntityPolizza;;

public class ControlPolizza {
	public int inserisciPolizza(String CF, String NomeCompagnia, String TipoVeicolo, String NumeroPraticaAgenzia,
			String data_inizio, String data_fine, String note, String Targa, String importo, String NumeroPolizza,
			String Referente, String Rateazione, String data_rinnovo, byte[] bFile) throws SQLException {

		EntityPolizza p = new EntityPolizza();
		return p.inserisciPolizza(CF, NomeCompagnia, TipoVeicolo, NumeroPraticaAgenzia, data_inizio, data_fine, note,
				Targa, importo, NumeroPolizza, Referente, Rateazione, data_rinnovo, bFile);

	}

	public ArrayList<PolizzaClienti> getPolizza(String CF, String numeroPolizza, String Targa)
			throws RemoteException, SQLException, HeadlessException {
		EntityPolizza p = new EntityPolizza();
		// JOptionPane.showMessageDialog(null, "Control Polizza "+ p.getPolizza(CF,
		// numeroPolizza, Targa).size());
		return p.getPolizza(CF, numeroPolizza, Targa);
	}

	public ArrayList<PolizzaClienti> getTrovaStorico(String CF, String Targa, String idPolizza) throws RemoteException {
		EntityPolizza p = new EntityPolizza();
		return p.getTrovaStorico(CF, Targa, idPolizza);
	}

	public int inserisciStoricoPolizza(int id_polizza, String CF, String NomeCompagnia, String TipoVeicolo,
			String NumeroPraticaAgenzia, String data_inizio, String data_fine, String note, String Targa,
			String importo, String NumeroPolizza, String Referente, String Rateazione, String data_rinnovo)
			throws SQLException {

		EntityPolizza p = new EntityPolizza();
		return p.inserisciStoricoPolizza(id_polizza, CF, NomeCompagnia, TipoVeicolo, NumeroPraticaAgenzia, data_inizio,
				data_fine, note, Targa, importo, NumeroPolizza, Referente, Rateazione, data_rinnovo);

	}

	public int updatePolizza(String CF, String NomeCompagnia, String TipoVeicolo, String NumeroPraticaAgenzia,
			String data_inizio, String data_fine, String note, String Targa, String importo, String NumeroPolizza,
			String Referente, String Rateazione, String data_rinnovo, String TargaPrec, String data_sospensione,
			String TipologiaUpdate, byte[] bFile)
			throws AccessException, RemoteException, NotBoundException, SQLException {

		EntityPolizza p = new EntityPolizza();
		return p.updatePolizza(CF, NomeCompagnia, TipoVeicolo, NumeroPraticaAgenzia, data_inizio, data_fine, note,
				Targa, importo, NumeroPolizza, Referente, Rateazione, data_rinnovo, TargaPrec, data_sospensione,
				TipologiaUpdate, bFile);

	}

	public ArrayList<PolizzaClienti> trovaScandenze(int data_1, int data_2)
			throws RemoteException, SQLException, ParseException {
		EntityPolizza p = new EntityPolizza();

		return p.trovaScandenze(data_1, data_2);
	}

	public byte[] getDocumenti(int id_polizza)
			throws AccessException, RemoteException, NotBoundException, SQLException {
		EntityPolizza p = new EntityPolizza();
		return p.getDocumenti(id_polizza);
	}

	public int deletePolizza(int id_polizza) throws AccessException, RemoteException, NotBoundException, SQLException {

		EntityPolizza p = new EntityPolizza();
		return p.deletePolizza(id_polizza);

	}

}
