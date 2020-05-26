package bases2.queries;


// Se crea esta clase para poder mostrar el resultado correctamente en la Query 2 de JPA
public class Query2 {
    
    private int Codigo;

    private String Nombre;

    private int Edad;
    
    private String NumCuenta;

    public Query2() {}

    public Query2(int codigo, String nombre, int edad, String numCuenta) {
        Codigo = codigo;
        Nombre = nombre;
        Edad = edad;
        NumCuenta = numCuenta;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public String getNumCuenta() {
        return NumCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        NumCuenta = numCuenta;
    }

    @Override
    public String toString() {
        return "[Codigo=" + Codigo + ", Edad=" + Edad + ", Nombre=" + Nombre + ", NumCuenta=" + NumCuenta + "]";
    }

    
}