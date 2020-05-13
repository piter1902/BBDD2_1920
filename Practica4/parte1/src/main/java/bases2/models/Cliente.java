package bases2.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Cliente {
    
    @Id
    @Column(name = "DNI")
    private String DNI;

    @Column(name = "DIRECCION")
    private String Direccion;

    @Column(name = "TELEFONO")
    private int Telefono;

    @Column(name = "EMAIL")
    private String Email;

    @Column(name = "NOMBRE")
    private String Nombre;

    @Column(name = "APELLIDOS")
    private String Apellidos;

    @ManyToMany
    private List<Cuenta> cuentas;

    public Cliente() {
        cuentas = new ArrayList<>();
    }

    public Cliente(String dNI, String direccion, int telefono, String email, String nombre, String apellidos) {
        DNI = dNI;
        Direccion = direccion;
        Telefono = telefono;
        Email = email;
        Nombre = nombre;
        Apellidos = apellidos;
        cuentas = new ArrayList<>();
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }
    
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void addCuenta(Cuenta c){
        this.cuentas.add(c);
        c.getPropietarios().add(this);
    }

    public void removeCuenta(Cuenta c){
        if(cuentas.contains(c)){
            cuentas.remove(c);
            c.getPropietarios().remove(this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Apellidos == null) ? 0 : Apellidos.hashCode());
        result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
        result = prime * result + ((Direccion == null) ? 0 : Direccion.hashCode());
        result = prime * result + ((Email == null) ? 0 : Email.hashCode());
        result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
        result = prime * result + Telefono;
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
        Cliente other = (Cliente) obj;
        if (Apellidos == null) {
            if (other.Apellidos != null)
                return false;
        } else if (!Apellidos.equals(other.Apellidos))
            return false;
        if (DNI == null) {
            if (other.DNI != null)
                return false;
        } else if (!DNI.equals(other.DNI))
            return false;
        if (Direccion == null) {
            if (other.Direccion != null)
                return false;
        } else if (!Direccion.equals(other.Direccion))
            return false;
        if (Email == null) {
            if (other.Email != null)
                return false;
        } else if (!Email.equals(other.Email))
            return false;
        if (Nombre == null) {
            if (other.Nombre != null)
                return false;
        } else if (!Nombre.equals(other.Nombre))
            return false;
        if (Telefono != other.Telefono)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cliente [Apellidos=" + Apellidos + ", DNI=" + DNI + ", Direccion=" + Direccion + ", Email=" + Email
                + ", Nombre=" + Nombre + ", Telefono=" + Telefono + "]";
    }

}