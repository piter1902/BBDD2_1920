-- Sucursal
CREATE OR REPLACE VIEW sucursal_view AS 
	(
		SELECT s.codigo AS codoficina, s.direccion AS dir, s.telefono AS tfno
		FROM Sucursal s
	)
	UNION
	(
		SELECT s1.codoficina, s1.dir, s1.tfno
		FROM sucursal@SCHEMA2BD2 s1
	);
	
SELECT * FROM sucursal_view;


-- Operacion (padre)
CREATE OR REPLACE VIEW operacion_view AS
	(
		SELECT *
		FROM (
			SELECT t1.num_transaccion AS numop, t1.descripcion AS descripcionop, t1.fecha AS fechaop, TO_CHAR(t1.fecha, 'HH24:MI') AS horaop, t1.importe AS cantidadop, TO_CHAR(t1.NUM_CUENTA_REALIZANTE) AS ccc
			FROM TRANSACCION t1
		)
		UNION
		(
			SELECT op.num_transaccion AS numop, op.descripcion AS descripcionop, op.fecha AS fechaop, TO_CHAR(op.fecha, 'HH24:MI') AS horaop, op.importe AS CANTIDADOP, TO_CHAR(op.NUM_CUENTA_REALIZANTE) AS ccc
			FROM operacion op
		)
	)
	UNION
	(
		SELECT o.numop, o.descripcionop, o.fechaop, o.horaop, o.cantidadop, o.ccc
		FROM operacion@SCHEMA2BD2 o
	);

SELECT * FROM operacion_view;


-- OpTransferencia
CREATE VIEW optransferencia_view AS
	(
		SELECT t1.num_transaccion AS numop, t1.descripcion AS descripcionop, 
				t1.fecha AS fechaop, 
				TO_CHAR(t1.fecha, 'HH24:MI') AS horaop, 
				t1.importe AS cantidadop, 
				TO_CHAR(t1.NUM_CUENTA_REALIZANTE) AS ccc,
				TO_CHAR(t1.NUM_CUENTA_BENEFICIARIO) AS cuentadestino
		FROM TRANSACCION t1
	)
	UNION
	(
		SELECT o.numop, o.descripcionop, o.fechaop, o.horaop, o.cantidadop, o.ccc, ot.cuentadestino
		FROM operacion@SCHEMA2BD2 o JOIN optransferencia@SCHEMA2BD2 ot ON o.numop = ot.numop AND o.ccc = ot.ccc
	);

SELECT * FROM optransferencia_view;


-- OpEfectivo
CREATE VIEW opefectivo_view AS
	(
		SELECT t1.num_transaccion AS numop, t1.descripcion AS descripcionop, 
				t1.fecha AS fechaop, 
				TO_CHAR(t1.fecha, 'HH24:MI') AS horaop, 
				t1.importe AS cantidadop, 
				TO_CHAR(t1.NUM_CUENTA_REALIZANTE) AS ccc,
				t1.tipo AS tipoopefectivo,
				t1.codigo AS sucursal_codoficina
		FROM OPERACION t1
	)
	UNION
	(
		SELECT o.numop, o.descripcionop, o.fechaop, o.horaop, o.cantidadop, o.ccc, oe.tipoopefectivo, oe.sucursal_codoficina
		FROM operacion@SCHEMA2BD2 o JOIN opefectivo@SCHEMA2BD2 oe ON o.numop = oe.numop AND o.ccc = oe.ccc
	);
	
SELECT * FROM opefectivo_view;

DROP VIEW sucursal_view;
DROP VIEW operacion_view;
DROP VIEW optransferencia_view;
DROP VIEW opefectivo_view;