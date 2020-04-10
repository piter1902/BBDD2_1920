import java.util.Date;
import java.util.List;

public class Cuenta_corriente extends Cuenta {


    public Cuenta_corriente() {
    }

    public Cuenta_corriente(int num_cuenta, String iBAN, Date fecha_creacion, int saldo) {
        super(num_cuenta, iBAN, fecha_creacion, saldo);
    }

    public Cuenta_corriente(Cuenta cuen_base) {
        super(cuen_base);
    }

}
