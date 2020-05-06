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


-- OpTransferencia
CREATE OR REPLACE VIEW optransferencia_view AS
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



-- OpEfectivo
CREATE OR REPLACE VIEW opefectivo_view AS
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
	
CREATE OR REPLACE VIEW titular_view AS
	(
		-- BANQUITO
		SELECT c.DNI as DNI, 
				c.nombre as NOMBRE,
				regexp_substr(c.apellido, '^[a-zA-Z]+\w|^[a-zA-Z]+$') APELLIDO1,
				regexp_substr(c.apellido, ' .*$') APELLIDO2,
				c.direccion as DIRECCION,
				TO_CHAR(c.telefono) as TELEFONO,
				c.email as EMAIL, 
				TO_DATE(sysdate-c.edad*365) as FECHA_NACIMIENTO
		FROM Cliente c
	)
	UNION
	(
		-- BANQUETE
		SELECT	t.DNI as DNI,
				t.nombre as NOMBRE, 
				t.apellido1 as APELLIDO1,
				t.apellido2 as APELLIDO2,
				d.calle || ', numero ' || d.numero || ', piso ' || d.piso 
					|| ', ' || d.ciudad || ', '
					|| (
						SELECT distinct cp.codpostal 
						FROM codpostal@SCHEMA2BD2 cp 
						WHERE cp.calle = d.calle AND
							cp.ciudad = d.ciudad
					) 
					as DIRECCION,
				t.telefono as TELEFONO, 
				null as EMAIL,
				t.fecha_nacimiento as FECHA_NACIMIENTO
		FROM titular@SCHEMA2BD2 t 
			JOIN direccion@SCHEMA2BD2 d ON
				t.direccion = d.id_direccion
	);

-- Cuenta (Padre)
CREATE OR REPLACE VIEW cuenta_view AS
    (
        SELECT TO_CHAR(c.Num_cuenta) AS CCC,c.Fecha_creacion as fechacreacion, c.saldo, c.IBAN
        FROM Cuenta c
    )
    UNION
    (
        SELECT c1.CCC, c1.fechacreacion, c1.saldo, (SELECT CONCAT(cod.codigo,cu.CCC) 
            from cuenta@SCHEMA2BD2 cu, codentidades@SCHEMA2BD2 cod
            where  cod.banco='Banquete' AND cu.CCC = c1.CCC) AS IBAN
        FROM cuenta@SCHEMA2BD2 c1
    );


-- Cuenta_corriente
CREATE OR REPLACE VIEW cuenta_corriente_view AS
    (
        SELECT TO_CHAR(cc.ID_cuenta) AS CCC, cc.ID_sucursal AS SUCURSAL_CODOFICINA
        FROM Cuenta_Corriente cc
    )
    UNION
    (
        SELECT cc1.CCC, cc1.SUCURSAL_CODOFICINA
        FROM cuentacorriente@SCHEMA2BD2 cc1
    );

-- Cuenta_ahorrro
CREATE OR REPLACE VIEW cuenta_ahorro_view AS
    (
        SELECT TO_CHAR(cc.ID_cuenta) AS CCC, cc.Interes AS TipoInteres
        FROM Cuenta_ahorro cc
    )
    UNION
    (
        SELECT cc1.CCC, cc1.TIPOINTERES
        FROM cuentaahorro@SCHEMA2BD2 cc1
    );

-- Poseer (N:M)
CREATE OR REPLACE VIEW Posee_view AS
    (
        SELECT p.DNI as TITULAR, TO_CHAR(p.Num_cuenta) AS CCC
        FROM Poseer p
    )
    UNION
    (
        select c1.titular, c1.CCC
        from cuenta@SCHEMA2BD2 c1
    );


SELECT * FROM operacion_view;
SELECT * FROM optransferencia_view;
SELECT * FROM opefectivo_view;
SELECT * FROM titular_view;
SELECT * FROM cuenta_view;

DROP VIEW sucursal_view;
DROP VIEW operacion_view;
DROP VIEW optransferencia_view;
DROP VIEW opefectivo_view;
DROP VIEW titular_view;
DROP VIEW cuenta_view;
DROP VIEW cuenta_corriente_view;
DROP VIEW cuenta_ahorro_view;
DROP VIEW Posee_view;