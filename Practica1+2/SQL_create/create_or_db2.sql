CREATE TYPE Cliente_t AS
(
    DNI       varchar(10),
    Nombre    varchar(50),
    Apellido  varchar(50),
    Edad      int,
    Direccion varchar(100),
    Telefono  int,
    Email     varchar(50)
)MODE DB2SQL;

CREATE TYPE Cuenta_t AS
(
    Num_cuenta     varchar(40),
    IBAN           varchar(40),
    Fecha_creacion date       ,
    Saldo          int        ,
    Propietario    REF(Cliente_t)       
)MODE DB2SQL;

CREATE TYPE Transaccion_t AS
(
    Num_transaccion       int,
    Num_cuenta_realizante REF (Cuenta_t),
    Fecha                 date,
    Hora                  varchar(10),
    Importe               int,
    Descripcion           varchar(280),
    Codigo                varchar(50)
)MODE DB2SQL;

CREATE TYPE Sucursal_t AS
(
    Codigo    varchar(50),
    Direccion varchar(100),
    Telefono  int,
    Transacciones        REF ( Transaccion_t )  
)MODE DB2SQL;

ALTER TYPE Transaccion_t ADD ATTRIBUTE Sucursal REF ( Sucursal_t );

ALTER TYPE Cliente_t ADD ATTRIBUTE Cuentas   REF(Cuenta_t);

CREATE TYPE Cuenta_ahorro_t UNDER Cuenta_t AS
(
    Interes        int   
)MODE DB2SQL;

CREATE TYPE Transferencia_t UNDER Transaccion_t AS
(
    Num_cuenta_beneficiario     REF (Cuenta_t )
)MODE DB2SQL;

CREATE TYPE Operacion_t UNDER Transaccion_t AS
(
    Tipo                VARCHAR(10)
);

CREATE TYPE Cuenta_corriente_t UNDER Cuenta_t MODE DB2SQL;

CREATE TABLE Cliente OF Cliente_t
(
    REF IS Oid USER GENERATED,
    DNI       WITH OPTIONS NOT NULL,
    Nombre    WITH OPTIONS NOT NULL,
    Apellido WITH OPTIONS NOT NULL,
    Edad      WITH OPTIONS NOT NULL,
    Direccion WITH OPTIONS NOT NULL,
    Telefono  WITH OPTIONS NOT NULL,
    CONSTRAINT PK_cliente PRIMARY KEY ( DNI )
);

CREATE TABLE Cuenta OF Cuenta_t
(
    REF IS Oid USER GENERATED,
    Num_cuenta     WITH OPTIONS NOT NULL,
    IBAN           WITH OPTIONS NOT NULL,
    Fecha_creacion WITH OPTIONS NOT NULL,
    Saldo          WITH OPTIONS NOT NULL,
    Propietario    WITH OPTIONS SCOPE Cliente,
    CONSTRAINT PK_cuenta PRIMARY KEY ( Num_cuenta )
);

ALTER TABLE Cliente ALTER COLUMN Cuentas ADD SCOPE Cuenta;

CREATE TABLE Cuenta_ahorro OF Cuenta_ahorro_t UNDER Cuenta
    INHERIT SELECT PRIVILEGES
    (
        Interes WITH OPTIONS NOT NULL
    );

CREATE TABLE Cuenta_corriente OF Cuenta_corriente_t UNDER Cuenta
    INHERIT SELECT PRIVILEGES;

CREATE TABLE Transaccion OF Transaccion_t
(
    Num_transaccion       WITH OPTIONS NOT NULL,
    Num_cuenta_realizante WITH OPTIONS NOT NULL,
    Fecha                 date,
    Hora                  varchar(10),
    Importe               int,
    Descripcion           varchar(280),
    Codigo                varchar(50)
);