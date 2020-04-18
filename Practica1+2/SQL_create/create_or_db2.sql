-- Enlaces de interés:
-- https://www.ibm.com/support/knowledgecenter/SSEPGG_11.5.0/com.ibm.db2.luw.admin.structypes.doc/doc/t0006634.html

-- Creation of types
-- Los Oids se generan a mano. Para poder generarlos automáticamente, usar GENERATE_UNIQUE()

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
    Saldo          int        
)MODE DB2SQL;

CREATE TYPE Cuenta_ahorro_t UNDER Cuenta_t AS
(
    Interes        int   
)MODE DB2SQL;

-- CREATE TYPE Cuenta_corriente_t UNDER Cuenta_t AS
-- (
--     Para_que_funcione          int
--     -- Este atributo se lo he añadido para que deje crear la tabla. 
-- )MODE DB2SQL;
CREATE TYPE Sucursal_t AS
(
    Codigo    varchar(50),
    Direccion varchar(100),
    Telefono  int
)MODE DB2SQL;

CREATE TYPE Cuenta_corriente_t UNDER Cuenta_t AS
(
    Sucursal    REF(Sucursal_t)
)MODE DB2SQL;

-- Bridge type between Cuenta & Cliente
CREATE TYPE Poseer_t AS
(
    DNI            REF(Cliente_t),
    Num_cuenta     REF(Cuenta_t)
)MODE DB2SQL;


CREATE TYPE Transaccion_t AS
(
    Num_transaccion       int,
    Num_cuenta_realizante REF (Cuenta_t),
    Fecha                 date,
    Hora                  varchar(10),
    Importe               int,
    Descripcion           varchar(280),
    Codigo                varchar(50),
    Sucursal              REF (Sucursal_t)
)MODE DB2SQL;

CREATE TYPE Transferencia_t UNDER Transaccion_t AS
(
    Num_cuenta_beneficiario     REF (Cuenta_t )
)MODE DB2SQL;

CREATE TYPE Operacion_t UNDER Transaccion_t AS
(
    Tipo                VARCHAR(10)
)MODE DB2SQL;

-- Creation of tables

CREATE TABLE Cliente OF Cliente_t
(
    REF         IS Oid USER GENERATED,
    DNI         WITH OPTIONS NOT NULL,
    Nombre      WITH OPTIONS NOT NULL,
    Apellido    WITH OPTIONS NOT NULL,
    Edad        WITH OPTIONS NOT NULL,
    Direccion   WITH OPTIONS NOT NULL,
    Telefono    WITH OPTIONS NOT NULL,
    CONSTRAINT PK_cliente PRIMARY KEY ( DNI )
);

CREATE TABLE Cuenta OF Cuenta_t
(
    REF IS Oid USER GENERATED,
    Num_cuenta     WITH OPTIONS NOT NULL,
    IBAN           WITH OPTIONS NOT NULL,
    Fecha_creacion WITH OPTIONS NOT NULL,
    Saldo          WITH OPTIONS NOT NULL,
    CONSTRAINT PK_cuenta PRIMARY KEY ( Num_cuenta )
);

CREATE TABLE Cuenta_ahorro OF Cuenta_ahorro_t UNDER Cuenta
    INHERIT SELECT PRIVILEGES
    (
        Interes WITH OPTIONS NOT NULL
    );

CREATE TABLE Sucursal OF Sucursal_t
(
    REF IS Oid USER GENERATED,
    Codigo    WITH OPTIONS NOT NULL,
    Direccion WITH OPTIONS NOT NULL,
    -- Telefono  int
    CONSTRAINT PK_sucursal  PRIMARY KEY (Codigo)
);

CREATE TABLE Cuenta_corriente OF Cuenta_corriente_t UNDER Cuenta
    INHERIT SELECT PRIVILEGES
    (
        Sucursal WITH OPTIONS SCOPE Sucursal
    );
    
CREATE TABLE Poseer OF Poseer_t
(
    REF IS Oid USER GENERATED,
    DNI            WITH OPTIONS SCOPE Cliente,
    DNI            WITH OPTIONS NOT NULL,    
    Num_cuenta     WITH OPTIONS SCOPE Cuenta,
    Num_cuenta     WITH OPTIONS NOT NULL,

    -- Constraint necesarios para integridad referencial
    CONSTRAINT  PK_Poseer PRIMARY KEY ( DNI, Num_cuenta )
    -- CONSTRAINT  FK_cliente FOREIGN KEY (DNI) REFERENCES Cliente,
    -- CONSTRAINT  FK_cuenta FOREIGN KEY (Num_cuenta) REFERENCES Cuenta
);

CREATE TABLE Transaccion OF Transaccion_t
(
    REF IS Oid USER GENERATED,
    Num_transaccion       WITH OPTIONS NOT NULL,
    Num_cuenta_realizante WITH OPTIONS SCOPE Cuenta,
    -- Num_cuenta_realizante WITH OPTIONS NOT NULL,
    Fecha                 WITH OPTIONS NOT NULL,
    Hora                  WITH OPTIONS NOT NULL,
    Importe               WITH OPTIONS NOT NULL,
    Descripcion           WITH OPTIONS NOT NULL,
    Codigo                WITH OPTIONS NOT NULL,
    Sucursal              WITH OPTIONS SCOPE Sucursal,
    -- Sucursal              WITH OPTIONS NOT NULL,
    CONSTRAINT  PK_transaccion PRIMARY KEY (Num_transaccion)
    -- CONSTRAINT  FK_cuenta_realizante FOREIGN KEY (Num_cuenta_realizante) REFERENCES Cuenta,
    -- CONSTRAINT FK_sucursal  FOREIGN KEY (Sucursal) REFERENCES Sucursal
);

CREATE TABLE Transferencia OF Transferencia_t UNDER Transaccion
    INHERIT SELECT PRIVILEGES
    (
        Num_cuenta_beneficiario WITH OPTIONS SCOPE Cuenta
        -- CONSTRAINT  FK_cuenta_beneficiario FOREIGN KEY (Num_cuenta_beneficiario) REFERENCES Cuenta
    );

CREATE TABLE Operacion OF Operacion_t UNDER Transaccion
    INHERIT SELECT PRIVILEGES
    (
        Tipo WITH OPTIONS NOT NULL
    );
