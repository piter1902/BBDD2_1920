import java.util.Date;

public abstract class Transacciones {
    protected Date Fecha;

    protected String Hora;

    protected int Importe;

    protected String Descripcion;

    protected String Num_transaccion;

    protected Sucursal Sucursal;

    protected Cuenta Num_cuenta_realizante;

    public Transacciones(Date fecha, String hora, int importe, String descripcion, String num_transaccion) {
        Fecha = fecha;
        Hora = hora;
        Importe = importe;
        Descripcion = descripcion;
        Num_transaccion = num_transaccion;
        Sucursal = null;
        Num_cuenta_realizante = null;
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

    public Sucursal getSucursal() {
        return Sucursal;
    }

    public void setSucursal(Sucursal Sucursal) {
        this.Sucursal = Sucursal;
    }

    public Cuenta getNum_cuenta_realizante() {
        return Num_cuenta_realizante;
    }

    public void setNum_cuenta_realizante(Cuenta num_cuenta_realizante) {
        Num_cuenta_realizante = num_cuenta_realizante;
    }
}
