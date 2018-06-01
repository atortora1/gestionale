package server.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import server.persistenza.DBManager;

public class GeneratoreCF {

	public String calcolaCF(String nome, String cognome, int giorno, int mese, int anno, String sesso,
			String comuneDiNascita) throws Exception {
		String codiceCognome = this.calcolaCodiceCognome(cognome);
		String codiceNome = this.calcolaCodiceNome(nome);
		String codiceDataNascitaESesso = this.calcolaCodiceDataNascitaESesso(anno, mese, giorno, sesso);
		String codiceComunale = this.calcolaCodiceComune(comuneDiNascita);

		String risultato = codiceCognome + codiceNome + codiceDataNascitaESesso + codiceComunale;

		String carattereDiControllo = this.calcolaCarattereDiControllo(risultato);

		risultato += carattereDiControllo;

		return risultato;
	}

	private String calcolaCodiceCognome(String cognome) {
		DBManager.getInstance().getConnection();
		String codiceCognome;
		int numeroConsonanti;
		cognome = UtilsParole.eliminaSpaziBianchi(cognome).toUpperCase();

		if (cognome.length() >= 3) {
			numeroConsonanti = UtilsParole.getNumeroConsonanti(cognome);

			if (numeroConsonanti >= 3) {
				codiceCognome = UtilsParole.getPrimeConsonanti(cognome, 3);
			} else {
				codiceCognome = UtilsParole.getPrimeConsonanti(cognome, numeroConsonanti);
				codiceCognome += UtilsParole.getPrimeVocali(cognome, 3 - numeroConsonanti);
			}
		} else {
			int numeroCaratteri = cognome.length();
			codiceCognome = cognome + UtilsParole.nXChar(3 - numeroCaratteri);
		}
		return codiceCognome;
	}

	private String calcolaCodiceNome(String nome) {
		DBManager.getInstance().getConnection();
		String codiceNome;
		int numeroConsonanti;
		nome = UtilsParole.eliminaSpaziBianchi(nome).toUpperCase();

		if (nome.length() >= 3) {
			numeroConsonanti = UtilsParole.getNumeroConsonanti(nome);

			if (numeroConsonanti >= 4) {
				codiceNome = UtilsParole.getConsonanteI(nome, 1) + UtilsParole.getConsonanteI(nome, 3)
						+ UtilsParole.getConsonanteI(nome, 4);
			} else if (numeroConsonanti >= 3) {
				codiceNome = UtilsParole.getPrimeConsonanti(nome, 3);
			} else {
				codiceNome = UtilsParole.getPrimeConsonanti(nome, numeroConsonanti);
				codiceNome += UtilsParole.getPrimeVocali(nome, 3 - numeroConsonanti);
			}
		} else {
			int numeroCaratteri = nome.length();
			codiceNome = nome + UtilsParole.nXChar(3 - numeroCaratteri);
		}
		return codiceNome;
	}

	private String calcolaCodiceDataNascitaESesso(int anno, int mese, int giorno, String sesso) {
		DBManager.getInstance().getConnection();
		String codiceDataNascitaESesso;
		String codiceAnno;
		String codiceMese;
		String codiceGiornoESesso;

		codiceAnno = calcolaCodiceAnno(anno);
		codiceMese = calcolaCodiceMese(mese);
		codiceGiornoESesso = calcolaCodiceGiornoESesso(giorno, sesso);

		codiceDataNascitaESesso = codiceAnno + codiceMese + codiceGiornoESesso;

		return codiceDataNascitaESesso;
	}

	private String calcolaCodiceAnno(int anno) {
		DBManager.getInstance().getConnection();
		return Integer.toString(anno).substring(2);
	}

	private String calcolaCodiceMese(int mese) {
		DBManager.getInstance().getConnection();
		String risultato;
		// mese++; //I mesi iniziano da 1
		switch (mese) {
		case 1:
			risultato = "A";
			break;
		case 2:
			risultato = "B";
			break;
		case 3:
			risultato = "C";
			break;
		case 4:
			risultato = "D";
			break;
		case 5:
			risultato = "E";
			break;
		case 6:
			risultato = "H";
			break;
		case 7:
			risultato = "L";
			break;
		case 8:
			risultato = "M";
			break;
		case 9:
			risultato = "P";
			break;
		case 10:
			risultato = "R";
			break;
		case 11:
			risultato = "S";
			break;
		case 12:
			risultato = "T";
			break;
		default:
			risultato = "";
			break;
		}
		return risultato;
	}

