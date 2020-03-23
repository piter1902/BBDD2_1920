
CREATE TABLE Cuenta
(
 Num_cuenta     varchar(40) NOT NULL,
 IBAN           varchar(40) NOT NULL,
 Saldo          int         NOT NULL,
 Fecha_creacion date        NOT NULL, 
 CONSTRAINT PK_cuenta PRIMARY KEY (Num_cuenta)

);