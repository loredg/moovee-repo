create schema moovee;
use moovee;

create table Account 
(
id int primary key not null AUTO_INCREMENT,
username varchar(20) UNIQUE not null,
email varchar(50) UNIQUE not null,
password char(128) not null,
nome varchar(30) not null,
cognome varchar(30) not null,
admin boolean default false
);

create table Ordine
(
id int primary key not null AUTO_INCREMENT,
totale integer not null,
data date not null
idAccount int not null,
idIndirizzo int not null,
foreign key(idIndirizzo) references Indirizzo(id) on update cascade on delete cascade,
foreign key(idAccount) references Account(id) on update cascade on delete cascade
);

create table Film
(
id int primary key not null AUTO_INCREMENT,
regista varchar(40) not null,
genere varchar(20) not null,
anno_uscita int not null,
durata_min integer not null,
prezzo double not null,
qta integer not null
titolo varchar(50) not null,
copertina_landscape longblob default NULL, 
copertina longblob default NULL,
data_aggiunta date not null
);


create table Composto_Da 
(
idFilm int not null AUTO_ICNREMENT,
idOrdine int not null,
qta int not null;
primary key (idFilm, idOrdine),
foreign key (idFilm) references Film(id) on update cascade on delete cascade,
foreign key (idOrdine) references Ordine(id) on update cascade on delete cascade
);

create table CartaDiPagamento (
numero varchar(16) primary key not null,
scadenza date not null,
cvc varchar(3) not null,
idAccount int not null,
foreign key(idAcoount) references Account(id)
);

create table indirizzo (
id int primary key not null AUTO_INCREMENT,
via varchar(40) not null,
cap varchar(12) not null,
citta varchar(30) not null,
provincia varchar(30) not null,
regione varchar(30) not null,
stato varchar(30) not null,
idAccount int not null,
foreign key(idAccount) references Account(id)
);