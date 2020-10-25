package hecrpu.simarro.bancoaplicacion;

public class Cuenta {
    private String numCuenta;

    public Cuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numCuenta='" + numCuenta + '\'' +
                '}';
    }
}
