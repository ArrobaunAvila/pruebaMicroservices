CREATE TABLE  PERSON (
id INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
gender VARCHAR(1)  NULL,
age VARCHAR(2) NULL,
identification VARCHAR(20)  UNIQUE NULL,
address VARCHAR(50) NOT NULL,
cellphone  VARCHAR(15) NOT NULL,
PRIMARY KEY(id));

CREATE TABLE CLIENT(
id INTEGER NOT NULL AUTO_INCREMENT,
password VARCHAR NOT NULL,
state VARCHAR NOT NULL,
id_person VARCHAR(20) NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(id_person) REFERENCES PERSON(id)
);

CREATE TABLE account(
 id INTEGER NOT NULL AUTO_INCREMENT,
 account VARCHAR(50) NOT NULL,
 typeAccount VARCHAR(50) NOT NULL,
 balanceInitial VARCHAR(50) NOT NULL,
 state VARCHAR NOT NULL,
 id_client INTEGER,
 PRIMARY KEY(id),
 FOREIGN KEY(id_client) REFERENCES CLIENT(id)
);

--insert into person (name,gender,age,identification,address,cellphone) values('Jose Lema', 'M', '28','10231313', 'Otavalo sn y Principal','098254785');
--insert into person (name,gender,age,identification,address,cellphone)  values('Marianela Montalvo', 'F', '28','19031313', 'Amazonas y NNU','097548965');
--insert into person (name,gender,age,identification,address,cellphone)  values('Juan Osorio', 'M', '28','16898123', '13 junio Equinnocial','098874587');


