package carrello;

import java.sql.Date;

public class CarrelloBean {
	private Date datainizio, datafine;
	private String email;
	private int numerocamera;

	public CarrelloBean() {
	}

	public CarrelloBean(Date datainizio, Date datafine, String email, int numerocamera) {
		super();
		this.datainizio = datainizio;
		this.datafine = datafine;
		this.email = email;
		this.numerocamera = numerocamera;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumerocamera() {
		return numerocamera;
	}

	public void setNumerocamera(int numerocamera) {
		this.numerocamera = numerocamera;
	}

}
