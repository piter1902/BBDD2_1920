package bases2.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "CUENTA_AHORRO")
@PrimaryKeyJoinColumn(name = "ID_CUENTA")
public class CuentaAhorro extends Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "INTERES", nullable = false)
    private int interes;

    public CuentaAhorro() {
        super();
    }

    public CuentaAhorro(int numCuenta, String iBAN, Date fechaCreacion, int saldo, String tipo, int interes) {
        super(numCuenta, iBAN, fechaCreacion, saldo, tipo);
        this.interes = interes;
    }

    public int getInteres() {
        return interes;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + interes;
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
        CuentaAhorro other = (CuentaAhorro) obj;
        if (interes != other.interes)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CuentaAhorro [interes=" + interes + " super=" + super.toString() + "]";
    }
    
}