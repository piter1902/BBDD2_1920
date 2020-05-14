package bases2.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPERACION")
public class Operacion extends Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "TIPO")
    private String tipo;

    public Operacion() {
    }

    public Operacion(int numTransaccion, Date fecha, int importe, String descripcion, Cuenta realizante,
            String tipo) {
        super(numTransaccion, fecha, importe, descripcion, realizante);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Operacion other = (Operacion) obj;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Operacion [tipo=" + tipo + "]";
    }

}