package server.entity;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import server.persistenza.ComuniDAO;

public class EntityComuni implements Serializable {

	private String nome = "";

	public EntityComuni() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Object> getNomeComuni(String cerca) throws SQLException {
		ComuniDAO comuni = new ComuniDAO();
		return comuni.ritornoComuni(cerca);
	}
}
