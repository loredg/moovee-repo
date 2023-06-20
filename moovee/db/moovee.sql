create schema moovee;
use moovee;

create table Account 
(
username varchar(20) primary key not null,
email varchar(30) not null,
password char(64) not null,
nome varchar(20) not null,
cognome varchar(30) not null,
indirizzo varchar(50),
admin boolean default false
);

create table Ordine
(
id char(9) primary key not null,
totale integer not null,
data date not null
);

create table Film
(
id char(9) primary key not null,
regista varchar(40) not null,
genere varchar(20) not null,
data_uscita date not null,
durata_min integer not null,
prezzo integer not null,
qta integer not null
titolo varchar(30) not null,
copertina mediumblob default null;
);

create table Bluray 
(
id char(9) not null,
primary key(id),
foreign key(id) references Film(id) on update cascade on delete cascade
);

create table DVD 
(
id char(9) not null,
primary key(id),
foreign key(id) references Film(id) on update cascade on delete cascade
);

create table Composto_Da 
(
idFilm char(9) not null,
idOrdine char(9) not null,
primary key (idFilm, idOrdine),
foreign key (idFilm) references Film(id),
foreign key (idOrdine) references Ordine(id) on update cascade on delete cascade
);