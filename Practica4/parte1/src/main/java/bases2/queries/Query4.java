package bases2.queries;

public class Query4 {

    private int numCuenta;

    private int sumaImporte;

    public Query4() {

    }

    public Query4(int numCuenta, int sumaImporte) {
        this.numCuenta = numCuenta;
        this.sumaImporte = sumaImporte;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
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
        return "[numCuenta=" + numCuenta + ", sumaImporte=" + sumaImporte + "]";
    }
}