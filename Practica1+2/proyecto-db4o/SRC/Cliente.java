import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String DNI;

    private String Nombre;

    private String Apellido;

    private int Edad;

    private String Direccion;

    private int Telefono;

    private String Email;

    private List Cuentas;

    public Cliente(String dNI, String nombre, String apellido, int edad, String direccion, int telefono, String email) {
        DNI = dNI;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        Direccion = direccion;
        Telefono = telefono;
        Email = email;
        Cuentas = new ArrayList();
    }
    /**
     * Constructor de cliente a partir de otro objeto cliente.
     * Es utilizado para poder hacer Query sin errores. 
     * 
     * @param c_base
     */
    public Cliente(Cliente c_base) {
        this(c_base.getDNI(), c_base.getNombre(), c_base.getApellido(), c_base.getEdad(), c_base.getDireccion(),
                c_base.getTelefono(), c_base.getEmail());
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public List getCuentas() {
        return Cuentas;
    }

    public void addCuenta(Cuenta cuenta) {
        Cuentas.add(cuenta);
    }

    public String printCuentas(){
        List<Integer> resultado = new ArrayList<Integer>();
        for(Object c : Cuentas){
            Cuenta cuen = (Cuenta)c;
            resultado.add(cuen.getNum_cuenta());
        }
        return resultado.toString();
    }
    @Override
    public String toString() {
        return "Cliente [Apellido=" + Apellido + ", Cuentas= " + printCuentas() + ", DNI=" + DNI + ", Direccion=" + Direccion
                + ", Edad=" + Edad + ", Email=" + Email + ", Nombre=" + Nombre + ", Telefono=" + Telefono + "]";
    }

}
