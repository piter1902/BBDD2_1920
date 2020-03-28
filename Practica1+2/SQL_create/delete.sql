DROP TABLE Cuenta_ahorro;

DROP TABLE Cuenta_corriente;

DROP TABLE Cuenta;

DROP TABLE Cliente;

ALTER TYPE cuenta_t drop ATTRIBUTE Propietario;

ALTER TYPE cliente_t drop ATTRIBUTE Cuentas;

DROP TYPE cuenta_ahorro_t;

DROP TYPE ciente_corriente_t;

DROP TYPE cuenta_t;

DROP TYPE cliente_t;