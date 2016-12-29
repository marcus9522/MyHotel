package prenotazione;

import java.sql.Date;

public class PrenotazioneBean {
	private int idprenotazione;
	private String email;
	private double totale;
	private Date datainizio, datafine;

	public PrenotazioneBean() {

	}

	public PrenotazioneBean(int idprenotazione, String email, double totale, Date datainizio, Date datafine) {
		super();
		this.idprenotazione = idprenotazione;
		this.email = email;
		this.totale = totale;
		this.datainizio = datainizio;
		this.datafine = datafine;
	}

	public int getIdprenotazione() {
		return idprenotazione;
	}

	public void setIdprenotazione(int idprenotazione) {
		this.idprenotazione = idprenotazione;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public Date getDatainizio() {
		return datainizio;
	}

	public void setDatainizio(Date datainizio) {
		this.datainizio = datainizio;
	}

	public Date getDatafine() {
		return datafine;
	}

	public void setDatafine(Date datafine) {
		this.datafine = datafine;
	}

}
