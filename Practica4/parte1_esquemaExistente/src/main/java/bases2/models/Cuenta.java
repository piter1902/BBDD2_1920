package bases2.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CUENTA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cuenta implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NUM_CUENTA", nullable = false)
    private int numCuenta;

    @Column(name = "IBAN", nullable = false)
    private String IBAN;

    @Column(name = "FECHA_CREACION", nullable = false)
    private Date fechaCreacion;

    @Column(name = "SALDO", nullable = false)
    private int saldo;

    @Column(name = "TIPO")
    private String tipo;

    @ManyToMany(mappedBy = "cuentas", fetch = FetchType.LAZY)
    private List<Cliente> propietarios;

    public Cuenta() {
        propietarios = new ArrayList<>();
    }

    public Cuenta(int numCuenta, String iBAN, Date fechaCreacion, int saldo, String tipo) {
        this.numCuenta = numCuenta;
        IBAN = iBAN;
        this.fechaCreacion = fechaCreacion;
        this.saldo = saldo;
        this.tipo = tipo;
        propietarios = new ArrayList<>();
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        result = prime * result + numCuenta;
        result = prime * result + ((propietarios == null) ? 0 : propietarios.hashCode());
        result = prime * result + saldo;
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
        if (numCuenta != other.numCuenta)
            return false;
        if (propietarios == null) {
            if (other.propietarios != null)
                return false;
        } else if (!propietarios.equals(other.propietarios))
            return false;
        if (saldo != other.saldo)
            return false;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cuenta [IBAN=" + IBAN + ", fechaCreacion=" + fechaCreacion + ", numCuenta=" + numCuenta
                + ", saldo=" + saldo + ", tipo=" + tipo + "]";
    }

    
    
}