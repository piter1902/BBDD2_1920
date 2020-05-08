CREATE OR REPLACE VIEW titular_view AS
(
	-- BANQUITO
	-- Seleccionamos los atributos que queremos mostrar con sus respectivos
	-- nombres
	SELECT c.DNI as DNI, 
		c.nombre as NOMBRE,
		regexp_substr(c.apellido, '^[a-zA-Z]+\w|^[a-zA-Z]+$') APELLIDO1,
		regexp_substr(c.apellido, ' .*$') APELLIDO2,
		c.direccion as DIRECCION,
		TO_CHAR(c.telefono) as TELEFONO,
		c.email as EMAIL, 
		-- calculamos la edad ya que no disponemos de la fecha de 
		-- nacimiento, sino la edad en el momento de registro
		TO_DATE(sysdate-c.edad*365) as FECHA_NACIMIENTO
	FROM Cliente c
)
UNION
(
	-- BANQUETE
	-- Seleccionamos los atributos que queremos mostrar con sus respectivos
	-- nombres
	SELECT	t.DNI as DNI,
		t.nombre as NOMBRE, 
		t.apellido1 as APELLIDO1,
		t.apellido2 as APELLIDO2,
		d.calle || ', numero ' || d.numero || ', piso ' || d.piso 
			|| ', ' || d.ciudad || ', '
			|| (
				-- buscamos en codpostal el código correspondiente
				-- a la calle y ciudad que estamos mostrando
				SELECT distinct cp.codpostal 
				FROM codpostal@schema2bd2 cp 
				WHERE cp.calle = d.calle AND
					cp.ciudad = d.ciudad
			) 
			as DIRECCION,
		t.telefono as TELEFONO,
		-- no disponen de atributo email en banquete
		null as EMAIL,
		t.fecha_nacimiento as FECHA_NACIMIENTO
	FROM titular@schema2bd2 t 
		JOIN direccion@schema2bd2 d ON
			t.direccion = d.id_direccion
);

SELECT * FROM titular_view;
DROP VIEW titular_view;