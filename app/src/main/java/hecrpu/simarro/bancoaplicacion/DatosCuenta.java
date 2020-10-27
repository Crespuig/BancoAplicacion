package hecrpu.simarro.bancoaplicacion;


import java.util.ArrayList;
import java.util.List;

public class DatosCuenta {
    static List CUENTA = new ArrayList<Cuenta>();

    static {
        CUENTA.add(new Cuenta("Cuenta 1", "1234", 100.00f));
        CUENTA.add(new Cuenta("Cuenta 2", "5678", 200.00f));
        CUENTA.add(new Cuenta("Cuenta 3", "9101", 300.00f));
        CUENTA.add(new Cuenta("Cuenta 4", "1213", 400.00f));
    }


}
