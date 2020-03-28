import java.util.Date;

public class Operacion extends Transacciones{

    private String Tipo;

    public Operacion(Date fecha, String hora, int importe, String descripcion, String num_transaccion, Sucursal codigo,
            Cuenta_ahorro num_cuenta_realizante, String tipo) {
        super(fecha, hora, importe, descripcion, num_transaccion, codigo, num_cuenta_realizante);
        Tipo = tipo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
    
}
