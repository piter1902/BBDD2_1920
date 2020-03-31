import java.util.Date;
import java.util.List;

public class Cuenta_ahorro extends Cuenta {

    private int Interes;
    

    public int getInteres() {
        return Interes;
    }

    public void setInteres(int interes) {
        Interes = interes;
    }

    public Cuenta_ahorro() {
    }

    public Cuenta_ahorro(int num_cuenta, String iBAN, Date fecha_creacion, int saldo, int interes) {
        super(num_cuenta, iBAN, fecha_creacion, saldo);
        Interes = interes;
    }

    public Cuenta_ahorro(Cuenta cuen_base, int interes) {
        super(cuen_base);
        Interes = interes;
    }

    @Override
    public String toString() {
        return "Cuenta [Clientes=" + printClientes() + " , Fecha_creacion=" + Fecha_creacion + ", IBAN=" + IBAN
                + ", Num_cuenta=" + Num_cuenta + ", Saldo=" + Saldo + ", Interes=" + Interes + "]";
    }
}
