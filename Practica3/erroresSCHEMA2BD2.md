# Errores en su implementación
---

- En su esquema una cuenta solo puede ser de un títular.

- La especialización de _cuentaahorro_ y _cuentacorriente_ debería ser disjunta, pero no.

- Tablas _CodPostal_ y _Codentidades_ no están relacionadas con restricciones de integridad referencial.
- Tabla _Sucursal_ no tiene **dirección** relacionado con nada (no hay integridad referencial). Solo es un **check**.
- Tabla _OpTrasferencia_ no tiene **cuentadestino** relacionado con _cuenta_. Solo es un **check**
- Tabla _OpEfectivo_ no tiene **sucursal_codoficina** relacionado con _sucursal_. Solo es un **check**

- _CCC_ en **Cuenta** no tiene los 20 dígitos, hay que pensar en los dígitos que faltan (ellos tienen 18).

- _CodPostal_ no tiene sentido que esté relacionada con calle y ciudad porque se duplica información de dirección con código. No cumple la forma normal... ?

--- 
## Sentencias SQL útiles

```
-- Direcciones bien (con su código postal)
SELECT d.id_direccion, d.calle, d.numero, d.piso, d.ciudad, c.codpostal
FROM DIRECCION@SCHEMA2BD2 d JOIN CODPOSTAL@SCHEMA2BD2 c ON d.calle = c.calle AND d.ciudad = c.ciudad;
```

