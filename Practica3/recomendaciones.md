# Recomendaciones
---
Un esquema de datos

**Modelo de datos canónico a utilizar** -> Modelo ER extendido porque es un modelo de datos rico semánticamente.
	- Modelo de datos conceptual siempre será más rico que uno lógico.

### Fases:
---
1. Traducción: cada seaquema lcoal se traduce a un modelo canónico.
	- Utilizando el esquema ER extendido.
		- Enriquecimiento semántico: 1 tabla hace 2.
2. Integración: (**NO ES FACIL**) Los esquemas se integran en un modelo de  [...]
	- Resolver los problemas de integridad semántica (sinonimia, homonimia...)
	- Integraremos los dos modelos en una tabla haciendo consultas a la base de datos.

### ¿Qué vamos a utilizar?
---
**Oracle DATABASE LINK**
- En nuestro caso no podremos crear un enlace (porque no nos dan permisos en _danae_ ni en _hendrix_).
	- En las prácticas nos lo proporcionan ellos.
- **¿Cómo se usan?**
	- `SELECT * FROM A@hendrix;` Selecciona todo de la tabla A de la conexión con Hendrix.
	- Usar sinonimos `GRANT CREATE SYNONYM TO <usuario>;`
		- `CREATE SYNONYM A FOR A@hendrix;`
- **OTRA POSIBILIDAD** -> **USAR VISTAS**
	- Utilizar vistas y a través de las mismas crear el esquema global.

### PROBLEMA:
---
**¿CÓMO SON LAS TABLAS?**
	- **SOLUCIÓN**: Consultar el _catálogo_ de la base de datos.

### Pistas:
- Updatable views
