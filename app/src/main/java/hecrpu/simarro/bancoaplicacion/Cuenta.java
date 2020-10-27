package hecrpu.simarro.bancoaplicacion;

public class Cuenta {
    private String numCuenta, nombre;
    float saldo;

    public Cuenta(String nombre, String numCuenta, float saldo) {
        this.numCuenta = numCuenta;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    @Override
    public String toString() {
        return this.getNombre() + " " + this.getNumCuenta() + " " + this.getSaldo() + "€";
    }
}
