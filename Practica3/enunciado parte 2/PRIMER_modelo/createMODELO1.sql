
-- ************************************** "Diagnostico"

CREATE TABLE "Diagnostico"
(
 "ID"               int NOT NULL,
 "DNI"              varchar(10) NOT NULL,
 "FechaDiagnostico" date NOT NULL,
 "Descripcion"      varchar(200) NOT NULL,
 CONSTRAINT "PK_Diagnostico" PRIMARY KEY ( "ID", "DNI" ),
 CONSTRAINT "FK_11" FOREIGN KEY ( "DNI" ) REFERENCES "Paciente" ( "DNI" )
);

CREATE INDEX "fkIdx_11" ON "Diagnostico"
(
 "DNI"
);

-- ************************************** "Direccion"

CREATE TABLE "Direccion"
(
 "ID"        int NOT NULL,
 "Calle"     varchar(50) NOT NULL,
 "CodPostal" int NOT NULL,
 "NumPiso"   varchar(10) NOT NULL,
 "NumBloque" varchar(10) NOT NULL,
 CONSTRAINT "PK_Direccion" PRIMARY KEY ( "ID" )
);

-- ************************************** "Enfermero"

CREATE TABLE "Enfermero"
(
 "DNI"         varchar(10) NOT NULL,
 "Nombre"      varchar(20) NOT NULL,
 "Apellidos"   varchar(20) NOT NULL,
 "NumContacto" varchar(20) NOT NULL,
 "Servicio"    varchar(50) NOT NULL,
 "ID"          int NOT NULL,
 CONSTRAINT "PK_Medicos" PRIMARY KEY ( "DNI" ),
 CONSTRAINT "FK_21" FOREIGN KEY ( "ID" ) REFERENCES "Direccion" ( "ID" )
);

CREATE INDEX "fkIdx_21" ON "Enfermero"
(
 "ID"
);

-- ************************************** "Ingresos"

CREATE TABLE "Ingresos"
(
 "NumPlanta" int NOT NULL,
 "DNI"       varchar(10) NOT NULL,
 "Fecha"     date NOT NULL,
 CONSTRAINT "PK_Ingresos" PRIMARY KEY ( "NumPlanta", "DNI" ),
 CONSTRAINT "FK_101" FOREIGN KEY ( "DNI" ) REFERENCES "Paciente" ( "DNI" ),
 CONSTRAINT "FK_97" FOREIGN KEY ( "NumPlanta" ) REFERENCES "Planta" ( "NumPlanta" )
);

CREATE INDEX "fkIdx_101" ON "Ingresos"
(
 "DNI"
);

CREATE INDEX "fkIdx_97" ON "Ingresos"
(
 "NumPlanta"
);

-- ************************************** "Medico"

CREATE TABLE "Medico"
(
 "DNI"          varchar(10) NOT NULL,
 "Nombre"       varchar(20) NOT NULL,
 "Apellidos"    varchar(20) NOT NULL,
 "NumContacto"  varchar(20) NOT NULL,
 "Especialidad" varchar(50) NOT NULL,
 "NumColegiado" varchar(30) NOT NULL,
 "ID"           int NOT NULL,
 CONSTRAINT "PK_Medicos" PRIMARY KEY ( "DNI" ),
 CONSTRAINT "FK_21" FOREIGN KEY ( "ID" ) REFERENCES "Direccion" ( "ID" )
);

CREATE INDEX "fkIdx_21" ON "Medico"
(
 "ID"
);

-- ************************************** "MedicoPaciente"

CREATE TABLE "MedicoPaciente"
(
 "DNIMedico"   varchar(10) NOT NULL,
 "DNIPaciente" varchar(10) NOT NULL,
 CONSTRAINT "PK_MedicoPaciente" PRIMARY KEY ( "DNIMedico", "DNIPaciente" ),
 CONSTRAINT "FK_35" FOREIGN KEY ( "DNIMedico" ) REFERENCES "Medico" ( "DNI" ),
 CONSTRAINT "FK_53" FOREIGN KEY ( "DNIPaciente" ) REFERENCES "Paciente" ( "DNI" )
);

CREATE INDEX "fkIdx_35" ON "MedicoPaciente"
(
 "DNIMedico"
);

CREATE INDEX "fkIdx_53" ON "MedicoPaciente"
(
 "DNIPaciente"
);

-- ************************************** "Paciente"

CREATE TABLE "Paciente"
(
 "DNI"         varchar(10) NOT NULL,
 "Nombre"      varchar(20) NOT NULL,
 "Apellidos"   varchar(20) NOT NULL,
 "NumContacto" varchar(20) NOT NULL,
 "ID"          int NOT NULL,
 CONSTRAINT "PK_Medicos" PRIMARY KEY ( "DNI" ),
 CONSTRAINT "FK_27" FOREIGN KEY ( "ID" ) REFERENCES "Direccion" ( "ID" )
);

CREATE INDEX "fkIdx_27" ON "Paciente"
(
 "ID"
);

-- ************************************** "Pertenece"

CREATE TABLE "Pertenece"
(
 "NumPlanta" int NOT NULL,
 "DNI"       varchar(10) NOT NULL,
 "Fecha"     date NOT NULL,
 CONSTRAINT "PK_Pertenece" PRIMARY KEY ( "NumPlanta", "DNI" ),
 CONSTRAINT "FK_67" FOREIGN KEY ( "NumPlanta" ) REFERENCES "Planta" ( "NumPlanta" ),
 CONSTRAINT "FK_92" FOREIGN KEY ( "DNI" ) REFERENCES "Enfermero" ( "DNI" )
);

CREATE INDEX "fkIdx_67" ON "Pertenece"
(
 "NumPlanta"
);

CREATE INDEX "fkIdx_92" ON "Pertenece"
(
 "DNI"
);

-- ************************************** "Planta"

CREATE TABLE "Planta"
(
 "NumPlanta" int NOT NULL,
 "Nombre"    varchar(30) NOT NULL,
 CONSTRAINT "PK_Planta" PRIMARY KEY ( "NumPlanta" )
);




























































