
public class Sucursal {
    private int Codigo;

    private String Direccion;

    private int Telefono;

    public Sucursal(int codigo, String direccion, int telefono) {
        Codigo = codigo;
        Direccion = direccion;
        Telefono = telefono;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        Telefono = telefono;
    }

    @Override
    public String toString() {
        return "Sucursal [Codigo=" + Codigo + ", Direccion=" + Direccion + ", Telefono=" + Telefono + "]";
    }

}
