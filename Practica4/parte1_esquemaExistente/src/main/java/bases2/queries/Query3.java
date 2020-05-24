package bases2.queries;

import java.sql.Timestamp;

// Se crea esta clase para poder mostrar el resultado correctamente en la Query 2 de JPA
public class Query3 {
    

    private String Nombre;

    private int NumCuenta;

    private Timestamp Fecha;
    

    public Query3() {}

    public Query3(String nombre, int numCuenta,Timestamp fecha) {
        Nombre = nombre;
        Fecha = fecha;
        NumCuenta = numCuenta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Timestamp getFecha() {
        return Fecha;
    }

    public void setFecha(Timestamp fecha) {
        Fecha = fecha;
    }

    public int getNumCuenta() {
        return NumCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        NumCuenta = numCuenta;
    }

    @Override
    public String toString() {
        return "[Nombre del Cliente=" + Nombre + ", Fecha=" + Fecha + ", NumCuenta=" + NumCuenta + "]";
    }

    
}