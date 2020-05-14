package bases2.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class Transaccion implements Serializable{

    @Id
    @Column(name = "NUM_TRANSACCION", nullable = false)
    private int NumTransaccion;

    @Column(name = "FECHA", nullable = false)
    private Date fecha;

    @Column(name = "IMPORTE", nullable = false)
    private int importe;

    @Column(name = "DESCRIPCION", nullable = true)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "NUM_CUENTA_REALIZANTE")
    @Id
    private Cuenta realizante;

    @ManyToOne
    @JoinColumn(name = "CODIGO")
    private Sucursal sucursal;

    public Transaccion() {
    }

    public Transaccion(int numTransaccion, Date fecha, int importe, String descripcion,
            Cuenta realizante) {
        NumTransaccion = numTransaccion;
        this.fecha = fecha;
        this.importe = importe;
        this.descripcion = descripcion;
        this.realizante = realizante;
    }

    public int getNumTransaccion() {
        return NumTransaccion;
    }

    public void setNumTransaccion(int numTransaccion) {
        NumTransaccion = numTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cuenta getRealizante() {
        return realizante;
    }

    public void setRealizante(Cuenta realizante) {
        this.realizante = realizante;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + NumTransaccion;
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + importe;
        result = prime * result + ((realizante == null) ? 0 : realizante.hashCode());
        result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaccion other = (Transaccion) obj;
        if (NumTransaccion != other.NumTransaccion)
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (importe != other.importe)
            return false;
        if (realizante == null) {
            if (other.realizante != null)
                return false;
        } else if (!realizante.equals(other.realizante))
            return false;
        if (sucursal == null) {
            if (other.sucursal != null)
                return false;
        } else if (!sucursal.equals(other.sucursal))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Transaccion [NumTransaccion=" + NumTransaccion + ", descripcion=" + descripcion + ", fecha=" + fecha
                + ", importe=" + importe + ", realizante=" + realizante + ", sucursal=" + sucursal + "]";
    }

    

}