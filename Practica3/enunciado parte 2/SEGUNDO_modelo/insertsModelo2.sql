
INSERT INTO "Personal" VALUES ('12345678X', 'Pedro', 'Amargo', 'Limon', 
'Avenida perez galdos, numero 6, 5D', 633889977);

INSERT INTO "Personal" VALUES ('78934513Y', 'Hayk', 'Kocharyan', 'Naranja', 
'calle juan perez, numero 1, 5D', 695497090);

INSERT INTO "Personal" VALUES ('12032146H', 'Jesus', 'asi', 'si', 
'pasaje de tu vida', 605495396);

INSERT INTO "Personal" VALUES ('49386023D', 'juanjo', 'ojnauj', 'adverse', 
'plaza lopez', 61237777);

INSERT INTO "Personal" VALUES ('12335678X', 'juanjose', 'tambotatatata', 'tambo',
'avenida cruce de merse', 789345234);

INSERT INTO "Personal" VALUES ('12345778X', 'javier', 'javier', 'javierin',
'plaza javier javierin', 678879234);

INSERT INTO "Personal" VALUES ('12342278X', 'lopez', 'lopezin', 'lopecientos',
'avenida de lopez lopedo', 673312368);

INSERT INTO "Personal" VALUES ('45345678X', 'Ande', 'andante', 'andador', 
'plaza del andador', 712984789);

INSERT INTO "Personal" VALUES ('89895678X', 'pie', 'piececillos', 'piezones',
'calle pie grande 7', 673346781);

INSERT INTO "Personal" VALUES ('11122678X', 'tocador', 'tocado', 'tocadin',
'calle tocado de la vida', 768823465);

SELECT * FROM "Personal";

-- ========================================================================
INSERT INTO "Enfermeros" VALUES ('12345678X', ' Salud Mental');

INSERT INTO "Enfermeros" VALUES ('78934513Y', ' Covid-19');

INSERT INTO "Enfermeros" VALUES ('12032146H', ' Covadonga');

INSERT INTO "Enfermeros" VALUES ('49386023D', 'Neurologia pesada');

INSERT INTO "Enfermeros" VALUES ('12335678X', 'Cositas feas');

SELECT * FROM "Enfermeros";
-- ========================================================================

INSERT INTO "Medicos" VALUES ('12345778X', 'Neurologia', '12345');

INSERT INTO "Medicos" VALUES ('12342278X', 'Cirugia', '23455');

INSERT INTO "Medicos" VALUES ('45345678X', 'Cardiologia', '34523');

INSERT INTO "Medicos" VALUES ('89895678X', 'Pedriatria', '12312');

INSERT INTO "Medicos" VALUES ('11122678X', 'Urgencias', '12312');

SELECT * FROM "Medicos";
-- ========================================================================
INSERT INTO "Pacientes" VALUES ('34534534E', 'Poder', 'Podido' ,'Pudiente' ,
'Calle del poder 5' , 645645645 );

INSERT INTO "Pacientes" VALUES ('89345612A', 'Lanzar', 'Lanzado' ,'Lanzante' ,
'Plaza de Lanzas 45' , 68973452 );

INSERT INTO "Pacientes" VALUES ('67834123X', 'Profe', 'Profesor' ,'Profeta' ,
'Avenida Profeta' , 89121234 );

INSERT INTO "Pacientes" VALUES ('34534544E', 'Poder', 'Podido' ,'Pudiente' ,
'Calle del poder 5' , 678223526 );

INSERT INTO "Pacientes" VALUES ('83275834H', 'Fallo', 'fallado' ,'fallante' ,
'Calle fallada' , 678192742 );

INSERT INTO "Pacientes" VALUES ('82948757D', 'Ejecutator', 'Ejec' ,'Exec' ,
'Calle ejecutada' , 892938475 );

INSERT INTO "Pacientes" VALUES ('27384747E', 'Malo', 'Malito', 'Maliente' ,
'Calle mala 4' , 274846364 );

INSERT INTO "Pacientes" VALUES ('19294938Q', 'Brookker', 'Broker' ,'Brokete' ,
'Palza Boquete 1' , 384747372 );

SELECT * FROM "Pacientes";


-- ========================================================================
INSERT INTO "Plantas" VALUES (1 , 'Pediatria');

INSERT INTO "Plantas" VALUES (2, 'Operaciones');

INSERT INTO "Plantas" VALUES (3, 'Pesadas');

SELECT * FROM "Plantas";

-- ========================================================================
-- enfermeros a plantas
INSERT INTO "Asignados" VALUES ( '12345678X', 3);

INSERT INTO "Asignados" VALUES ( '78934513Y', 3);

INSERT INTO "Asignados" VALUES ( '12032146H', 2);

INSERT INTO "Asignados" VALUES ( '49386023D', 2);

INSERT INTO "Asignados" VALUES ( '12335678X', 1);

SELECT * FROM  "Asignados";
-- ========================================================================
-- pacientes atendidos
INSERT INTO "Atendidos" VALUES ( '12345778X', '34534534E');

INSERT INTO "Atendidos" VALUES ( '12342278X', '89345612A');

INSERT INTO "Atendidos" VALUES ( '45345678X', '67834123X');

INSERT INTO "Atendidos" VALUES ( '89895678X', '34534544E');

INSERT INTO "Atendidos" VALUES ( '11122678X', '83275834H');

INSERT INTO "Atendidos" VALUES ( '89895678X', '82948757D');

INSERT INTO "Atendidos" VALUES ( '45345678X', '27384747E');

INSERT INTO "Atendidos" VALUES ( '11122678X', '19294938Q');

SELECT * FROM "Atendidos";
-- ========================================================================
-- Cada paciente uno o mas diagnosticos
INSERT INTO "Diagnostico" VALUES (1, '34534534E', '2001-02-12', 
'descripcion problema de este cliente');

INSERT INTO "Diagnostico" VALUES (2, '89345612A', '2011-12-1', 
'le duele el ojo');

INSERT INTO "Diagnostico" VALUES (3, '67834123X', '2012-10-23',
'algo tendrá ns');

INSERT INTO "Diagnostico" VALUES (4, '34534544E', '2014-08-22',
'algo que no le va bien');

INSERT INTO "Diagnostico" VALUES (5, '83275834H', '2014-07-26', 
'se ha roto la pierna');

INSERT INTO "Diagnostico" VALUES (6, '82948757D', '2020-06-6',
'le duele el occipital');

INSERT INTO "Diagnostico" VALUES (7, '27384747E', '2020-01-8',
'se le ha roto la unya');

INSERT INTO "Diagnostico" VALUES (8, '34534534E', '2020-04-14',
'no puede respirar');

SELECT * FROM "Diagnostico";

-- ========================================================================
INSERT INTO "Ingresos" VALUES ( 1, 1, '34534534E', '2001-02-12', '2001-12-1');

INSERT INTO "Ingresos" VALUES ( 2, 2, '89345612A', '2011-12-1', '2012-1-12');

INSERT INTO "Ingresos" VALUES ( 3, 2, '67834123X', '2012-10-23', '2012-10-23');

INSERT INTO "Ingresos" VALUES ( 4, 3, '34534544E', '2014-08-22', '2014-08-29');

INSERT INTO "Ingresos" VALUES ( 5, 3, '83275834H', '2014-07-26', '2014-07-28');

INSERT INTO "Ingresos" VALUES ( 6, 1, '82948757D', '2020-05-1', '2020-05-5');

SELECT * FROM "Ingresos";






