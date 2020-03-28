INSERT INTO Transaccion
(Num_transaccion, Num_cuenta_realizante, Num_cuenta_beneficiario, Fecha, Importe, Descripcion, Codigo)
VALUES
(1, 1235567891, 3456789011, TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 100, 'Guarrerias',0418);

INSERT INTO Transaccion
(Num_transaccion, Num_cuenta_realizante, Num_cuenta_beneficiario, Fecha, Importe, Descripcion, Codigo)
VALUES
(1, 3456789011, 1234567892, TO_DATE('2005/05/03 12:02:44', 'yyyy/mm/dd hh24:mi:ss'), 1322, 'Cositas', 5801);

INSERT INTO Operacion
(Num_transaccion, Num_cuenta_realizante, Fecha, Importe, Descripcion, Tipo, Codigo)
VALUES
(2,1235567891, TO_DATE('2003/12/03 01:02:44', 'yyyy/mm/dd hh24:mi:ss'), -10, 'Bollo', 'Retirada', 5801);

INSERT INTO Operacion
(Num_transaccion, Num_cuenta_realizante, Fecha, Importe, Descripcion, Tipo, Codigo)
VALUES
(1,3456789011, TO_DATE('2020/12/03 06:02:44', 'yyyy/mm/dd hh24:mi:ss'), +100, 'Paga', 'Ingreso', 2066);