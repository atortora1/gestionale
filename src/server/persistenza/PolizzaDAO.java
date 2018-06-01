package server.persistenza;

import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import interfaces_rmi.Cliente;
import interfaces_rmi.Polizza;
import interfaces_rmi.PolizzaClienti;

public class PolizzaDAO {
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy");
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private String query = "";
	ByteArrayInputStream bais;

	public int inserisciPolizza(String CF, String NomeCompagnia, String TipoVeicolo, String NumeroPraticaAgenzia,
			String data_inizio, String data_fine, String note, String Targa, String importo, String NumeroPolizza,
			String Referente, String Rateazione, String data_rinnovo, byte[] bFile) throws SQLException {

		try {

			Connection connect = DBManager.getInstance().getConnection();
			String query = "insert into ph2z7xhp182hcqva.polizza (`CF_cliente`,`Nome Compagnia`,`Tipo Veicolo`,`Numero Pratica in registro`,`data_inizio`,`data_fine`,`note`,`Targa`,`Importo`,`NumeroPolizza`,Referente,Rateazione,data_pagamento,Stato,documenti) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement pat = connect.prepareStatement(query);
			pat.setString(1, CF.toUpperCase());
			pat.setString(2, NomeCompagnia.toUpperCase());
			pat.setString(3, TipoVeicolo.toUpperCase());
			pat.setString(4, NumeroPraticaAgenzia.toUpperCase());
			pat.setString(5, data_inizio);
			pat.setString(6, data_fine);
			pat.setString(7, note);
			pat.setString(8, Targa.toUpperCase());
			pat.setString(9, importo);
			pat.setString(10, NumeroPolizza.toUpperCase());
			pat.setString(11, Referente.toUpperCase());
			pat.setString(12, Rateazione);
			pat.setString(14, "ATTIVA");
			pat.setString(13, data_rinnovo);
			if (bFile != null) {
				bais = new ByteArrayInputStream(bFile);
				pat.setBlob(15, bais);
			} else {
				pat.setNull(15, java.sql.Types.BLOB);
			}
			pat.execute();
			pat.close();

		} catch (SQLIntegrityConstraintViolationException c) {
			c.printStackTrace();
			return -1;

		}
		return 1;

	}

	public int inserisciStoricoPolizza(int id_polizza, String CF, String NomeCompagnia, String TipoVeicolo,
			String NumeroPraticaAgenzia, String data_inizio, String data_fine, String note, String Targa,
			String importo, String NumeroPolizza, String Referente, String Rateazione, String data_rinnovo)
			throws SQLException {
		try {
			String query = "Insert into ph2z7xhp182hcqva.storico_polizza (id_polizza, cf_cliente, importo, rateazione, note, data_scadenza_semestre, data_inizio_contratto, data_fine_contratto,targa,nomecomp,tipov) values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pat;
			pat = DBManager.getInstance().getConnection().prepareStatement(query);
			pat.setInt(1, id_polizza);
			pat.setString(2, CF.toUpperCase());
			pat.setString(3, importo);
			pat.setString(4, Rateazione);
			// pat.setString(6,
			// sdf.format(df2.parse(data_rinnovo)));
			pat.setString(6, data_rinnovo);
			pat.setString(7, data_inizio);
			pat.setString(8, data_fine);
			pat.setString(9, Targa.toUpperCase());
			pat.setString(10, NomeCompagnia.toUpperCase());
			pat.setString(11, TipoVeicolo.toUpperCase());
			// if(data_fine_contr.equals(data_rinnovo)){
			// pat.setString(5, "Il cliente ha
			// rinnovato il contratto numero:
			// "+NUMEROCONTRATTO+" scaduto in
			// data: "+data_fine_contr);
			// }else{
			// pat.setString(5,"Il cliente ha
			// rinnovato il pagamento semestrale
			// della polizza numero:
			// "+NUMEROCONTRATTO+" scaduto in
			// data:
			// "+sdf.format(df2.parse(data_rinnovo)));
			pat.setString(5, note);
			// }
			pat.execute();
			pat.close();

		} catch (SQLException c) {
			c.printStackTrace();
			return -1;

		}
		return 1;

	}

