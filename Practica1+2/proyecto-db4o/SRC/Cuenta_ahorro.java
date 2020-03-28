import java.util.Date;

public class Cuenta_ahorro extends Cuenta {
    
    public int Interes;

    public Cuenta_ahorro(int Num_cuenta, String IBAN, Date Fecha_creacion, int Saldo, int interes) {
        super(Num_cuenta, IBAN, Fecha_creacion, Saldo);
        Interes = interes;
    }

    public int getInteres() {
        return Interes;
    }

    public void setInteres(int interes) {
        Interes = interes;
    }

}
