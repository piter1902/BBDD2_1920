package bases2.queries;

public class Query4 {

    private String nombre;
    
    private String numCuenta;

    private int sumaImporte;

    public Query4() {

    }

    public Query4(String nombre, String numCuenta, int sumaImporte) {
        this.nombre = nombre;
        this.numCuenta = numCuenta;
        this.sumaImporte = sumaImporte;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public int getSumaImporte() {
        return sumaImporte;
    }

    public void setSumaImporte(int sumaImporte) {
        this.sumaImporte = sumaImporte;
    }

    @Override
    public String toString() {
        return "Query4 [nombre=" + nombre + ", numCuenta=" + numCuenta + ", sumaImporte=" + sumaImporte + "]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}