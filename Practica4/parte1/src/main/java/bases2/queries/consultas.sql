-- Consulta 1
select
  cl.nombre,
  SUM(tr.importe),
  tr.realizante_numcuenta
from transaccion tr
JOIN cliente_cuenta pos ON tr.realizante_numcuenta = pos.cuentas_numcuenta
JOIN cliente cl ON pos.propietarios_DNI = cl.DNI
where
  tr.DTYPE = 'Operacion'
  AND tr.tipo = 'Retirada'
GROUP BY
  tr.realizante_numcuenta,
  cl.nombre
ORDER BY
  SUM(importe) DESC;
-- Consulta 2
SELECT
  suc.CODIGO,
  cl.NOMBRE,
  cl.EDAD,
  cc.NUMCUENTA
FROM Sucursal suc
JOIN CUENTA cc ON suc.CODIGO = cc.sucursal_codigo
JOIN CLIENTE_CUENTA pos ON cc.numCuenta = pos.cuentas_numcuenta
JOIN CLIENTE cl ON pos.PROPIETARIOS_DNI = cl.DNI
WHERE
  cl.EDAD < 30
  AND cc.DTYPE = 'CuentaCorriente'
ORDER BY
  suc.codigo;
-- Consulta 3
SELECT
  cl.NOMBRE,
  p.CUENTAS_NUMCUENTA,
  s1.FECHA
FROM (
    SELECT
      max(t.FECHA) AS fecha,
      t.REALIZANTE_NUMCUENTA
    from transaccion t
    GROUP BY
      t.REALIZANTE_NUMCUENTA
  ) s1
JOIN CLIENTE_CUENTA p ON p.CUENTAS_NUMCUENTA = s1.REALIZANTE_NUMCUENTA
JOIN cliente cl ON p.PROPIETARIOS_DNI = cl.DNI
GROUP BY
  cl.NOMBRE,
  p.CUENTAS_NUMCUENTA,
  s1.FECHA
ORDER BY
  cl.NOMBRE;
-- Consulta 4 para BD original de oracle
SELECT
  cl.Nombre,
  subquery1.Num_cuenta,
  MAX(subquery1.importe) as importe
FROM (
    SELECT
      tr.REALIZANTE_NUMCUENTA AS Num_cuenta,
      MAX(tr.importe) AS Importe
    FROM TRANSACCION tr
    GROUP BY
      tr.REALIZANTE_NUMCUENTA
  ) subquery1
JOIN CLIENTE_CUENTA pos ON pos.CUENTAS_NUMCUENTA = subquery1.Num_cuenta
JOIN CLIENTE cl ON cl.DNI = pos.PROPIETARIOS_DNI
GROUP BY
  subquery1.Num_cuenta,
  cl.NOMBRE
ORDER BY
  cl.NOMBRE;