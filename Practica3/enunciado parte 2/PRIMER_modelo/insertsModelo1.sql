
-- Direcciones

INSERT INTO "Direccion" VALUES (1, 'Amargura', 50670, '1 D', '32');

INSERT INTO "Direccion" VALUES (2, 'Xinto',50100, '3 H', 'Escalera derecha');

INSERT INTO "Direccion" VALUES (3, 'Mayor', 50670, '3', '');

INSERT INTO "Direccion" VALUES (4, 'María de Luna', 50078, '22 D', '4');

INSERT INTO "Direccion" VALUES (5, 'Calle Francisco', 50011, '9 H', 'Puerta 4');

INSERT INTO "Direccion" VALUES (6, 'Calle Sádaa', 50678, '1 A', 'Escalera Izquierda');

INSERT INTO "Direccion" VALUES (7, 'Felicidad', 50000, '1 S', 'Bloque A');

INSERT INTO "Direccion" VALUES (8, 'Calle Jose María Aznar', 50444, '13 L', '7');

INSERT INTO "Direccion" VALUES (9, 'Avenida Madrid', 50002, '1', '');

INSERT INTO "Direccion" VALUES (10, 'Calle Pedro Jiménez', 50009, '5 C', 'Bloque central');

INSERT INTO "Direccion" VALUES (11, 'Calle Julio Iglesias', 50112, '3 B', '112');

INSERT INTO "Direccion" VALUES (12, 'Calle El Corazón Del Sapo', 50018, '11 C', '32');

INSERT INTO "Direccion" VALUES (13, 'Calle María de Mi Asunción', 50001, '8', '');

INSERT INTO "Direccion" VALUES (14, 'Bolígrafo Rojo', 50672, '1 C', '55');

INSERT INTO "Direccion" VALUES (15, 'Calle Kerry', 50345, '4 H', '8');

SELECT * FROM "Direccion";
-- ========================================================================
-- Enfermeros
INSERT INTO "Enfermero" VALUES ('12345374C', 'Luis', 'Ponzio, Naranjo', 
764736458,'COVID-19',1);

INSERT INTO "Enfermero" VALUES ('76437474F', 'Julian', 'Perez, Franco', 
666326987, 'Enfermedades Cardiacas', 3);

INSERT INTO "Enfermero" VALUES ('93746738X', 'Maria', 'Del Bosque, Mayayo', 
678730283, 'Enfermedades Mentales', 6);

INSERT INTO "Enfermero" VALUES ('78643764F', 'Julia', 'Coppo, yon', 
766536532, 'Enfermedades respiratorias', 2);

INSERT INTO "Enfermero" VALUES ('89473749P', 'Francisco', 'Del Moro, Espejo', 
976172727, 'Pediatría', 5);

SELECT * FROM "Enfermero";

-- ========================================================================
--Medicos

INSERT INTO "Medico" VALUES ('76376378O', 'José', 'Mesa, Redondo', 
982836272, 'Alergología', '017687654', 4);

INSERT INTO "Medico" VALUES ('74782829I', 'Sofia', 'Muñoz, Pantoja', 
653543543, 'Cardiología', '016735353', 7);

INSERT INTO "Medico" VALUES ('64564356H', 'Laura', 'Martinez, Sofá', 
767367639, 'Cirujía de mama', '028718718', 9);

INSERT INTO "Medico" VALUES ('65365379K', 'Adrian', 'Tambo, Tambo', 
656293365, 'Geriatría', '026536365', 14);


SELECT * FROM "Medico";

-- ========================================================================
-- Pacientes

INSERT INTO "Paciente" VALUES ('63635367A', 'Aurea', 'Lizondo, Aguerri', 
657282335, 8);

INSERT INTO "Paciente" VALUES ('15243784L', 'Jesús', 'Navarro, Tejero', 
783736278, 11);

INSERT INTO "Paciente" VALUES ('72763653S', 'Juan José', 'Pérez, Hermoso', 
652416780, 10);

INSERT INTO "Paciente" VALUES ('65367211D', 'Lourdes', 'Botella, Mesa', 
647382873, 12);

INSERT INTO "Paciente" VALUES ('73667643Z', 'Julián', 'Muñoz, García', 
764636748, 13);