	private String calcolaCodiceGiornoESesso(int giorno, String sesso) {
		DBManager.getInstance().getConnection();
		String codiceGiorno = String.format("%02d", giorno);

		if (sesso.equals("F")) {
			int codiceGiornoIntero;
			codiceGiornoIntero = Integer.parseInt(codiceGiorno);
			codiceGiornoIntero += 40;
			codiceGiorno = Integer.toString(codiceGiornoIntero);
		}

		return codiceGiorno;
	}

	private String calcolaCodiceComune(String comune) throws Exception {
		String query = "select codice from comuni where nome='" + comune + "'";
		PreparedStatement pat = DBManager.getInstance().getConnection().prepareStatement(query);
		ResultSet rs = pat.executeQuery();
		rs.next();
		return rs.getString("codice");
	}

	private String calcolaCarattereDiControllo(String codice) throws Exception {
		DBManager.getInstance().getConnection();
		// Passaggio 1 (suddivisione dispari e pari)
		String pari = UtilsParole.getStringaPari(codice);
		String dispari = UtilsParole.getStringaDispari(codice);

		// Passaggio 2 (conversione valori)
		int sommaDispari = conversioneCaratteriDispari(dispari);
		int sommaPari = conversioneCaratteriPari(pari);

		// Passaggio 3 (somma, divisione e conversione finale)
		int somma = sommaDispari + sommaPari;
		int resto = somma % 26;
		char restoConvertito = conversioneResto(resto);

		return Character.toString(restoConvertito);
	}

	private int conversioneCaratteriDispari(String string) {
		DBManager.getInstance().getConnection();
		int risultato = 0;
		for (int i = 0; i < string.length(); i++) {
			char carattere = string.charAt(i);
			switch (carattere) {
			case '0':
			case 'A':
				risultato += 1;
				break;
			case '1':
			case 'B':
				risultato += 0;
				break;
			case '2':
			case 'C':
				risultato += 5;
				break;
			case '3':
			case 'D':
				risultato += 7;
				break;
			case '4':
			case 'E':
				risultato += 9;
				break;
			case '5':
			case 'F':
				risultato += 13;
				break;
			case '6':
			case 'G':
				risultato += 15;
				break;
			case '7':
			case 'H':
				risultato += 17;
				break;
			case '8':
			case 'I':
				risultato += 19;
				break;
			case '9':
			case 'J':
				risultato += 21;
				break;
			case 'K':
				risultato += 2;
				break;
			case 'L':
				risultato += 4;
				break;
			case 'M':
				risultato += 18;
				break;
			case 'N':
				risultato += 20;
				break;
			case 'O':
				risultato += 11;
				break;
			case 'P':
				risultato += 3;
				break;
			case 'Q':
				risultato += 6;
				break;
			case 'R':
				risultato += 8;
				break;
			case 'S':
				risultato += 12;
				break;
			case 'T':
				risultato += 14;
				break;
			case 'U':
				risultato += 16;
				break;
			case 'V':
				risultato += 10;
				break;
			case 'W':
				risultato += 22;
				break;
			case 'X':
				risultato += 25;
				break;
			case 'Y':
				risultato += 24;
				break;
			case 'Z':
				risultato += 23;
				break;
			}
		}
		return risultato;
	}

	private int conversioneCaratteriPari(String string) {
		DBManager.getInstance().getConnection();
		int risultato = 0;
		for (int i = 0; i < string.length(); i++) {
			char carattere = string.charAt(i);
			int numero = Character.getNumericValue(carattere);

			if (Character.isLetter(carattere)) {
				// Se è una lettera
				numero = carattere - 65;
				risultato += numero;
			} else {
				// Se è un numero
				risultato += numero;
			}
		}
		return risultato;
	}

	private char conversioneResto(int resto) {
		DBManager.getInstance().getConnection();
		return (char) (resto + 65);
	}
}