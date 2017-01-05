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
idprenotazione int not null,
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
