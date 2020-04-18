-- ************************************** "Cliente"
INSERT INTO Cliente 
(DNI, Nombre, Apellido, Edad, Direccion, Telefono, Email)
VALUES
('12345678D', 'Jesus','Villacampa',21,'Fckg ejea',666777888,'jesus@gmail.com');

INSERT INTO Cliente 
(DNI, Nombre, Apellido, Edad, Direccion, Telefono, Email)
VALUES
('23456789A', 'Pedro','Tamargo',21,'Huesqueta',656565656,'pedro@gmail.com');

INSERT INTO Cliente 
(DNI, Nombre, Apellido, Edad, Direccion, Telefono, Email)
VALUES
('34567890R', 'Juanjo','Tambo',21,'Sadaba feo',676767678,'tambo@gmail.com');

INSERT INTO Cliente 
(DNI, Nombre, Apellido, Edad, Direccion, Telefono, Email)
VALUES
('43123212H', 'Hayk','Kocharyan',21,'Ni aqui ni ahi',636679654,'hayk@gmail.com');


-- ************************************** "Cuenta"
INSERT INTO Cuenta
(Num_cuenta, IBAN, Fecha_creacion, Saldo, Tipo)
VALUES
(1235567891,'ES6621000418401235567891',TO_DATE('2014-07-18', 'YYYY-MM-DD'), 1230, 'Ahorro');

INSERT INTO Cuenta
(Num_cuenta, IBAN, Fecha_creacion, Saldo, Tipo)
VALUES
(1234567892,'ES6000491500051234567892',TO_DATE('2015-09-18', 'YYYY-MM-DD'), 2130, 'Corriente');

INSERT INTO Cuenta
(Num_cuenta, IBAN, Fecha_creacion, Saldo, Tipo)
VALUES
(1234567991,'ES9420805801101234567991',TO_DATE('2017-02-18', 'YYYY-MM-DD'), 3450, 'Corriente');

INSERT INTO Cuenta
(Num_cuenta, IBAN, Fecha_creacion, Saldo, Tipo)
VALUES
(3456789011,'ES1720852066623456789011',TO_DATE('2018-01-1', 'YYYY-MM-DD'), 4310, 'Ahorro');

-- ************************************** "Cuenta_ahorro"
INSERT INTO Cuenta_Corriente
(ID_Cuenta, ID_Sucursal)
VALUES
(1234567892, 0418);

INSERT INTO Cuenta_Corriente
(ID_Cuenta, ID_Sucursal)
VALUES
(1234567991, 6912);

-- ************************************** "Cuenta_ahorro"
INSERT INTO Cuenta_ahorro
(ID_Cuenta, Interes)
VALUES
(1235567891, 1);

INSERT INTO Cuenta_ahorro
(ID_Cuenta, Interes)
VALUES
(3456789011, 2);

-- ************************************** "Sucursal"
INSERT INTO Sucursal
(Codigo, Direccion, Telefono)
VALUES
( 0418,'mi direccion 6', 901232323);

INSERT INTO Sucursal
(Codigo, Direccion, Telefono)
VALUES
( 5801, 'mi calle 4', 901898789);

INSERT INTO Sucursal
(Codigo, Direccion, Telefono)
VALUES
( 6912, 'mi avenida 4', 901354545);

INSERT INTO Sucursal
(Codigo, Direccion, Telefono)
VALUES
( 2066, 'mi pueblo 12', 901897689);


-- ************************************** "Operacion"


-- ************************************** "Poseer"
INSERT INTO Poseer
(DNI, Num_cuenta)
VALUES
('12345678D', 1235567891);

INSERT INTO Poseer
(DNI, Num_cuenta)
VALUES
( '23456789A', 1234567892 );

INSERT INTO Poseer
(DNI, Num_cuenta)
VALUES
( '34567890R' , 1234567991);

INSERT INTO Poseer
(DNI, Num_cuenta)
VALUES
( '43123212H' , 3456789011);






















