# Errores en su implementación

- En su esquema una cuenta solo puede ser de un títular.

- La especialización de _cuentaahorro_ y _cuentacorriente_ debería ser disjunta, pero no.

- Tablas _CodPostal_ y _Codentidades_ no están relacionadas con restricciones de integridad referencial.
- Tabla _Sucursal_ no tiene **dirección** relacionado con nada (no hay integridad referencial). Solo es un **check**. (y encima tampoco es una referencia)
- Tabla _OpTrasferencia_ no tiene **cuentadestino** relacionado con _cuenta_. Solo es un **check**
	- Esto es un problema pero a la vez permite que se realicen transferencias entre entidades. Por ejemplo existe el caso de Banquete y Banco generosidad.
- Tabla _OpEfectivo_ no tiene **sucursal_codoficina** relacionado con _sucursal_. Solo es un **check**

- _CCC_ en **Cuenta** no tiene los 20 dígitos, hay que pensar en los dígitos que faltan (ellos tienen 18).

- _CodPostal_ no tiene sentido que esté relacionada con calle y ciudad porque se duplica información de dirección con código. No cumple la forma normal... ?

---
# Errores nuestros

- Una transferencia no tiene que tener una sucursal, solo las operaciones de ingreso o retirada de efectivo.

--- 
## Sentencias SQL útiles

```
-- Direcciones bien (con su código postal)
SELECT d.id_direccion, d.calle, d.numero, d.piso, d.ciudad, c.codpostal
FROM DIRECCION@SCHEMA2BD2 d JOIN CODPOSTAL@SCHEMA2BD2 c ON d.calle = c.calle AND d.ciudad = c.ciudad;

-- Hay cuentas que pertenecen a los dos hijos
SELECT *
FROM cuentaahorro@SCHEMA2BD2 ca
WHERE ca.ccc IN (SELECT ccc FROM cuentacorriente@SCHEMA2BD2);

-- No hay operaciones que no tengan hijos
SELECT *
FROM operacion@SCHEMA2BD2 o
WHERE o.numop NOT IN (
		SELECT ot.numop
		FROM optransferencia@SCHEMA2BD2 ot
	)
	AND o.numop NOT IN (
		SELECT oe.numop
		FROM opefectivo@SCHEMA2BD2 oe
	);
```
