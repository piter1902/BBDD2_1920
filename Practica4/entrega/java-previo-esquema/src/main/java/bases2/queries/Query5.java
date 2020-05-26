package bases2.queries;

public class Query5 {

    private String Nombre;

    private int numCuenta;

    private Long sumaImporte;

    private int Edad;

    public Query5() {

    }

    public Query5(String nombre, int numCuenta, Long sumaImporte, int edad) {
        this.Nombre = nombre;
        this.numCuenta = numCuenta;
        this.sumaImporte = sumaImporte;
        this.Edad = edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Long getSumaImporte() {
        return sumaImporte;
    }

    public void setSumaImporte(Long sumaImporte) {
        this.sumaImporte = sumaImporte;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        this.Edad = edad;
    }

    @Override
    public String toString() {
        return "[Nombre=" + Nombre + "Edad=" + Edad + "numCuenta=" + numCuenta + ", sumaImporte=" + sumaImporte + "]";
    }
}