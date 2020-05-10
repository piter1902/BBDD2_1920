
-- Consultas básicas
SELECT * FROM sucursal_view;

SELECT * FROM operacion_view;

SELECT * FROM optransferencia_view;

SELECT * FROM opefectivo_view;

SELECT * FROM titular_view;

SELECT * FROM cuenta_view;

SELECT * FROM cuenta_corriente_view;

SELECT * FROM cuenta_ahorro_view;

SELECT * FROM Posee_view;

-- Consulta de datos de una cuenta
SELECT t.DNI, t.Nombre, t.Apellido1, t.Apellido2, c.ccc, c.saldo, c.fechacreacion
FROM cuenta_view c JOIN posee_view pv ON c.ccc = pv.ccc JOIN titular_view t ON t.dni = pv.titular;

-- Consulta sobre la información de una transferencia
SELECT ot.ccc AS CuentaInicio, ot.cantidadop, ot.fechaop, ot.horaop, ot.descripcionop, t.Nombre AS Beneficiario, ot.cuentadestino
FROM optransferencia_view ot LEFT OUTER JOIN cuenta_view c ON ot.cuentadestino = c.ccc JOIN posee_view pv ON c.ccc = pv.ccc JOIN titular_view t ON t.dni = pv.titular;


