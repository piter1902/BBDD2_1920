package bases2.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CuentaAhorro extends Cuenta implements Serializable{

    @Column(name = "INTERES")
    private double interes;

    public CuentaAhorro() {
        super();
    }

    public CuentaAhorro(String numCuenta, String iBAN, Date fechaCreacion, int saldo, double interes) {
        super(numCuenta, iBAN, fechaCreacion, saldo);
        this.interes = interes;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(interes);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        if (Double.doubleToLongBits(interes) != Double.doubleToLongBits(other.interes))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CuentaAhorro [interes=" + interes + "]";
    }

    
}