DROP DATABASE IF EXISTS  myhotel;
CREATE DATABASE myhotel;
USE myhotel;

CREATE TABLE utente (
email varchar(40) NOT NULL,
password varchar(20) NOT NULL,
ruolo enum('amministratore','normale'),
nome varchar(20) NOT NULL,
cognome varchar(20) NOT NULL,
data_nascita date NOT NULL,
PRIMARY KEY (email)
);

CREATE TABLE camera(
numerocamera int not null,
prezzo double not null,
tipologia enum('Singola','Doppia','Suite'),
immagine text(500),
descrizione text(300),
PRIMARY KEY (numerocamera)
);


CREATE TABLE servizio(
nomeservizio varchar(20) not null,
descrizione text not null,
PRIMARY KEY (nomeservizio)
);

CREATE TABLE ha(
numerocamera int not null,
nomeservizio varchar(20) not null,
FOREIGN KEY(numerocamera) REFERENCES camera(numerocamera) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(nomeservizio) REFERENCES servizio(nomeservizio) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE prenotazione (
idprenotazione int not null auto_increment,
email varchar(40) not null,
numerocamera int not null,
totale double not null,
datainizio date NOT NULL,
datafine date NOT NULL,
FOREIGN KEY(numerocamera) REFERENCES camera(numerocamera) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (email) REFERENCES utente(email) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY(idprenotazione)
);

CREATE TABLE comprende(
nomeservizio varchar(20) not null,
idprenotazione int not null,
FOREIGN KEY (idprenotazione) REFERENCES prenotazione(idprenotazione) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE hacarrello(
email varchar(40) not null,
numerocamera int not null,
datainizio date NOT NULL,
datafine date NOT NULL,
totale double NOT NULL,
FOREIGN KEY (email) REFERENCES utente(email) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(numerocamera) REFERENCES camera(numerocamera) ON DELETE CASCADE ON UPDATE CASCADE
);

Insert into utente(email, password, ruolo, nome, cognome, data_nascita)
    Values('alecriscuolo@alice.it', 'lovenapoli', 'normale', 'Alessandro', 'Criscuolo', '1995-06-24');
Insert into utente(email, password, ruolo, nome, cognome, data_nascita)
    Values('gianlucacriscuolo@alice.it', 'glovenapoli', 'normale', 'Gianluca', 'Criscuolo', '1989-05-14');
    Insert into utente(email, password, ruolo, nome, cognome, data_nascita)
    Values('email', 'password', 'normale', 'Gianluca', 'Criscuolo', '1989-05-14');
    Insert into utente(email, password, ruolo, nome, cognome, data_nascita)
    Values('email2', 'password2', 'amministratore', 'Gianluca', 'Criscuolo', '1989-05-14');
Insert into camera(numerocamera, prezzo, tipologia, immagine, descrizione)
    Values('24', '250.25', 'Doppia', 'http://www.hotelgalileopadova.it/resources/images/9d880762-0f9b-42e7-9379-d64e90526f10/it/B/camere-a-padova.jpg', 'Camera doppia,televisore, bagno con doccia e yacuzzi.');
Insert into servizio(nomeservizio, descrizione)
	Values('wifi', 'Con questa opzione si potrà avere libero accesso all utilizzo della rete wifi dell hotel');
Insert into ha(numerocamera, nomeservizio)
	Values('24','wifi');
Insert into prenotazione(idprenotazione, email, numerocamera, totale, datainizio, datafine)
	Values('2056', 'alecriscuolo@alice.it', '24', '250.25', '2017-02-15', '2017-02-20');
Insert into comprende(nomeservizio, idprenotazione)
	Values('wifi','2056');
Insert into hacarrello(email, numerocamera, datainizio, datafine, totale)
	Values('alecriscuolo@alice.it', '24', '2017-02-15', '2017-02-20', '250.25');