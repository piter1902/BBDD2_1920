import java.util.Date;

public class Transacciones {
    private Date Fecha;

    private String Hora;

    private int Importe;

    private String Descripcion;

    private String Num_transaccion;

    private Sucursal Codigo;

    private Cuenta Num_cuenta_realizante;

    public Transacciones(Date fecha, String hora, int importe, String descripcion, String num_transaccion,
            Sucursal codigo, Cuenta num_cuenta_realizante) {
        Fecha = fecha;
        Hora = hora;
        Importe = importe;
        Descripcion = descripcion;
        Num_transaccion = num_transaccion;
        Codigo = codigo;
        Num_cuenta_realizante = num_cuenta_realizante;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public int getImporte() {
        return Importe;
    }

    public void setImporte(int importe) {
        Importe = importe;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNum_transaccion() {
        return Num_transaccion;
    }

    public void setNum_transaccion(String num_transaccion) {
        Num_transaccion = num_transaccion;
    }

    public Sucursal getCodigo() {
        return Codigo;
    }

    public void setCodigo(Sucursal codigo) {
        Codigo = codigo;
    }

    public Cuenta getNum_cuenta_realizante() {
        return Num_cuenta_realizante;
    }

    public void setNum_cuenta_realizante(Cuenta num_cuenta_realizante) {
        Num_cuenta_realizante = num_cuenta_realizante;
    }
}
