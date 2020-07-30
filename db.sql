-- Dumping structure for table banking_demo.test_accounts
CREATE TABLE test_accounts
(id INT NOT NULL,
 name varchar(200) NOT NULL,
 sum INT DEFAULT 0,
 PRIMARY KEY (id));

INSERT INTO TEST_ACCOUNTS (ID, NAME, SUM) VALUES (1, 'Ioana', 100);


create table clienti(
    id number not null,
    nume varchar2(40),
    prenume varchar2(40),
    userName varchar(40),
    parola varchar(200),
    constraint pk_id_client primary key(id_client)
);

create sequence clienti_seq start with 1 increment by 1;


create table stari(
    id number not null,
    stare varchar2(15),
    constraint pk_id_stare primary key(id_stare)
);
insert into stari(id_stare,stare) values(1,'ACTIV');
insert into stari(id_stare,stare) values(2,'IN APROBARE');
insert into stari(id_stare,stare) values(3,'INACTIV');


create table conturi(
    id number not null,
    nr_cont number,
    banca varchar2(50),
    suma number,
    id_stare number,
    id_client number not null,
    constraint pk_id_cont primary key(id_cont),
    constraint fk_clienti foreign key(id_client) references clienti(id_client),
    constraint fk_stari foreign key(id_stare) references stari(id_stare)
);

create sequence conturi_seq start with 1 increment by 1;


create table tranzactii(
    id number not null,
    data_operatie date,
    id_cont_sursa number not null,
    sold_final_sursa number,
    id_cont_destinatie number not null,
    sold_final_destinatie number,
    suma_tranzactie number,
    constraint pk_id_tranzactie primary key(id_tranzactie),
    constraint fk_id_cont_sursa foreign key(id_cont_sursa) references conturi(id_cont),
    constraint fk_id_cont_destinatie foreign key(id_cont_destinatie) references conturi(id_cont)
);
create sequence tranzactii_seq start with 1 increment by 1;

alter table clienti
add id_rol number;


create table roluri(
    id_rol number not null,
    rol varchar2(15),
    constraint pk_id_rol primary key(id_rol)
);
create sequence roluri_seq start with 1 increment by 1;
insert into roluri(id_rol, rol)
values(roluri_seq.nextval,'admin');
insert into roluri(id_rol, rol)
values(roluri_seq.nextval,'client');


alter table clienti
add constraint fk_rol
foreign key (id_rol) references roluri(id_rol);