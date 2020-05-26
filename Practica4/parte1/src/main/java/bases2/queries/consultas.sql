-- Consulta 1
select
  cl.nombre,
  SUM(op.importe),
  op.num_cuenta_realizante
from operacion op
JOIN poseer pos ON op.num_cuenta_realizante = pos.num_cuenta
JOIN cliente cl ON pos.DNI = cl.DNI
where
  op.tipo = 'Retirada'
GROUP BY
  op.num_cuenta_realizante,
  cl.nombre
ORDER BY
  SUM(importe) DESC;

-- Consulta 2
SELECT
  suc.CODIGO,
  cl.NOMBRE,
  cl.EDAD,
  cc.ID_CUENTA
FROM Sucursal suc
JOIN CUENTA_CORRIENTE cc ON suc.CODIGO = cc.ID_SUCURSAL
JOIN POSEER pos ON cc.ID_CUENTA = pos.NUM_CUENTA
JOIN CLIENTE cl ON pos.DNI = cl.DNI
WHERE
  cl.EDAD < 30
ORDER BY
  suc.codigo;

--Consulta3
SELECT
  cl.Nombre,
  p.num_cuenta,
  s1.fecha
FROM (
    SELECT
      max(t.fecha) AS fecha,
      t.num_cuenta_realizante
    from transaccion t
    GROUP BY
      t.num_cuenta_realizante
  ) s1
JOIN poseer p ON p.num_cuenta = s1.num_cuenta_realizante
JOIN cliente cl ON p.DNI = cl.DNI
GROUP BY
  cl.Nombre,
  p.num_cuenta,
  s1.fecha
ORDER BY
  cl.Nombre;

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