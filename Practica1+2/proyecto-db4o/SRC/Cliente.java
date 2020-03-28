
public class Cliente {
    public String DNI;

    public String Nombre;

    public String Apellido;

    public int Edad;

    public String Direccion;

    public int Telefono;

    public String Email;

    public Cliente(String dNI, String nombre, String apellido, int edad, String direccion, int telefono, String email) {
        DNI = dNI;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        Direccion = direccion;
        Telefono = telefono;
        Email = email;
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

}
