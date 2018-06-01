package interfaces_rmi;

import java.io.Serializable;

public class Comuni implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -5820953333825011535L;
	private String nome = "";

	public Comuni() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
