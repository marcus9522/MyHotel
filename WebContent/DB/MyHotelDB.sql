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
	Values('admin@email.it', 'admin', 'amministratore', 'Raffaele', 'Criscuolo', '1958-08-04');
Insert into utente(email, password, ruolo, nome, cognome, data_nascita)
    Values('user@email.it', 'user', 'normale', 'Alessandro', 'Criscuolo', '1995-06-24');
Insert into utente(email, password, ruolo, nome, cognome, data_nascita)
    Values('marcus2295@gmail.it', 'marcus2295', 'normale', 'Marco', 'Avagliano', '1995-08-07');
Insert into utente(email, password, ruolo, nome, cognome, data_nascita)
    Values('gianlucacriscuolo@alice.it', 'gianluca', 'normale', 'Gianluca', 'Criscuolo', '1989-05-14');    
Insert into utente(email, password, ruolo, nome, cognome, data_nascita)
    Values('userdb@mail.it', 'userdb', 'normale', 'User', 'DB', '1985-12-14');
Insert into utente(email, password, ruolo, nome, cognome, data_nascita)
    Values('mail@mail.it', 'mail', 'normale', 'Francesco', 'Nota', '1995-02-15');
Insert into camera(numerocamera, prezzo, tipologia, immagine, descrizione)
    Values('24', '250.25', 'Doppia', 'http://www.hotelclodio.it/sites/hotelclodio.gisnet.it/files/Camera-Doppia_HOTEL_CLODIO_ROMA.jpg', 'Camera doppia,televisore, bagno con doccia e yacuzzi.');
Insert into camera(numerocamera, prezzo, tipologia, immagine, descrizione)
	Values('7', '190.50', 'Doppia', 'http://www.hoteldeicongressiroma.com/wp-content/uploads/2013/11/doppia02.jpg', 'Camera doppia,televisore, bagno con doccia e yacuzzi.');
Insert into camera(numerocamera, prezzo, tipologia, immagine, descrizione)
	Values('32', '170.90', 'Singola', 'http://d38nr4x52y6vub.cloudfront.net/_novaimg/galleria/659141.jpg', 'Camera singola con televisore.');
Insert into camera(numerocamera, prezzo, tipologia, immagine, descrizione)
	Values('17', '300.90','Suite', 'http://photos.mandarinoriental.com/is/image/MandarinOriental/las-vegas-suite-emperor-suite-bedroom-2?$DetailBannerHeight$', 'Suite con televisore, bagno con doccia e yacuzzi');
Insert into servizio(nomeservizio, descrizione)
	Values('wifi', 'Con questa opzione si potrà avere libero accesso all utilizzo della rete wifi dell hotel');
Insert into servizio(nomeservizio, descrizione)
	Values('frigobar', 'Con questa opzione si potrà aggiungere alla camera selezionata il frigobar');
Insert into servizio(nomeservizio, descrizione)
	Values('sala massaggi', 'Con questa opzione si potrà avere libero accesso alla sala massaggi');
Insert into servizio(nomeservizio, descrizione)
	Values('Palestra', 'Con questa opzione si potrà avere libero accesso alla zona gym');
Insert into ha(numerocamera, nomeservizio)
	Values('24','wifi');
Insert into prenotazione(idprenotazione, email, numerocamera, totale, datainizio, datafine)
	Values('2056', 'gianlucacriscuolo@alice.it', '24', '750.75', '2017-01-05', '2017-01-07');
Insert into prenotazione(idprenotazione, email, numerocamera, totale, datainizio, datafine)
	Values('2057', 'marcus2295@gmail.it', '7', '952.50', '2017-01-05', '2017-01-09');
Insert into prenotazione(idprenotazione, email, numerocamera, totale, datainizio, datafine)
	Values('2058', 'user@email.it', '32', '170.90', '2017-01-09','2017-01-10');
Insert into hacarrello(email, numerocamera, datainizio, datafine, totale)
	Values('gianlucacriscuolo@alice.it', '24', '2017-01-05', '2017-01-07', '750.75');
Insert into hacarrello(email, numerocamera, datainizio, datafine, totale)
	Values('marcus2295@gmail.it', '7', '2017-01-05', '2017-01-09', '952.50');
Insert into hacarrello(email, numerocamera, datainizio, datafine, totale)
	Values('user@email.it', '32', '2017-01-09', '2017-01-10', '170.90');