	public ArrayList<PolizzaClienti> getPolizza(String CF, String numeroPolizza, String Targa)
			throws RemoteException, HeadlessException, SQLException {

		String cf, numPolizza, targa;
		cf = numPolizza = targa = "";
		ArrayList<PolizzaClienti> polizza = new ArrayList<>();
		Connection connect = DBManager.getInstance().getConnection();
		Polizza dett = null;

		if (!CF.isEmpty())
			cf = "ph2z7xhp182hcqva.polizza.CF_cliente='" + CF.toUpperCase() + "'";
		if (!numeroPolizza.isEmpty())
			numPolizza = "ph2z7xhp182hcqva.polizza.NumeroPolizza='" + numeroPolizza.toUpperCase() + "'";

		if (!Targa.isEmpty()) {
			targa = "ph2z7xhp182hcqva.polizza.Targa='" + Targa.toUpperCase() + "'";
			if (!numPolizza.isEmpty())
				targa = "AND " + targa;
		}
		try {

			// JOptionPane.showMessageDialog(null, "Server: sono nel try");
			String queryPolizza = "Select distinct ph2z7xhp182hcqva.polizza.CF_cliente,ph2z7xhp182hcqva.clienti.nome,ph2z7xhp182hcqva.clienti.cognome, ph2z7xhp182hcqva.polizza.`Nome Compagnia`, polizza.`Tipo Veicolo`,"
					+ "ph2z7xhp182hcqva.polizza.`Numero Pratica in Registro`,"
					+ "ph2z7xhp182hcqva.polizza.data_inizio,ph2z7xhp182hcqva.polizza.data_fine,ph2z7xhp182hcqva.polizza.note,ph2z7xhp182hcqva.polizza.Targa,ph2z7xhp182hcqva.polizza.Importo,ph2z7xhp182hcqva.polizza.`NumeroPolizza`,"
					+ "ph2z7xhp182hcqva.polizza.Referente,ph2z7xhp182hcqva.polizza.Rateazione,ph2z7xhp182hcqva.polizza.idpolizza,ph2z7xhp182hcqva.polizza.data_pagamento,ph2z7xhp182hcqva.polizza.Stato,ph2z7xhp182hcqva.polizza.data_sospensione,ph2z7xhp182hcqva.polizza.documenti\n"
					+ "from ph2z7xhp182hcqva.polizza\n"
					+ "Inner Join ph2z7xhp182hcqva.clienti  on ph2z7xhp182hcqva.polizza.CF_cliente = ph2z7xhp182hcqva.clienti.CF\n"
					+ "where " + cf + " " + numPolizza + " " + targa
					+ " AND (ph2z7xhp182hcqva.polizza.Stato = 'ATTIVA' OR ph2z7xhp182hcqva.polizza.Stato = 'SOSPESA' )";
			Statement stPolizza = connect.createStatement();
			ResultSet rsPolizza = stPolizza.executeQuery(queryPolizza);
			if (!rsPolizza.isBeforeFirst()) {
				// JOptionPane.showMessageDialog(null, "Server: é vuota");
				return null;
			} else {
				while (rsPolizza.next()) {
					Cliente clientePolizza = new Cliente();

					clientePolizza.setNome(rsPolizza.getString(2));
					clientePolizza.setCognome(rsPolizza.getString(3));

					dett = new Polizza();
					dett.setId_polizza(rsPolizza.getInt(15));
					dett.setId_cliente(rsPolizza.getString(1));
					dett.setNome_compagnia(rsPolizza.getString(4));
					dett.setTipo_veicolo(rsPolizza.getString(5));
					dett.setNumero_pratica(rsPolizza.getString(6));
					dett.setData_inizio(sdf2.format(df.parse(rsPolizza.getString(7))));
					dett.setData_fine(sdf2.format(df.parse(rsPolizza.getString(8))));
					dett.setNota(rsPolizza.getString(9));
					dett.setTarga(rsPolizza.getString(10));
					dett.setImporto(rsPolizza.getString(11));
					dett.setNumero_polizza(rsPolizza.getString(12));
					dett.setReferente(rsPolizza.getString(13));
					dett.setRateazione(rsPolizza.getString(14));

					dett.setStato(rsPolizza.getString(17));

					dett.setData_rinnovo(sdf2.format(df.parse(rsPolizza.getString(16))));
					if (rsPolizza.getString(18) != null)
						dett.setData_sospensione(sdf2.format(df.parse(rsPolizza.getString(18))));
					else
						dett.setData_sospensione(rsPolizza.getString(18));
					// dett.setbFile(rsPolizza.getBytes(19));
					// polizza.add(dett);
					PolizzaClienti risulatato = new PolizzaClienti(clientePolizza, dett);
					polizza.add(risulatato);
					// JOptionPane.showMessageDialog(null, "Server:Sono nella query");
				}

			}
			rsPolizza.close();
			stPolizza.close();
		} catch (ParseException | SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

		return polizza;
	}

	public ArrayList<PolizzaClienti> getTrovaStorico(String CF, String Targa, String idPolizza) {

		String cf, targa, numpolizza;
		cf = targa = numpolizza = "";
		ArrayList<PolizzaClienti> polizza = new ArrayList<>();
		Polizza dett = null;

		if (!CF.isEmpty() || !Targa.isEmpty()) {

			if (!idPolizza.isEmpty()) {
				numpolizza = "ph2z7xhp182hcqva.storico_polizza.id_polizza=" + idPolizza + " ";
				if (!CF.isEmpty())
					numpolizza = " AND " + numpolizza;
			}
			if (!Targa.isEmpty()) {
				targa = "ph2z7xhp182hcqva.storico_polizza.targa='" + Targa + "' ";
				if (!CF.isEmpty())
					targa = " AND " + targa;
			}
			if (!CF.isEmpty())
				cf = "ph2z7xhp182hcqva.storico_polizza.cf_cliente='" + CF + "' ";
			try {
				System.out.println(cf + " " + targa + " " + numpolizza);
				String query3 = "Select ph2z7xhp182hcqva.storico_polizza.nomecomp, ph2z7xhp182hcqva.storico_polizza.tipov,"
						+ "ph2z7xhp182hcqva.polizza.`Numero Pratica in Registro`, ph2z7xhp182hcqva.storico_polizza.data_inizio_contratto,"
						+ "ph2z7xhp182hcqva.storico_polizza.data_fine_contratto, ph2z7xhp182hcqva.storico_polizza.note, ph2z7xhp182hcqva.storico_polizza.targa,"
						+ "ph2z7xhp182hcqva.storico_polizza.Importo, ph2z7xhp182hcqva.polizza.`NumeroPolizza`,"
						+ "ph2z7xhp182hcqva.polizza.Referente, ph2z7xhp182hcqva.storico_polizza.Rateazione,"
						+ "ph2z7xhp182hcqva.storico_polizza.id_polizza,"
						+ "ph2z7xhp182hcqva.storico_polizza.data_scadenza_semestre, ph2z7xhp182hcqva.storico_polizza.cf_cliente,ph2z7xhp182hcqva.clienti.Nome,ph2z7xhp182hcqva.clienti.Cognome\n"
						+ "from ph2z7xhp182hcqva.storico_polizza\n"
						+ "Inner Join ph2z7xhp182hcqva.polizza  on ph2z7xhp182hcqva.storico_polizza.id_polizza = ph2z7xhp182hcqva.polizza.idpolizza\n"
						+ "Inner Join ph2z7xhp182hcqva.clienti  on ph2z7xhp182hcqva.polizza.CF_cliente = ph2z7xhp182hcqva.clienti.CF\n"
						+ "where " + cf + targa + numpolizza;
				Statement st = DBManager.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(query3);
				if (!rs.isBeforeFirst()) {
					st.close();
					rs.close();
					return null;
				} else {
					while (rs.next()) {
						Cliente clientePolizza = new Cliente();

						clientePolizza.setNome(rs.getString(15));
						clientePolizza.setCognome(rs.getString(16));

						dett = new Polizza();

						dett.setId_cliente(rs.getString(14));
						dett.setNome_compagnia(rs.getString(1));
						dett.setTipo_veicolo(rs.getString(2));
						dett.setNumero_pratica(rs.getString(3));
						dett.setData_inizio(sdf2.format(df.parse(rs.getString(4))));
						dett.setData_fine(sdf2.format(df.parse(rs.getString(5))));
						dett.setNota(rs.getString(6));
						dett.setTarga(rs.getString(7));
						dett.setImporto(rs.getString(8));
						dett.setNumero_polizza(rs.getString(9));
						dett.setReferente(rs.getString(10));
						dett.setRateazione(rs.getString(11));

						dett.setData_rinnovo(sdf2.format(df.parse(rs.getString(13))));

						PolizzaClienti risulatato = new PolizzaClienti(clientePolizza, dett);
						polizza.add(risulatato);

					}

				}
				rs.close();
				st.close();
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}

		}

		return polizza;
	}

	public int updatePolizza(String CF, String NomeCompagnia, String TipoVeicolo, String NumeroPraticaAgenzia,
			String data_inizio, String data_fine, String note, String Targa, String importo, String NumeroPolizza,
			String Referente, String Rateazione, String data_rinnovo, String TargaPrec, String data_sospensione,
			String TipologiaUpdate, byte[] bFile)
			throws AccessException, RemoteException, NotBoundException, SQLException {
		try {

			if (TipologiaUpdate.equals("Rinnovo Semestrale") || TipologiaUpdate.equals("Rinnovo Contratto")
					|| TipologiaUpdate.equals("Modifica Dati") || TipologiaUpdate.equals("Sospensione Polizza")
					|| TipologiaUpdate.equals("Riattivazione Polizza")
					|| TipologiaUpdate.equals("Cambio Contratto o Polizza Scaduta")
					|| TipologiaUpdate.equals("VOLTURA")) {

				if (TipologiaUpdate.equals("Modifica Dati")) {
					if (bFile != null) {
						query = "UPDATE ph2z7xhp182hcqva.polizza set CF_cliente=?,`Nome Compagnia` = ?,`Tipo Veicolo`= ?, `Numero Pratica in registro`=?,"
								+ "data_inizio=?,data_fine=?,note=?,Targa =?,Importo = ?,NumeroPolizza =?,Referente =?,Rateazione =?,data_pagamento =?,"
								+ "documenti= ? where CF_cliente = ? AND Targa =? ";
					} else {
						query = "UPDATE ph2z7xhp182hcqva.polizza set CF_cliente=?,`Nome Compagnia` = ?,`Tipo Veicolo`= ?, `Numero Pratica in registro`=?,"
								+ "data_inizio=?,data_fine=?,note=?,Targa =?,Importo = ?,NumeroPolizza =?,Referente =?,Rateazione =?,data_pagamento =?"
								+ " where CF_cliente = ? AND Targa =? ";
					}
					try {
						PreparedStatement pat;
						pat = DBManager.getInstance().getConnection().prepareStatement(query);
						pat.setString(1, CF.toUpperCase());
						pat.setString(2, NomeCompagnia.replace("\'", "\\'").toUpperCase());
						pat.setString(3, TipoVeicolo.replace("\'", "\\'").toUpperCase());
						pat.setString(4, NumeroPraticaAgenzia.replace("\'", "\\'").toUpperCase());
						pat.setString(5, data_inizio);
						pat.setString(6, data_fine);
						pat.setString(7, note.replace("\'", "\\'"));
						pat.setString(8, Targa.toUpperCase());
						pat.setString(9, importo);
						pat.setString(10, NumeroPolizza.replace("\'", "\\'").toUpperCase());
						pat.setString(11, Referente);
						pat.setString(12, Rateazione);
						pat.setString(13, data_rinnovo);
						if (bFile != null) {
							bais = new ByteArrayInputStream(bFile);
							pat.setBlob(14, bais);
							pat.setString(15, CF.toUpperCase());
							pat.setString(16, TargaPrec);
						} else {
							pat.setString(14, CF.toUpperCase());
							pat.setString(15, TargaPrec);
							// pat.setNull(14, java.sql.Types.BLOB);
						}

						pat.executeUpdate();
					} catch (MySQLIntegrityConstraintViolationException e5) {
						e5.printStackTrace();
						return -2;
					} catch (Exception e6) {
						e6.printStackTrace();
						return -1;
					}
					return 1;
				} else if (TipologiaUpdate.equals("Rinnovo Semestrale")
						|| TipologiaUpdate.equals("Rinnovo Contratto")) {

					System.out.println("Targa: " + Targa + " Targa Prec: " + TargaPrec);
					query = "Update ph2z7xhp182hcqva.polizza set CF_cliente='" + CF.toUpperCase()
							+ "' ,`Nome Compagnia` = '" + NomeCompagnia.replace("\'", "\\'").toUpperCase()
							+ "' ,`Tipo Veicolo`= '" + TipoVeicolo.replace("\'", "\\'").toUpperCase()
							+ "', `Numero Pratica in registro`='"
							+ NumeroPraticaAgenzia.replace("\'", "\\'").toUpperCase() + "', data_inizio='" + data_inizio
							+ "', data_fine=' " + data_fine + "', note='" + note.replace("\'", "\\'") + "', Targa ='"
							+ Targa.toUpperCase() + "', Importo = '" + importo + "', NumeroPolizza ='"
							+ NumeroPolizza.replace("\'", "\\'").toUpperCase() + "', Referente ='"
							+ Referente.replace("\'", "\\'").toUpperCase() + "', Rateazione ='" + Rateazione
							+ "', data_pagamento ='" + data_rinnovo + "',documenti= " + bFile + " "
							+ "where CF_cliente = '" + CF.toUpperCase() + "' AND Targa ='" + TargaPrec + "';";

				} else if (TipologiaUpdate.equals("Sospensione Polizza")) {

					System.out.println("Query Sospensione Polizza");
					query = "Update ph2z7xhp182hcqva.polizza set CF_cliente='" + CF.toUpperCase()
							+ "' ,`Nome Compagnia` = '" + NomeCompagnia.replace("\'", "\\'").toUpperCase()
							+ "' ,Stato='" + "SOSPESA" + "' ,data_sospensione = '" + data_sospensione + // sdf.format(cal.getTime())+
							"' " + "where CF_cliente = '" + CF.toUpperCase() + "' AND Targa ='" + Targa.toUpperCase()
							+ "';";

				} else if (TipologiaUpdate.equals("Riattivazione Polizza")) {

					query = "Update ph2z7xhp182hcqva.polizza set CF_cliente='" + CF.toUpperCase()
							+ "' ,`Nome Compagnia` = '" + NomeCompagnia.replace("\'", "\\'").toUpperCase()
							+ "' ,`Tipo Veicolo`= '" + TipoVeicolo.toUpperCase() + "', `Numero Pratica in registro`='"
							+ NumeroPraticaAgenzia.replace("\'", "\\'").toUpperCase() + "', data_inizio='" + data_inizio
							+ "', data_fine=' " + data_fine + "', note='" + note.replace("\'", "\\'") + "', Targa ='"
							+ Targa + "', Importo = '" + importo + "', NumeroPolizza ='"
							+ NumeroPolizza.replace("\'", "\\'").toUpperCase() + "', Referente ='"
							+ Referente.replace("\'", "\\'").toUpperCase() + "', Rateazione ='" + Rateazione
							+ "', data_pagamento ='" + data_rinnovo + "', Stato ='" + "ATTIVA"
							+ "', data_sospensione=NULL" + " " + "where CF_cliente = '" + CF.toUpperCase()
							+ "' AND Targa ='" + TargaPrec + "';";
				} else if (TipologiaUpdate.equals("Cambio Contratto o Polizza Scaduta")) {

					System.out.println("Cambio Contratto o Polizza Scaduta");
					query = "Update ph2z7xhp182hcqva.polizza set CF_cliente='" + CF.toUpperCase() + "', Stato ='SCADUTA"
							+ "' " + "where CF_cliente = '" + CF.toUpperCase() + "' AND Targa ='" + TargaPrec + "';";

				} else if (TipologiaUpdate.equals("VOLTURA")) {

					System.out.println("Targa: " + Targa + " Targa Prec: " + TargaPrec);
					query = "Update ph2z7xhp182hcqva.polizza set CF_cliente='" + CF.toUpperCase()
							+ "' ,`Nome Compagnia` = '" + NomeCompagnia.replace("\'", "\\'").toUpperCase()
							+ "' ,`Tipo Veicolo`= '" + TipoVeicolo.replace("\'", "\\'").toUpperCase()
							+ "', `Numero Pratica in registro`='"
							+ NumeroPraticaAgenzia.replace("\'", "\\'").toUpperCase() + "', note='"
							+ note.replace("\'", "\\'") + "', Targa='" + Targa.toUpperCase() + "', Importo = '"
							+ importo + "', NumeroPolizza ='" + NumeroPolizza.toUpperCase() + "', Referente ='"
							+ Referente.replace("\'", "\\'").toUpperCase() + "', Rateazione ='" + Rateazione
							+ "', Stato ='ATTIVA" + "' " + "where CF_cliente = '" + CF.toUpperCase() + "' AND Targa ='"
							+ TargaPrec + "';";
				}
				// System.out.println(query);
				PreparedStatement pat;
				pat = DBManager.getInstance().getConnection().prepareStatement(query);
				pat.execute();
				pat.close();
				return 1;
			}
		} catch (Exception e) {

			e.printStackTrace();
			return -1;
		}
		return 0;

	}

	public ArrayList<PolizzaClienti> trovaScandenze(int data_1, int data_2)
			throws RemoteException, SQLException, ParseException {
		String query = "Select ph2z7xhp182hcqva.clienti.cf,ph2z7xhp182hcqva.clienti.nome,ph2z7xhp182hcqva.clienti.cognome as COGNOME,ph2z7xhp182hcqva.clienti.Tel,ph2z7xhp182hcqva.clienti.email,ph2z7xhp182hcqva.polizza.NumeroPolizza,ph2z7xhp182hcqva.polizza.Targa,ph2z7xhp182hcqva.polizza.`Numero Pratica In registro`,ph2z7xhp182hcqva.polizza.Rateazione,ph2z7xhp182hcqva.polizza.note,ph2z7xhp182hcqva.polizza.data_pagamento,"
				+ "ph2z7xhp182hcqva.clienti.note,ph2z7xhp182hcqva.polizza.data_fine, ph2z7xhp182hcqva.polizza.`Nome Compagnia`\n"
				+ "From ph2z7xhp182hcqva.clienti\n"
				+ "Inner Join ph2z7xhp182hcqva.polizza on ph2z7xhp182hcqva.clienti.cf = ph2z7xhp182hcqva.polizza.CF_cliente\n"
				+ "where YEAR(ph2z7xhp182hcqva.polizza.data_pagamento) =" + data_1
				+ " AND MONTH(ph2z7xhp182hcqva.polizza.data_pagamento) =" + data_2
				+ " AND ph2z7xhp182hcqva.polizza.Stato = 'ATTIVA'";

		Statement st = DBManager.getInstance().getConnection().createStatement();
		ResultSet rsPolizza = st.executeQuery(query);
		ArrayList<PolizzaClienti> polizza = new ArrayList<>();

		Polizza dett = null;
		try {

			if (!rsPolizza.isBeforeFirst()) {
				st.close();
				rsPolizza.close();
				return null;
			} else {
				while (rsPolizza.next()) {

					Cliente clientePolizza = new Cliente();

					clientePolizza.setCF(rsPolizza.getString(1));
					clientePolizza.setNome(rsPolizza.getString(2));
					clientePolizza.setCognome(rsPolizza.getString(3));
					clientePolizza.setCell(rsPolizza.getString(4));
					clientePolizza.setEmail(rsPolizza.getString(5));
					clientePolizza.setNote(rsPolizza.getString(12));
					dett = new Polizza();
					// dett.setId_polizza(rsPolizza.getInt(1));
					dett.setId_cliente(rsPolizza.getString(1));
					// dett.setNome_compagnia(rsPolizza.getString(4));
					// dett.setTipo_veicolo(rsPolizza.getString(5));
					dett.setNumero_pratica(rsPolizza.getString(8));
					// dett.setData_inizio(sdf2.format(df.parse(rsPolizza.getString(7))));
					dett.setData_fine(sdf2.format(df.parse(rsPolizza.getString(13))));
					dett.setNota(rsPolizza.getString(10));
					dett.setTarga(rsPolizza.getString(7));
					// dett.setImporto(rsPolizza.getString(11));
					dett.setNumero_polizza(rsPolizza.getString(6));
					// dett.setReferente(rsPolizza.getString(13));
					dett.setRateazione(rsPolizza.getString(9));
					dett.setData_rinnovo(sdf2.format(df.parse(rsPolizza.getString(11))));
					dett.setNome_compagnia(rsPolizza.getString(14));
					// dett.setStato(rsPolizza.getString(17));

					// dett.setData_rinnovo(sdf2.format(df.parse(rsPolizza.getString(11))));

					PolizzaClienti risulatato = new PolizzaClienti(clientePolizza, dett);
					polizza.add(risulatato);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

		st.close();
		rsPolizza.close();
		return polizza;
	}

	public byte[] getDocumenti(int id_polizza) {
		byte[] fileBytes = null;
		String query;
		try {
			query = "select documenti from ph2z7xhp182hcqva.polizza where idpolizza =" + id_polizza + ";";
			Statement st = DBManager.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				fileBytes = rs.getBytes(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileBytes;
	}

	public int deletePolizza(int id_polizza2) {

		try {
			PreparedStatement st = DBManager.getInstance().getConnection()
					.prepareStatement("delete from ph2z7xhp182hcqva.polizza where idpolizza =?");

			st.setLong(1, id_polizza2);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

}
