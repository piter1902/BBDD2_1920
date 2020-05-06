-- ************************************** "Personal"

CREATE TABLE "Personal"
(
 "DNI"       varchar(9) NOT NULL,
 "Nombre"    varchar(20) NOT NULL,
 "Apellido1" varchar(20) NOT NULL,
 "Apellido2" varchar(20) NOT NULL,
 "Direccion" varchar(200) NOT NULL,
 "Telefono"  int NOT NULL,
 CONSTRAINT "PK_Personal" PRIMARY KEY ( "DNI" )
);

-- ************************************** "Enfermeros"

CREATE TABLE "Enfermeros"
(
 "DNI"      varchar(9) NOT NULL,
 "Servicio" varchar(50) NOT NULL,
 CONSTRAINT "PK_Enfermeros" PRIMARY KEY ( "DNI" ),
 CONSTRAINT "FK_21" FOREIGN KEY ( "DNI" ) REFERENCES "Personal" ( "DNI" )
);

-- ************************************** "Médicos"

CREATE TABLE "Médicos"
(
 "DNI"           varchar(9) NOT NULL,
 "Especialidad"  varchar(50) NOT NULL,
 "Num_Colegiado" varchar(50) NOT NULL,
 CONSTRAINT "PK_Médicos" PRIMARY KEY ( "DNI" ),
 CONSTRAINT "FK_13" FOREIGN KEY ( "DNI" ) REFERENCES "Personal" ( "DNI" )
);

-- ************************************** "Pacientes"

CREATE TABLE "Pacientes"
(
 "DNI"       varchar(9) NOT NULL,
 "Nombre"    varchar(20) NOT NULL,
 "Apellido1" varchar(20) NOT NULL,
 "Apellido2" varchar(20) NOT NULL,
 "Direccion" varchar(200) NOT NULL,
 "Telefono"  int NOT NULL,
 CONSTRAINT "PK_Pacientes" PRIMARY KEY ( "DNI" )
);

-- ************************************** "Plantas"

CREATE TABLE "Plantas"
(
 "Num_Planta" int NOT NULL,
 "Titulo"     varchar(50) NOT NULL,
 CONSTRAINT "PK_Plantas" PRIMARY KEY ( "Num_Planta" )
);

-- ************************************** "Asignados"

CREATE TABLE "Asignados"
(
 "DNI"        varchar(9) NOT NULL,
 "Num_Planta" int NOT NULL,
 CONSTRAINT "PK_Asignados" PRIMARY KEY ( "DNI", "Num_Planta" ),
 CONSTRAINT "FK_32" FOREIGN KEY ( "DNI" ) REFERENCES "Enfermeros" ( "DNI" ),
 CONSTRAINT "FK_36" FOREIGN KEY ( "Num_Planta" ) REFERENCES "Plantas" ( "Num_Planta" )
);

-- ************************************** "Atendidos"

CREATE TABLE "Atendidos"
(
 "DNI_medico"   varchar(9) NOT NULL,
 "DNI_paciente" varchar(9) NOT NULL,
 CONSTRAINT "PK_Atendidos" PRIMARY KEY ( "DNI_medico", "DNI_paciente" ),
 CONSTRAINT "FK_59" FOREIGN KEY ( "DNI_medico" ) REFERENCES "Médicos" ( "DNI" ),
 CONSTRAINT "FK_65" FOREIGN KEY ( "DNI_paciente" ) REFERENCES "Pacientes" ( "DNI" )
);

-- ************************************** "Diagnostico"

CREATE TABLE "Diagnostico"
(
 "ID"          int NOT NULL,
 "DNI"         varchar(9) NOT NULL,
 "Fecha"       date NOT NULL,
 "Descripcion" text NOT NULL,
 CONSTRAINT "PK_Diagnostico" PRIMARY KEY ( "ID", "DNI" ),
 CONSTRAINT "FK_73" FOREIGN KEY ( "DNI" ) REFERENCES "Pacientes" ( "DNI" )
);

-- ************************************** "Ingresos"

CREATE TABLE "Ingresos"
(
 "ID"            int NOT NULL,
 "Num_Planta"    int NOT NULL,
 "DNI"           varchar(9) NOT NULL,
 "Fecha_Ingreso" date NOT NULL,
 "Fecha_Alta"    date NULL,
 CONSTRAINT "PK_Ingresos" PRIMARY KEY ( "ID", "Num_Planta", "DNI" ),
 CONSTRAINT "FK_44" FOREIGN KEY ( "Num_Planta" ) REFERENCES "Plantas" ( "Num_Planta" ),
 CONSTRAINT "FK_55" FOREIGN KEY ( "DNI" ) REFERENCES "Pacientes" ( "DNI" )
);

