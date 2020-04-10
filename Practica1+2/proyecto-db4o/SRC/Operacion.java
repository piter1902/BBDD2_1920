import java.util.Date;

public class Operacion extends Transacciones {

    private String Tipo;

    public Operacion(Date fecha, String hora, int importe, String descripcion, String num_transaccion, String tipo) {
        super(fecha, hora, importe, descripcion, num_transaccion);
        Tipo = tipo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    @Override
    public String toString() {
        return "Operacion [Sucursal=" + Sucursal.getCodigo() + ", Descripcion=" + Descripcion + ", Importe=" + Importe
                + ", Num_cuenta_realizante=" + Num_cuenta_realizante.getNum_cuenta() + ", Num_transaccion="
                + Num_transaccion + ", Tipo=" + Tipo + "]";
    }

}
