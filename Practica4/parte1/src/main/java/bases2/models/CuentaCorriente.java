package bases2.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CuentaCorriente extends Cuenta implements Serializable {

    @ManyToOne
    private Sucursal sucursal;

    public CuentaCorriente() {
    }

    public CuentaCorriente(String numCuenta, String iBAN, Date fechaCreacion, int saldo, Sucursal sucursal) {
        super(numCuenta, iBAN, fechaCreacion, saldo);
        this.sucursal = sucursal;
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
        int result = super.hashCode();
        result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
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
        CuentaCorriente other = (CuentaCorriente) obj;
        if (sucursal == null) {
            if (other.sucursal != null)
                return false;
        } else if (!sucursal.equals(other.sucursal))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CuentaCorriente [sucursal=" + sucursal + "]";
    }

}