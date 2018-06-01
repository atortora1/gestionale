package interfaces_rmi;

import java.io.Serializable;

public class Cliente implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 3015580134942096632L;
	/**
	* 
	*/
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
}
