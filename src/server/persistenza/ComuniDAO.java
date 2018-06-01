package server.persistenza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import server.entity.EntityComuni;

public class ComuniDAO {

	public ArrayList<Object> ritornoComuni(String cerca) throws SQLException {

		ArrayList<Object> nomeComuni = new ArrayList<Object>();
		// EntityComuni dett = null;

		try {
			Statement stmt = DBManager.getInstance().getConnection().createStatement();
			String query = "select nome from comuni where nome like '" + cerca + "%'";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				// dett = new EntityComuni();
				// dett.setNome(rs.getString("nome"));
				// nomeComuni.add(dett);
				nomeComuni.add(rs.getString(1));
			}
			stmt.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nomeComuni;
	}

}
