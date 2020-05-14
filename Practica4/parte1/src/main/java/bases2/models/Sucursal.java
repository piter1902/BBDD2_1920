package bases2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sucursal {
    
    @Id
    @Column(name = "CODIGO")
    private int codigo;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    private int telefono;

    public Sucursal() {}

    public Sucursal(int codigo, String direccion, int telefono) {
        this.codigo = codigo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
        result = prime * result + telefono;
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
        Sucursal other = (Sucursal) obj;
        if (codigo != other.codigo)
            return false;
        if (direccion == null) {
            if (other.direccion != null)
                return false;
        } else if (!direccion.equals(other.direccion))
            return false;
        if (telefono != other.telefono)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Sucursal [codigo=" + codigo + ", direccion=" + direccion + ", telefono=" + telefono + "]";
    }

}