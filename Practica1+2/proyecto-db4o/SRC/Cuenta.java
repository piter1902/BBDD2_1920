import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public abstract class Cuenta {

    protected int Num_cuenta;

    protected String IBAN;

    protected Date Fecha_creacion;

    protected int Saldo;

    protected List Clientes;

    public Cuenta(int num_cuenta, String iBAN, Date fecha_creacion, int saldo) {
        Num_cuenta = num_cuenta;
        IBAN = iBAN;
        Fecha_creacion = fecha_creacion;
        Saldo = saldo;
        Clientes = new ArrayList();
    }

    public Cuenta(Cuenta cuen_base) {
        this(cuen_base.getNum_cuenta(), cuen_base.getIBAN(), cuen_base.getFecha_creacion(), cuen_base.getSaldo());
    }

    public Cuenta() {
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
        return Num_cuenta == cuenta.Num_cuenta && Objects.equals(IBAN, cuenta.IBAN)
                && Objects.equals(Fecha_creacion, cuenta.Fecha_creacion) && Saldo == cuenta.Saldo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Num_cuenta, IBAN, Fecha_creacion, Saldo);
    }

    public List getClientes() {
        return Clientes;
    }

    public void setClientes(List clientes) {
        Clientes = clientes;
    }

    public void addCliente(Cliente cliente) {
        Clientes.add(cliente);
    }

    public String printClientes(){
        List<String> resultado = new ArrayList<String>();
        for(Object c : Clientes){
            Cliente cli = (Cliente)c;
            resultado.add(cli.getNombre());
        }
        return resultado.toString();
    }
    @Override
    public String toString() {
        return "Cuenta [Clientes=" + printClientes() + " , Fecha_creacion=" + Fecha_creacion + ", IBAN=" + IBAN
                + ", Num_cuenta=" + Num_cuenta + ", Saldo=" + Saldo + "]";
    }

}