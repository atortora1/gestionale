package interfaces_rmi;

import java.io.Serializable;

public class PolizzaClienti implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2264635602097126500L;
	public Cliente datiCliente = new Cliente();
	public Polizza datiPolizza = new Polizza();

	public PolizzaClienti(Cliente cliente, Polizza polizza) {

		datiCliente = cliente;
		datiPolizza = polizza;
	}

}
