import java.util.Date;
import java.util.Objects;

public class Cuenta {

    private int Num_cuenta;

    private String IBAN;

    private Date Fecha_creacion;

    private int Saldo;

    public Cuenta(int Num_cuenta, String IBAN, Date Fecha_creacion, int Saldo) {
        this.Num_cuenta = Num_cuenta;
        this.IBAN = IBAN;
        this.Fecha_creacion = Fecha_creacion;
        this.Saldo = Saldo;
    }

    public int getNum_cuenta() {
        return this.Num_cuenta;
    }

    public void setNum_cuenta(int Num_cuenta) {
        this.Num_cuenta = Num_cuenta;
    }

    public String getIBAN() {
        return this.IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Date getFecha_creacion() {
        return this.Fecha_creacion;
    }

    public void setFecha_creacion(Date Fecha_creacion) {
        this.Fecha_creacion = Fecha_creacion;
    }

    public int getSaldo() {
        return this.Saldo;
    }

    public void setSaldo(int Saldo) {
        this.Saldo = Saldo;
    }

    public Cuenta Num_cuenta(int Num_cuenta) {
        this.Num_cuenta = Num_cuenta;
        return this;
    }

    public Cuenta IBAN(String IBAN) {
        this.IBAN = IBAN;
        return this;
    }

    public Cuenta Fecha_creacion(Date Fecha_creacion) {
        this.Fecha_creacion = Fecha_creacion;
        return this;
    }

    public Cuenta Saldo(int Saldo) {
        this.Saldo = Saldo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cuenta)) {
            return false;
        }
        Cuenta cuenta = (Cuenta) o;
        return Num_cuenta == cuenta.Num_cuenta && Objects.equals(IBAN, cuenta.IBAN) && Objects.equals(Fecha_creacion, cuenta.Fecha_creacion) && Saldo == cuenta.Saldo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Num_cuenta, IBAN, Fecha_creacion, Saldo);
    }

    @Override
    public String toString() {
        return "{" +
            " Num_cuenta='" + getNum_cuenta() + "'" +
            ", IBAN='" + getIBAN() + "'" +
            ", Fecha_creacion='" + getFecha_creacion() + "'" +
            ", Saldo='" + getSaldo() + "'" +
            "}";
    }
}