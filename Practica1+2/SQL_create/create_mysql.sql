-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "Cuenta_ahorro"

CREATE TABLE "Cuenta_ahorro"
(
 "Num_cuenta"     int NOT NULL,
 "IBAN"           varchar(40) NOT NULL,
 "Fecha_creacion" date NOT NULL,
 "Saldo"          int NOT NULL,
 "Interes"        int NOT NULL,
 CONSTRAINT "PK_Cuenta" PRIMARY KEY ( "Num_cuenta" )
);








-- ************************************** "Cliente"

CREATE TABLE "Cliente"
(
 "DNI"       varchar(10) NOT NULL,
 "Nombre"    varchar(50) NOT NULL,
 "Apellido"  varchar(50) NOT NULL,
 "Edad"      int NOT NULL,
 "Direccion" varchar(100) NOT NULL,
 "Telefono"  int NOT NULL,
 "Email"     varchar(50) NULL,
 CONSTRAINT "PK_Cliente" PRIMARY KEY ( "DNI" )
);

-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "Cuenta_corriente"

CREATE TABLE "Cuenta_corriente"
(
 "Num_cuenta"     int NOT NULL,
 "IBAN"           varchar(40) NOT NULL,
 "Fecha_creacion" date NOT NULL,
 "Saldo"          int NOT NULL,
 CONSTRAINT "PK_Cuenta" PRIMARY KEY ( "Num_cuenta" )
);

-- ************************************** "Operacion"

CREATE TABLE "Operacion"
(
 "Num_transaccion"         int NOT NULL,
 "Num_cuenta_realizante"   int NOT NULL,
 "Num_cuenta_beneficiario" int NOT NULL,
 "Fecha"                   date NOT NULL,
 "Hora"                    varchar(10) NOT NULL,
 "Importe"                 int NOT NULL,
 "Descripcion"             varchar(280) NULL,
 "Codigo"                  varchar(50) NOT NULL,
 CONSTRAINT "PK_Transaccion" PRIMARY KEY ( "Num_transaccion", "Num_cuenta_realizante", "Num_cuenta_beneficiario" ),
 CONSTRAINT "FK_76" FOREIGN KEY ( "Num_cuenta_realizante" ) REFERENCES "Cuenta_ahorro" ( "Num_cuenta" ),
 CONSTRAINT "FK_83" FOREIGN KEY ( "Num_cuenta_beneficiario" ) REFERENCES "Cuenta_ahorro" ( "Num_cuenta" ),
 CONSTRAINT "FK_95" FOREIGN KEY ( "Codigo" ) REFERENCES "Sucursal" ( "Codigo" )
);

CREATE INDEX "fkIdx_76" ON "Operacion"
(
 "Num_cuenta_realizante"
);

CREATE INDEX "fkIdx_83" ON "Operacion"
(
 "Num_cuenta_beneficiario"
);

CREATE INDEX "fkIdx_95" ON "Operacion"
(
 "Codigo"
);

-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "Sucursal"

CREATE TABLE "Sucursal"
(
 "Codigo"    varchar(50) NOT NULL,
 "Direccion" varchar(100) NOT NULL,
 "Telefono"  int NOT NULL,
 CONSTRAINT "PK_Sucursal" PRIMARY KEY ( "Codigo" )
);








-- ************************************** "Poseer"

CREATE TABLE "Poseer"
(
 "DNI"        varchar(10) NOT NULL,
 "Num_cuenta" int NOT NULL,
 CONSTRAINT "PK_table_31" PRIMARY KEY ( "DNI", "Num_cuenta" ),
 CONSTRAINT "FK_37" FOREIGN KEY ( "DNI" ) REFERENCES "Cliente" ( "DNI" ),
 CONSTRAINT "FK_41" FOREIGN KEY ( "Num_cuenta" ) REFERENCES "Cuenta_ahorro" ( "Num_cuenta" ),
 CONSTRAINT "FK_47" FOREIGN KEY ( "Num_cuenta" ) REFERENCES "Cuenta_corriente" ( "Num_cuenta" )
);

CREATE INDEX "fkIdx_37" ON "Poseer"
(
 "DNI"
);

CREATE INDEX "fkIdx_41" ON "Poseer"
(
 "Num_cuenta"
);

CREATE INDEX "fkIdx_47" ON "Poseer"
(
 "Num_cuenta"
);

-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "Sucursal"

CREATE TABLE "Sucursal"
(
 "Codigo"    varchar(50) NOT NULL,
 "Direccion" varchar(100) NOT NULL,
 "Telefono"  int NOT NULL,
 CONSTRAINT "PK_Sucursal" PRIMARY KEY ( "Codigo" )
);

-- ************************************** "Poseer"

CREATE TABLE "Poseer"
(
 "DNI"        varchar(10) NOT NULL,
 "Num_cuenta" int NOT NULL,
 CONSTRAINT "PK_table_31" PRIMARY KEY ( "DNI", "Num_cuenta" ),
 CONSTRAINT "FK_37" FOREIGN KEY ( "DNI" ) REFERENCES "Cliente" ( "DNI" ),
 CONSTRAINT "FK_41" FOREIGN KEY ( "Num_cuenta" ) REFERENCES "Cuenta_ahorro" ( "Num_cuenta" ),
 CONSTRAINT "FK_47" FOREIGN KEY ( "Num_cuenta" ) REFERENCES "Cuenta_corriente" ( "Num_cuenta" )
);

CREATE INDEX "fkIdx_37" ON "Poseer"
(
 "DNI"
);

CREATE INDEX "fkIdx_41" ON "Poseer"
(
 "Num_cuenta"
);

CREATE INDEX "fkIdx_47" ON "Poseer"
(
 "Num_cuenta"
);

-- ************************************** "Transaccion"

CREATE TABLE "Transaccion"
(
 "Num_transaccion"       int NOT NULL,
 "Num_cuenta_realizante" int NOT NULL,
 "Fecha"                 date NOT NULL,
 "Hora"                  varchar(10) NOT NULL,
 "Importe"               int NOT NULL,
 "Descripcion"           varchar(280) NULL,
 "Tipo"                  varchar(10) NOT NULL,
 "Codigo"                varchar(50) NOT NULL,
 CONSTRAINT "PK_Transaccion" PRIMARY KEY ( "Num_transaccion", "Num_cuenta_realizante" ),
 CONSTRAINT "FK_76" FOREIGN KEY ( "Num_cuenta_realizante" ) REFERENCES "Cuenta_ahorro" ( "Num_cuenta" ),
 CONSTRAINT "FK_92" FOREIGN KEY ( "Codigo" ) REFERENCES "Sucursal" ( "Codigo" )
);

CREATE INDEX "fkIdx_76" ON "Transaccion"
(
 "Num_cuenta_realizante"
);

CREATE INDEX "fkIdx_92" ON "Transaccion"
(
 "Codigo"
);
