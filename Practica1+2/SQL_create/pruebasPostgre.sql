--Insertamos 4 clientes 

INSERT INTO cliente VALUES ('18023487Q','Jesus', 'Villacampa Sagaste', '20', 'Calle Avenida Madrid 170', '654568731', 'txus710@gmail.com');

INSERT INTO cliente VALUES ('19027831B','Pedro', 'Tamargo Allue', '21', 'Calle Andrés Vicente 28', '654568731', 'pedrotamargo@gmail.com');

INSERT INTO cliente VALUES ('17827839N','Juan Jose Tambo', 'Tambo Tambo', '20', 'Calle Andrés Vicente 28', '654568731', 'pedrotamargo@gmail.com');

INSERT INTO cliente VALUES ('34543213P','Hayk', 'Kocharyan', '20', 'Calle Andrés Vicente 28', '654568731', 'pedrotamargo@gmail.com');

--Insertamos cuentas corrientes/ahorro

-- INSERT INTO cuenta_corriente VALUES ('0286 0891 4732 3233 5769','ES9831901158168186965683', '12003', '2004/09/17');

-- INSERT INTO cuenta_ahorro VALUES ('2816 9469 1834 0932 4973','ES5731901796101998879955', '7090', '2004/02/29','3');

-- INSERT INTO cuenta_corriente VALUES ('8769 7575 6726 4032 7214','ES1601283723028971134953', '5', '2003/05/13');

-- INSERT INTO cuenta_ahorro VALUES ('2802 6172 1780 8239 4048','ES2601822224226111467788', '700', '2019/09/08','5');

-- INSERT INTO cuenta_corriente VALUES ('7643 6354 2468 2857 0238','ES4704876688948353424981', '5091', '2018/02/23');
--Estos inserts darían error ya que la herencia de Postgre tiene limitaciones y dice que esas cuentas corrientes y de ahorro no estan en la tabla 'Cuenta'
--Por el contrario con un SELECT * from cuenta; podríamos visualizar todas cuentas de las tablas hijas

INSERT INTO cuenta VALUES ('0286 0891 4732 3233 5769','ES9831901158168186965683', '12003', '2004/09/17');

INSERT INTO cuenta VALUES ('2816 9469 1834 0932 4973','ES5731901796101998879955', '7090', '2004/02/29');

INSERT INTO cuenta VALUES ('8769 7575 6726 4032 7214','ES1601283723028971134953', '5', '2003/05/13');

INSERT INTO cuenta VALUES ('2802 6172 1780 8239 4048','ES2601822224226111467788', '700', '2019/09/08');

INSERT INTO cuenta VALUES ('7643 6354 2468 2857 0238','ES4704876688948353424981', '5091', '2018/02/23');

--Insertamos en la tabla Poseer, se asocian clientes con cuentas

INSERT INTO poseer VALUES('0286 0891 4732 3233 5769','19027831B');

INSERT INTO poseer VALUES('2816 9469 1834 0932 4973','19027831B');

INSERT INTO poseer VALUES('2816 9469 1834 0932 4973','18023487Q');

INSERT INTO poseer VALUES('8769 7575 6726 4032 7214','17827839N');

INSERT INTO poseer VALUES('2802 6172 1780 8239 4048','34543213P');

INSERT INTO poseer VALUES('7643 6354 2468 2857 0238','18023487Q');

--Insertamos sucursales

INSERT INTO sucursal VALUES('7237','Calle Tenor Fleta 12','675983456');

INSERT INTO sucursal VALUES('2347','Calle Martínez Soria 32','698765686');

--Insertamos operaciones y transferencias

INSERT INTO operacion VALUES('1','0286 0891 4732 3233 5769','2020/03/12', '-12','Rodilleras','10:12','7237');

INSERT INTO operacion VALUES('1','8769 7575 6726 4032 7214','2020/03/05', '-16','ecoTambo','15:42','2347');

INSERT INTO transferencia VALUES('2','0286 0891 4732 3233 5769','2020/03/20', '200','Alquiler','16:42','2347','7643 6354 2468 2857 0238');

-- Consulta la tabla Transaccion, que es padre de transacción y transferencia y saca las instancias de estas dos ultimas
SELECT * FROM Transaccion;

--Inserts de cuentas, de diferente tipo con la misma primary key para comprobar que Postgre no permite heredar restricciones de claves primarias y solo serían claves primarias en el ámbito local de la tabla
INSERT INTO cuenta VALUES ('9999 9999 9999 9999 9999','ES9831901158168186965622', '12003', '2004/09/17');
INSERT INTO cuenta_corriente VALUES ('9999 9999 9999 9999 9999','ES9831901158168186965622', '12003', '2004/09/17','7237');
INSERT INTO cuenta_ahorro VALUES ('9999 9999 9999 9999 9999','ES9831901158168186965622', '12003', '2004/09/17','5');

--Ahora consultamos la tabla Cuenta, que es la tabla padre y aparecen las tres con la misma clave
SELECT * FROM Cuenta;