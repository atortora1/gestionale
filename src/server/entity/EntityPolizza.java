package server.entity;

import java.awt.HeadlessException;
import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import interfaces_rmi.PolizzaClienti;
import server.persistenza.PolizzaDAO;

public class EntityPolizza implements Serializable {

	private String numero_polizza;
	private String nome_compagnia;
	private String data_inizio;
	private String data_fine;
	private String rateazione;
	private String numero_pratica;
	private String targa;
	private String tipo_veicolo;
	private String importo;
	private String nota;
	private String id_cliente;
	private String referente;
	private String data_rinnovo;
	private String data_sospensione;
	private int id_polizza;
	private String Stato;
	private byte[] bFile;

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNumero_polizza() {
		return numero_polizza;
	}

	public void setNumero_polizza(String numero_polizza) {
		this.numero_polizza = numero_polizza;
	}

	public String getNome_compagnia() {
		return nome_compagnia;
	}

	public void setNome_compagnia(String nome_compagnia) {
		this.nome_compagnia = nome_compagnia;
	}

	public String getData_inizio() {
		return data_inizio;
	}

	public void setData_inizio(String data_inizio) {
		this.data_inizio = data_inizio;
	}

	public String getData_fine() {
		return data_fine;
	}

	public void setData_fine(String data_fine) {
		this.data_fine = data_fine;
	}

	public String getRateazione() {
		return rateazione;
	}

	public void setRateazione(String rateazione) {
		this.rateazione = rateazione;
	}

	public String getNumero_pratica() {
		return numero_pratica;
	}

	public void setNumero_pratica(String numero_pratica) {
		this.numero_pratica = numero_pratica;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getTipo_veicolo() {
		return tipo_veicolo;
	}

	public void setTipo_veicolo(String tipo_veicolo) {
		this.tipo_veicolo = tipo_veicolo;
	}

	public String getImporto() {
		return importo;
	}

	public void setImporto(String importo) {
		this.importo = importo;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getReferente() {
		return referente;
	}

	public void setReferente(String referente) {
		this.referente = referente;
	}

	public String getData_rinnovo() {
		return data_rinnovo;
	}

	public void setData_rinnovo(String data_rinnovo) {
		this.data_rinnovo = data_rinnovo;
	}

	public String getData_sospensione() {
		return data_sospensione;
	}

	public void setData_sospensione(String data_sospensione) {
		this.data_sospensione = data_sospensione;
	}

	public String getStato() {
		return Stato;
	}

	public void setStato(String stato) {
		Stato = stato;
	}

	public int getId_polizza() {
		return id_polizza;
	}

	public void setId_polizza(int id_polizza) {
		this.id_polizza = id_polizza;
	}

	public byte[] getbFile() {
		return bFile;
	}

	public void setbFile(byte[] bFile) {
		this.bFile = bFile;
	}

	public int inserisciPolizza(String CF, String NomeCompagnia, String TipoVeicolo, String NumeroPraticaAgenzia,
			String data_inizio, String data_fine, String note, String Targa, String importo, String NumeroPolizza,
			String Referente, String Rateazione, String data_rinnovo, byte[] bFile) throws SQLException {
		setNome_compagnia(NomeCompagnia);
		setData_inizio(data_inizio);
		setData_fine(data_fine);
		setId_cliente(CF);
		setTipo_veicolo(TipoVeicolo);
		setTarga(Targa);
		setNumero_pratica(NumeroPraticaAgenzia);
		setNumero_polizza(NumeroPolizza);
		setNota(note);
		setImporto(importo);
		setRateazione(Rateazione);
		setReferente(Referente);
		setData_rinnovo(data_rinnovo);

		PolizzaDAO polizza = new PolizzaDAO();
		return polizza.inserisciPolizza(CF, NomeCompagnia, TipoVeicolo, NumeroPraticaAgenzia, data_inizio, data_fine,
				note, Targa, importo, NumeroPolizza, Referente, Rateazione, data_rinnovo, bFile);
	}

	public ArrayList<PolizzaClienti> getPolizza(String CF, String numeroPolizza, String Targa)
			throws RemoteException, SQLException, HeadlessException {
		PolizzaDAO polizza = new PolizzaDAO();
		return polizza.getPolizza(CF, numeroPolizza, Targa);
	}

	public ArrayList<PolizzaClienti> getTrovaStorico(String CF, String Targa, String idPolizza) throws RemoteException {
		PolizzaDAO polizza = new PolizzaDAO();
		return polizza.getTrovaStorico(CF, Targa, idPolizza);
	}

	public int inserisciStoricoPolizza(int id_polizza, String CF, String NomeCompagnia, String TipoVeicolo,
			String NumeroPraticaAgenzia, String data_inizio, String data_fine, String note, String Targa,
			String importo, String NumeroPolizza, String Referente, String Rateazione, String data_rinnovo)
			throws SQLException {
		/*
		 * setNome_compagnia(NomeCompagnia); setData_inizio(data_inizio);
		 * setData_fine(data_fine); setId_cliente(CF); setTipo_veicolo(TipoVeicolo);
		 * setTarga(Targa); setNumero_pratica(NumeroPraticaAgenzia);
		 * setNumero_polizza(NumeroPolizza); setNota(note); setImporto(importo);
		 * setRateazione(Rateazione); setReferente(Referente);
		 * setData_rinnovo(data_rinnovo);
		 */

		PolizzaDAO polizza = new PolizzaDAO();
		return polizza.inserisciStoricoPolizza(id_polizza, CF, NomeCompagnia, TipoVeicolo, NumeroPraticaAgenzia,
				data_inizio, data_fine, note, Targa, importo, NumeroPolizza, Referente, Rateazione, data_rinnovo);
	}

	public int updatePolizza(String CF, String NomeCompagnia, String TipoVeicolo, String NumeroPraticaAgenzia,
			String data_inizio, String data_fine, String note, String Targa, String importo, String NumeroPolizza,
			String Referente, String Rateazione, String data_rinnovo, String TargaPrec, String data_sospensione,
			String TipologiaUpdate, byte[] bFile)
			throws AccessException, RemoteException, NotBoundException, SQLException {

		PolizzaDAO polizza = new PolizzaDAO();
		return polizza.updatePolizza(CF, NomeCompagnia, TipoVeicolo, NumeroPraticaAgenzia, data_inizio, data_fine, note,
				Targa, importo, NumeroPolizza, Referente, Rateazione, data_rinnovo, TargaPrec, data_sospensione,
				TipologiaUpdate, bFile);
	}

	public ArrayList<PolizzaClienti> trovaScandenze(int data_1, int data_2)
			throws RemoteException, SQLException, ParseException {
		PolizzaDAO polizza = new PolizzaDAO();

		return polizza.trovaScandenze(data_1, data_2);
	}

	public byte[] getDocumenti(int id_polizza) {
		PolizzaDAO polizza = new PolizzaDAO();

		return polizza.getDocumenti(id_polizza);
	}

	public int deletePolizza(int id_polizza2) throws AccessException, RemoteException, NotBoundException, SQLException {

		PolizzaDAO polizza = new PolizzaDAO();
		return polizza.deletePolizza(id_polizza2);
	}

}
