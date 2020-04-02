import java.util.Date;

public class Transferencia extends Transacciones {
    private Cuenta Num_cuenta_beneficiario;

    
    public Transferencia(Date fecha, String hora, int importe, String descripcion, String num_transaccion) {
        super(fecha, hora, importe, descripcion, num_transaccion);
        Num_cuenta_beneficiario = null;
    }

    public Cuenta getNum_cuenta_beneficiario() {
        return Num_cuenta_beneficiario;
    }

    public void setNum_cuenta_beneficiario(Cuenta num_cuenta_beneficiario) {
        Num_cuenta_beneficiario = num_cuenta_beneficiario;
    }

    
}