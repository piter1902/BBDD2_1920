-- Cuenta (Padre)
CREATE OR REPLACE VIEW cuenta_view AS
    (
        SELECT c.Num_cuenta AS CCC,c.Fecha_creacion as fechacreacion, c.saldo, c.IBAN
        FROM Cuenta c
    )
    UNION
    (
        SELECT c1.CCC, c1.fechacreacion, c1.saldo, (SELECT CONCAT(cod.codigo,cu.CCC) 
            from cuenta@schema2bd2 cu, codentidades@schema2bd2 cod
            where  cod.banco='Banquete' AND cu.CCC = c1.CCC) AS IBAN
        FROM cuenta@SCHEMA2BD2 c1
    );

SELECT * FROM cuenta_view;

-- Cuenta_corriente
CREATE OR REPLACE VIEW cuenta_corriente AS
    (
        SELECT cc.ID_cuenta AS CCC, cc.ID_sucursal AS SUCURSAL_CODOFICINA
        FROM Cuenta_Corriente cc
    )
    UNION
    (
        SELECT cc1.CCC, cc1.SUCURSAL_CODOFICINA
        FROM cuentacorriente@SCHEMA2DB2 cc1
    );

-- Cuenta_ahorrro
CREATE OR REPLACE VIEW cuenta_ahorro AS
    (
        SELECT cc.ID_cuenta AS CCC, cc.Interes AS TipoInteres
        FROM Cuenta_ahorro cc
    )
    UNION
    (
        SELECT cc1.CCC, cc1.TIPOINTERES
        FROM cuentaahorro@SCHEMA2DB2 cc1
    );

-- Poseer (N:M)
CREATE OR REPLACE VIEW Posee AS
    (
        SELECT p.DNI as TITULAR, p.Num_cuenta AS CCC
        FROM Poseer p
    )
    UNION
    (
        select c1.titular, c1.CCC
        from cuenta@schema2bd2 c1
    );