INSERT INTO "Paciente" VALUES ('55256617A', 'Kiko', 'Rivera, Si', 
634252678, 15);

SELECT * FROM "Paciente";

-- ========================================================================
-- Médico del paciente
INSERT INTO "MedicoPaciente" VALUES ('76376378O', '63635367A');

INSERT INTO "MedicoPaciente" VALUES ('74782829I', '65367211D');

INSERT INTO "MedicoPaciente" VALUES ('65365379K', '55256617A');

INSERT INTO "MedicoPaciente" VALUES ('64564356H', '72763653S');

INSERT INTO "MedicoPaciente" VALUES ('74782829I', '73667643Z');

INSERT INTO "MedicoPaciente" VALUES ('65365379K', '15243784L');

SELECT * FROM "MedicoPaciente";

-- ========================================================================
-- Diagnóstico

INSERT INTO "Diagnostico" VALUES (1, '63635367A', TO_DATE('2017-06-09', 'YYYY-MM-DD'), 'El paciente posee verrugas intestinales de tercer grado. Está mu malico :(');

INSERT INTO "Diagnostico" VALUES (2, '15243784L', TO_DATE('2018-01-05', 'YYYY-MM-DD'), 'Perforación en el pulmón izquierdo que provoca pérdida de líquido.');

INSERT INTO "Diagnostico" VALUES (3, '72763653S', TO_DATE('2011-12-10', 'YYYY-MM-DD'), 'Rotura de fémur debido a un accidente de moto.');

INSERT INTO "Diagnostico" VALUES (4, '65367211D', TO_DATE('2020-03-01', 'YYYY-MM-DD'), 'Tiene algo de tos y fiebre tras comer sopa de murciélago.');

INSERT INTO "Diagnostico" VALUES (5, '73667643Z', TO_DATE('2020-02-02', 'YYYY-MM-DD'), 'Primer caso de coronavirus. Espero que sea el único.');

INSERT INTO "Diagnostico" VALUES (6, '55256617A', TO_DATE('2018-03-04', 'YYYY-MM-DD'), 'Le duele la cara de ser tan guapx.');

SELECT * FROM "Diagnostico";

-- ========================================================================
-- Plantas

INSERT INTO "Planta" VALUES (5, 'Enfermedades Víricas');

INSERT INTO "Planta" VALUES (2, 'COVID-19');

INSERT INTO "Planta" VALUES (3, 'Pediatría');

INSERT INTO "Planta" VALUES (4, 'Quemaduras');

INSERT INTO "Planta" VALUES (1, 'Cafetería');

INSERT INTO "Planta" VALUES (0, 'Recepción');

INSERT INTO "Planta" VALUES (6, 'Gente Malita');

-- ========================================================================
-- Enfermero a planta

INSERT INTO "Pertenece" VALUES(2, '12345374C', '2020-07-12');

INSERT INTO "Pertenece" VALUES(3, '76437474F', '2015-06-11');

INSERT INTO "Pertenece" VALUES(4, '93746738X', '2019-02-07');

INSERT INTO "Pertenece" VALUES(6, '78643764F', '2015-10-08');

INSERT INTO "Pertenece" VALUES(6, '89473749P', '2015-11-06');

SELECT * FROM "Pertenece";
-- ========================================================================
-- Paciente a planta

INSERT INTO "Ingresos" VALUES (2,'63635367A', TO_DATE('2020-01-10', 'YYYY-MM-DD'));

INSERT INTO "Ingresos" VALUES (3,'15243784L', TO_DATE('2020-03-11', 'YYYY-MM-DD'));

INSERT INTO "Ingresos" VALUES (5,'72763653S', TO_DATE('2019-06-10', 'YYYY-MM-DD'));

INSERT INTO "Ingresos" VALUES (5,'65367211D', TO_DATE('2018-01-12', 'YYYY-MM-DD'));

INSERT INTO "Ingresos" VALUES (6,'73667643Z', TO_DATE('2019-03-03', 'YYYY-MM-DD'));

INSERT INTO "Ingresos" VALUES (4,'55256617A', TO_DATE('2011-06-04', 'YYYY-MM-DD'));

SELECT * FROM "Ingresos";
