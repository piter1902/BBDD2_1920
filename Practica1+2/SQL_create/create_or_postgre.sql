
CREATE TABLE Cuenta
(
 Num_cuenta     varchar(40) NOT NULL,
 IBAN           varchar(40) NOT NULL,
 Saldo          int         NOT NULL,
 Fecha_creacion date        NOT NULL, 
 CONSTRAINT PK_cuenta PRIMARY KEY (Num_cuenta)

);

CREATE TABLE Cuenta_Ahorro
(
    Num_cuenta     varchar(40) NOT NULL,
    Interes     int NOT NULL,
    CONSTRAINT PK_cuenta_ahorro PRIMARY KEY (Num_cuenta)
) INHERITS (Cuenta);

CREATE TABLE Cuenta_Corriente
(
    Num_cuenta     varchar(40) NOT NULL,
    CONSTRAINT PK_cuenta_corriente PRIMARY KEY (Num_cuenta)
) INHERITS (Cuenta);

CREATE TABLE Cliente
(
 DNI       varchar(50) NOT NULL,
 Nombre    varchar(50) NOT NULL,
 Apellidos varchar(50) NOT NULL,
 Edad      int NOT NULL,
 Dirección varchar(100) NOT NULL,
 Telefono  int NOT NULL,
 Email     varchar(50) NULL,
 CONSTRAINT PK_Cliente PRIMARY KEY ( DNI )
);

CREATE TABLE Poseer 
(
    Num_cuenta int REFERENCES Cuenta ON DELETE CASCADE,
    DNI int REFERENCES Cliente ON DELETE CASCADE,
    PRIMARY KEY (Num_cuenta, DNI)
);

CREATE TABLE Sucursal
(
 Codigo    varchar(50) NOT NULL,
 Direccion varchar(50) NOT NULL,
 Telefono  int NOT NULL,
 CONSTRAINT PK_Sucursal PRIMARY KEY (Codigo)
);

CREATE TABLE Transaccion
(
 Num_transaccion int NOT NULL,
 Num_cuenta      varchar(40) NOT NULL,
 Fecha           date NOT NULL,
 Importe         int NOT NULL,
 Descripcion     varchar(280) NULL,
 Hora            varchar(10) NOT NULL,
 Codigo          varchar(50) NOT NULL,
 CONSTRAINT PK_Transaccion PRIMARY KEY ( Num_transaccion, Num_cuenta ),
 CONSTRAINT FK_Transaccion_NumCuenta FOREIGN KEY ( Num_cuenta ) REFERENCES Cuenta,
 CONSTRAINT FK_Transaccion_Sucursal FOREIGN KEY ( Codigo ) REFERENCES Sucursal
);

CREATE TABLE Transferencia
(
    Num_transaccion             int NOT NULL,
    Num_cuenta_realizante       varchar(40) NOT NULL,
    Num_cuenta_beneficiario     varchar(40) NOT NULL,
    Codigo                      varchar(50) NOT NULL,
    CONSTRAINT PK_Transferencia PRIMARY KEY ( Num_transaccion, Num_cuenta_realizante ),
    CONSTRAINT FK_Transferencia_NumCuentaRea FOREIGN KEY ( Num_cuenta_realizante ) REFERENCES Cuenta,
    CONSTRAINT FK_Transferencia_Sucursal FOREIGN KEY ( Codigo ) REFERENCES Sucursal,
    CONSTRAINT FK_Transferencia_NumCuentaBen FOREIGN KEY ( Num_cuenta_beneficiario ) REFERENCES Cuenta
) INHERITS(Transaccion);

CREATE TABLE Operacion
(
    Num_transaccion             int NOT NULL,
    Num_cuenta_realizante       varchar(40) NOT NULL,
    Codigo                      varchar(50) NOT NULL,
    CONSTRAINT PK_Operacion PRIMARY KEY ( Num_transaccion, Num_cuenta_realizante ),
    CONSTRAINT FK_Operacion_NumCuentaRea FOREIGN KEY ( Num_cuenta_realizante ) REFERENCES Cuenta,
    CONSTRAINT FK_Operacion_Sucursal FOREIGN KEY ( Codigo ) REFERENCES Sucursal
) INHERITS(Transaccion);

