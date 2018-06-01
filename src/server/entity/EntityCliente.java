package server.entity;

import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

import server.persistenza.ClientiDAO;
import server.persistenza.PolizzaDAO;

public class EntityCliente implements Serializable {
	private String CF;
	private String nome;
	private String cognome;
	private String email;
	private String cellulare;
	private String domicilio;
	private String CAP;
	private String LuogodiNascita;
	private String data_nascita;
	private String note;
	private int[] id_polizza;
	private int numero_polizze_pers;

	public int getNumero_polizze_pers() {
		return numero_polizze_pers;
	}

	public void setNumero_polizze_pers(int numero_polizze_pers) {
		this.numero_polizze_pers = numero_polizze_pers;
	}

	public int[] getId_polizza() {
		return id_polizza;
	}

	public void setId_polizza(int[] id_polizza) {
		this.id_polizza = id_polizza;
	}

	public void setCF(String CF) {
		this.CF = CF;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCell(String i) {
		this.cellulare = i;
	}

	public void setDomi(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setCAP(String cap) {
		this.CAP = cap;
	}

	public void setData(String data) {
		this.data_nascita = data;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCF() {
		return CF;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}

	public String getCell() {
		return cellulare;
	}

	public String getDomi() {
		return domicilio;
	}

	public String getCAP() {
		return CAP;
	}

	public String getData() {
		return data_nascita;
	}

	public String getLuogodiNascita() {
		return LuogodiNascita;
	}

	public void setLuogodiNascita(String luogodiNascita) {
		LuogodiNascita = luogodiNascita;
	}

	public boolean InserisciCliente(String CF, String Nome, String Cognome, String citta, String indirizzo,
			String email, String cap, String cell, String data, String note) {
		this.setCF(CF);
		this.setCAP(cap);
		this.setNome(Nome);
		this.setCognome(Cognome);
		this.setDomi(indirizzo);
		this.setEmail(email);
		this.setLuogodiNascita(citta);
		this.setData(data);
		this.setNote(note);

		ClientiDAO inserisciClienti = new ClientiDAO();
		return inserisciClienti.InserisciCliente(CF, Nome, Cognome, citta, indirizzo, email, cap, cell, data, note);

	}

	public boolean updateCliente(String CF, String Nome, String Cognome, String citta, String indirizzo, String email,
			String cap, String cell, String data, String note) {
		this.setCF(CF);
		this.setCAP(cap);
		this.setNome(Nome);
		this.setCognome(Cognome);
		this.setDomi(indirizzo);
		this.setEmail(email);
		this.setLuogodiNascita(citta);
		this.setData(data);
		this.setNote(note);

		ClientiDAO updateClienti = new ClientiDAO();
		return updateClienti.updateCliente(CF, Nome, Cognome, citta, indirizzo, email, cap, cell, data, note);

	}

	public ArrayList<EntityCliente> getClienti(String Nome, String Cognome, String data) {
		ClientiDAO ricercaClienti = new ClientiDAO();
		return ricercaClienti.getClienti(Nome, Cognome, data);
	}

	public ArrayList<EntityCliente> getClienti() {
		ClientiDAO ricercaClienti = new ClientiDAO();
		return ricercaClienti.getClienti();
	}

	public ArrayList<Object> autoCompleteClienti(String Tipologia, String cerca) throws RemoteException, SQLException {
		ClientiDAO ricercaClienti = new ClientiDAO();
		return ricercaClienti.autoCompleteClienti(Tipologia, cerca);
	}

	public int deleteCliente(String id_cliente)
			throws AccessException, RemoteException, NotBoundException, SQLException {

		ClientiDAO clienti = new ClientiDAO();
		return clienti.deleteCliente(id_cliente);
	}
}
