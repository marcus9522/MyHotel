package utente;

import java.sql.Date;

public class UtenteBean {

	private String email;
	private String password;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String ruolo;
	
	public UtenteBean(){
		
	}
	
	public UtenteBean(String em,String pass,String name,String surname,Date dn,String role){
		email = em;
		password = pass;
		nome = name;
		cognome = surname;
		dataNascita = dn;
		ruolo = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	
}
