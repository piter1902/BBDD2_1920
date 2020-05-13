package bases2.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public abstract class Cuenta implements Serializable{

    @Id
    @Column(name = "NumCuenta")
    private String numCuenta;

    @Column(name = "IBAN")
    private String IBAN;

    @Column(name = "FechaCreacion")
    private Date fechaCreacion;

    @Column(name = "SALDO")
    private int saldo;

    @ManyToMany(mappedBy = "cuentas")
    private List<Cliente> propietarios;

    public Cuenta() {
        propietarios = new ArrayList<>();
    }

    public Cuenta(String numCuenta, String iBAN, Date fechaCreacion, int saldo) {
        this.numCuenta = numCuenta;
        IBAN = iBAN;
        this.fechaCreacion = fechaCreacion;
        this.saldo = saldo;
        this.propietarios = new ArrayList<>();
    
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String iBAN) {
        IBAN = iBAN;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public List<Cliente> getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(List<Cliente> propietarios) {
        this.propietarios = propietarios;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((IBAN == null) ? 0 : IBAN.hashCode());
        result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
        result = prime * result + ((numCuenta == null) ? 0 : numCuenta.hashCode());
        result = prime * result + ((propietarios == null) ? 0 : propietarios.hashCode());
        result = prime * result + saldo;
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
        Cuenta other = (Cuenta) obj;
        if (IBAN == null) {
            if (other.IBAN != null)
                return false;
        } else if (!IBAN.equals(other.IBAN))
            return false;
        if (fechaCreacion == null) {
            if (other.fechaCreacion != null)
                return false;
        } else if (!fechaCreacion.equals(other.fechaCreacion))
            return false;
        if (numCuenta == null) {
            if (other.numCuenta != null)
                return false;
        } else if (!numCuenta.equals(other.numCuenta))
            return false;
        if (propietarios == null) {
            if (other.propietarios != null)
                return false;
        } else if (!propietarios.equals(other.propietarios))
            return false;
        if (saldo != other.saldo)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cuenta [IBAN=" + IBAN + ", fechaCreacion=" + fechaCreacion + ", numCuenta=" + numCuenta
                + ", propietarios=" + propietarios + ", saldo=" + saldo + "]";
    }

    
}