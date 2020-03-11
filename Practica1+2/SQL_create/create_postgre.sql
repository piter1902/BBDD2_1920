-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "Cuenta_Ahorro"

CREATE TABLE "Cuenta_Ahorro"
(
 "Num_cuenta"     varchar(40) NOT NULL,
 "Fecha_creacion" date NOT NULL,
 "Saldo"          int NOT NULL,
 "Interes"        int NOT NULL,
 "IBAN"           varchar(40) NOT NULL,
 CONSTRAINT "PK_Cuenta_Ahorro" PRIMARY KEY ( "Num_cuenta" )
);


-- ************************************** "Cliente"

CREATE TABLE "Cliente"
(
 "DNI"       varchar(50) NOT NULL,
 "Nombre"    varchar(50) NOT NULL,
 "Apellidos" varchar(50) NOT NULL,
 "Edad"      int NOT NULL,
 "Dirección" varchar(100) NOT NULL,
 "Telefono"  int NOT NULL,
 "Email"     varchar(50) NULL,
 CONSTRAINT "PK_Cliente" PRIMARY KEY ( "DNI" )
);



-- ************************************** "Cuenta_corriente"

CREATE TABLE "Cuenta_corriente"
(
 "Num_cuenta"     varchar(40) NOT NULL,
 "Fecha_creacion" date NOT NULL,
 "Saldo"          int NOT NULL,
 "IBAN"           varchar(40) NOT NULL,
 CONSTRAINT "PK_Corriente" PRIMARY KEY ( "Num_cuenta" )
);


-- ************************************** "Sucursal"

CREATE TABLE "Sucursal"
(
 "Codigo"    varchar(50) NOT NULL,
 "Direccion" varchar(50) NOT NULL,
 "Telefono"  int NOT NULL,
 CONSTRAINT "PK_Sucursal" PRIMARY KEY ( "Codigo" )
);


-- ************************************** "Poseer"

CREATE TABLE "Poseer"
(
 "Num_cuenta" varchar(40) NOT NULL,
 "DNI"        varchar(50) NOT NULL,
 CONSTRAINT "PK_Poseer" PRIMARY KEY ( "Num_cuenta", "DNI" ),
 CONSTRAINT "FK_44" FOREIGN KEY ( "Num_cuenta" ) REFERENCES "Cuenta_corriente" ( "Num_cuenta" ),
 CONSTRAINT "FK_48" FOREIGN KEY ( "Num_cuenta" ) REFERENCES "Cuenta_Ahorro" ( "Num_cuenta" ),
 CONSTRAINT "FK_57" FOREIGN KEY ( "DNI" ) REFERENCES "Cliente" ( "DNI" )
);

CREATE INDEX "fkIdx_44" ON "Poseer"
(
 "Num_cuenta"
);

CREATE INDEX "fkIdx_48" ON "Poseer"
(
 "Num_cuenta"
);

CREATE INDEX "fkIdx_57" ON "Poseer"
(
 "DNI"
);


-- ************************************** "Operacion"

CREATE TABLE "Operacion"
(
 "Num_transaccion" int NOT NULL,
 "Num_cuenta"      varchar(40) NOT NULL,
 "Fecha"           date NOT NULL,
 "Importe"         int NOT NULL,
 "Descripcion"     varchar(280) NULL,
 "Hora"            varchar(10) NOT NULL,
 "Tipo"            varchar(10) NOT NULL,
 "Codigo"          varchar(50) NOT NULL,
 CONSTRAINT "PK_Transaccion_Operacion" PRIMARY KEY ( "Num_transaccion", "Num_cuenta" ),
 CONSTRAINT "FK_70" FOREIGN KEY ( "Num_cuenta" ) REFERENCES "Cuenta_Ahorro" ( "Num_cuenta" ),
 CONSTRAINT "FK_87" FOREIGN KEY ( "Codigo" ) REFERENCES "Sucursal" ( "Codigo" )
);

CREATE INDEX "fkIdx_70" ON "Operacion"
(
 "Num_cuenta"
);

CREATE INDEX "fkIdx_87" ON "Operacion"
(
 "Codigo"
);

-- ************************************** "Transferencia"

CREATE TABLE "Transferencia"
(
 "Num_transaccion"         int NOT NULL,
 "Num_cuenta_realizante"   varchar(40) NOT NULL,
 "Num_cuenta_beneficiario" varchar(40) NOT NULL,
 "Fecha"                   date NOT NULL,
 "Importe"                 int NOT NULL,
 "Descripcion"             varchar(280) NULL,
 "Hora"                    varchar(10) NOT NULL,
 "Tipo"                    varchar(10) NOT NULL,
 "Codigo"                  varchar(50) NOT NULL,
 CONSTRAINT "PK_Transaccion_Transferencia" PRIMARY KEY ( "Num_transaccion", "Num_cuenta_realizante", "Num_cuenta_beneficiario" ),
 CONSTRAINT "FK_73" FOREIGN KEY ( "Num_cuenta_realizante" ) REFERENCES "Cuenta_Ahorro" ( "Num_cuenta" ),
 CONSTRAINT "FK_76" FOREIGN KEY ( "Num_cuenta_beneficiario" ) REFERENCES "Cuenta_Ahorro" ( "Num_cuenta" ),
 CONSTRAINT "FK_84" FOREIGN KEY ( "Codigo" ) REFERENCES "Sucursal" ( "Codigo" )
);

CREATE INDEX "fkIdx_73" ON "Transferencia"
(
 "Num_cuenta_realizante"
);

CREATE INDEX "fkIdx_76" ON "Transferencia"
(
 "Num_cuenta_beneficiario"
);

CREATE INDEX "fkIdx_84" ON "Transferencia"
(
 "Codigo"
);






















