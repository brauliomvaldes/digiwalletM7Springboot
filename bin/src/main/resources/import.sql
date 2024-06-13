create schema IF NOT EXISTS digiwalletm7;
use digiwalletm7;
insert into banks values (1, "Banco de Chile",1);
insert into banks values (2, "Banco Estado",1);
insert into banks values (3, "Banco B.C.I.",1);
insert into banks values (4, "Banco Santander",1);
insert into banks values (5, "Banco Scotiabank",1);

insert into currencyy values (1, "Pesos Chilenos",1, "CLP");
insert into currencyy values (2, "Dolar Americano",1, "USD");

insert into type_of_account values (1, "Cuenta Corriente",1);
insert into type_of_account values (2, "Cuenta RUT",1);
insert into type_of_account values (3, "Chiquera Electónica",1);
