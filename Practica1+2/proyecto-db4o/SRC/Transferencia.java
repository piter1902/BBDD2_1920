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

    @Override
    public String toString() {
        return "Transferencia [Sucursal=" + Sucursal.getCodigo() + ", Descripcion=" + Descripcion + ", Importe="
                + Importe + ", Num_cuenta_realizante=" + Num_cuenta_realizante.getNum_cuenta() + ", Num_transaccion="
                + Num_transaccion + ",Num_cuenta_beneficiario" + Num_cuenta_beneficiario.getNum_cuenta() + "]";
    }
}