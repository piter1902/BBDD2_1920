package bases2.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class Transaccion implements Serializable{

    @Id
    @Column(name = "NumTransaccion")
    private int NumTransaccion;

    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "IMPORTE")
    private int importe;

    @Column(name = "HORA")
    private String hora;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne
    @Id
    private Cuenta realizante;

    public Transaccion() {
    }

    public Transaccion(int numTransaccion, Date fecha, int importe, String hora, String descripcion,
            Cuenta realizante) {
        NumTransaccion = numTransaccion;
        this.fecha = fecha;
        this.importe = importe;
        this.hora = hora;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + NumTransaccion;
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((hora == null) ? 0 : hora.hashCode());
        result = prime * result + importe;
        result = prime * result + ((realizante == null) ? 0 : realizante.hashCode());
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
        if (hora == null) {
            if (other.hora != null)
                return false;
        } else if (!hora.equals(other.hora))
            return false;
        if (importe != other.importe)
            return false;
        if (realizante == null) {
            if (other.realizante != null)
                return false;
        } else if (!realizante.equals(other.realizante))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Transaccion [NumTransaccion=" + NumTransaccion + ", descripcion=" + descripcion + ", fecha=" + fecha
                + ", hora=" + hora + ", importe=" + importe + ", realizante=" + realizante + "]";
    }

}