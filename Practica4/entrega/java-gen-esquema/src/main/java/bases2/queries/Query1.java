package bases2.queries;


// Se crea esta clase para poder mostrar el resultado correctamente en la Query 1 de JPA
public class Query1 {
    
    private String nombre;

    private String numCuenta;

    private Long sumaImporte;

    public Query1() {}

    public Query1(String nombre, String numCuenta, Long sumaImporte) {
        this.nombre = nombre;
        this.numCuenta = numCuenta;
        this.sumaImporte = sumaImporte;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	public Long getSumaImporte() {
		return sumaImporte;
	}

	public void setSumaImporte(Long sumaImporte) {
		this.sumaImporte = sumaImporte;
	}

	@Override
	public String toString() {
		return "[nombre=" + nombre + ", numCuenta=" + numCuenta + ", sumaImporte=" + sumaImporte + "]";
	}


    
}