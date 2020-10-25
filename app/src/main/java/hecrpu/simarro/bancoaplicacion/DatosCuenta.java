package hecrpu.simarro.bancoaplicacion;

import java.util.ArrayList;
import java.util.List;

public class DatosCuenta {
    static List CUENTAS = new ArrayList<Cuenta>();

    static {
        CUENTAS.add(new Cuenta("ES12345678912345678900"));
        CUENTAS.add(new Cuenta("ES52012458795420123600"));
        CUENTAS.add(new Cuenta("ES65854587895442201202"));
        CUENTAS.add(new Cuenta("ES21500025458655584545"));
    }

}
