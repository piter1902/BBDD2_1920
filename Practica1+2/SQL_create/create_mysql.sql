-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;


-- ************************************** `Cuenta_Ahorro`

CREATE TABLE `Cuenta_Ahorro`
(
 `Num_cuenta`     varchar(40) NOT NULL ,
 `Fecha_creacion` date NOT NULL ,
 `Saldo`          int NOT NULL ,
 `Interes`        int NOT NULL ,
 `IBAN`           varchar(40) NOT NULL ,

PRIMARY KEY (`Num_cuenta`)
);

-- ************************************** `Cliente`

CREATE TABLE `Cliente`
(
 `DNI`       varchar(50) NOT NULL ,
 `Nombre`    varchar(50) NOT NULL ,
 `Apellidos` varchar(50) NOT NULL ,
 `Edad`      int NOT NULL ,
 `Direccion` varchar(100) NOT NULL ,
 `Telefono`  int NOT NULL ,
 `Email`     varchar(50) NULL ,

PRIMARY KEY (`DNI`)
);

-- ************************************** `Cuenta_corriente`

CREATE TABLE `Cuenta_corriente`
(
 `Num_cuenta`     varchar(40) NOT NULL ,
 `Fecha_creacion` date NOT NULL ,
 `Saldo`          int NOT NULL ,
 `IBAN`           varchar(40) NOT NULL ,

PRIMARY KEY (`Num_cuenta`)
);

-- ************************************** `Operacion`

CREATE TABLE `Operacion`
(
 `Num_transaccion_Operacion` int NOT NULL ,
 `Num_cuenta`                varchar(40) NOT NULL ,
 `Fecha`                     date NOT NULL ,
 `Importe`                   int NOT NULL ,
 `Descripcion`               varchar(280) NULL ,
 `Hora`                      varchar(10) NOT NULL ,
 `Tipo`                      varchar(10) NOT NULL ,
 `Codigo`                    varchar(50) NOT NULL ,

PRIMARY KEY (`Num_transaccion_Operacion`, `Num_cuenta`),
KEY `fkIdx_166` (`Codigo`),
CONSTRAINT `FK_166` FOREIGN KEY `fkIdx_166` (`Codigo`) REFERENCES `Sucursal` (`Codigo`),
KEY `fkIdx_169` (`Num_cuenta`),
CONSTRAINT `FK_169` FOREIGN KEY `fkIdx_169` (`Num_cuenta`) REFERENCES `Cuenta_Ahorro` (`Num_cuenta`)
);

-- ************************************** `Sucursal`

CREATE TABLE `Sucursal`
(
 `Codigo`    varchar(50) NOT NULL ,
 `Direccion` varchar(50) NOT NULL ,
 `Telefono`  int NOT NULL ,

PRIMARY KEY (`Codigo`)
);

-- ************************************** `Poseer`

CREATE TABLE `Poseer`
(
 `Num_cuenta` varchar(40) NOT NULL ,
 `DNI`        varchar(50) NOT NULL ,

PRIMARY KEY (`Num_cuenta`, `DNI`),
KEY `fkIdx_130` (`Num_cuenta`),
CONSTRAINT `FK_130` FOREIGN KEY `fkIdx_130` (`Num_cuenta`) REFERENCES `Cuenta_Ahorro` (`Num_cuenta`),
KEY `fkIdx_134` (`Num_cuenta`),
CONSTRAINT `FK_134` FOREIGN KEY `fkIdx_134` (`Num_cuenta`) REFERENCES `Cuenta_corriente` (`Num_cuenta`),
KEY `fkIdx_136` (`DNI`),
CONSTRAINT `FK_136` FOREIGN KEY `fkIdx_136` (`DNI`) REFERENCES `Cliente` (`DNI`)
);

-- ************************************** `Transferencia`

CREATE TABLE `Transferencia`
(
 `Num_transaccion_Transferencia` int NOT NULL ,
 `Num_cuenta_realizante`         varchar(40) NOT NULL ,
 `Num_cuenta_beneficiario`       varchar(40) NOT NULL ,
 `Fecha`                         date NOT NULL ,
 `Importe`                       int NOT NULL ,
 `Descripcion`                   varchar(280) NULL ,
 `Hora`                          varchar(10) NOT NULL ,
 `Tipo`                          varchar(10) NOT NULL ,
 `Codigo`                        varchar(50) NOT NULL ,

PRIMARY KEY (`Num_transaccion_Transferencia`, `Num_cuenta_realizante`, `Num_cuenta_beneficiario`),
KEY `fkIdx_157` (`Num_cuenta_realizante`),
CONSTRAINT `FK_157` FOREIGN KEY `fkIdx_157` (`Num_cuenta_realizante`) REFERENCES `Cuenta_Ahorro` (`Num_cuenta`),
KEY `fkIdx_160` (`Num_cuenta_beneficiario`),
CONSTRAINT `FK_160` FOREIGN KEY `fkIdx_160` (`Num_cuenta_beneficiario`) REFERENCES `Cuenta_Ahorro` (`Num_cuenta`),
KEY `fkIdx_163` (`Codigo`),
CONSTRAINT `FK_163` FOREIGN KEY `fkIdx_163` (`Codigo`) REFERENCES `Sucursal` (`Codigo`)
);