package bases2.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACCION")
public class Transferencia extends Transaccion {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "NUM_CUENTA_BENEFICIARIO")
    private Cuenta cuentaDestino;

    public Transferencia() {
    }

    public Transferencia(int numTransaccion, Date fecha, int importe, String descripcion,
            Cuenta realizante, Cuenta cuentaDestino) {
        super(numTransaccion, fecha, importe, descripcion, realizante);
        this.cuentaDestino = cuentaDestino;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cuentaDestino == null) ? 0 : cuentaDestino.hashCode());
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
        Transferencia other = (Transferencia) obj;
        if (cuentaDestino == null) {
            if (other.cuentaDestino != null)
                return false;
        } else if (!cuentaDestino.equals(other.cuentaDestino))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Trasnferencia [cuentaDestino=" + cuentaDestino + "]";
    }

    
}