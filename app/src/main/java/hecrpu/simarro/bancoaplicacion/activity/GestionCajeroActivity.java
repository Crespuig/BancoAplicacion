package hecrpu.simarro.bancoaplicacion.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.EditText;

import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.bd.Constantes;
import hecrpu.simarro.bancoaplicacion.dao.CajeroDAO;

public class GestionCajeroActivity extends AppCompatActivity {

    private Cursor cursor;

    private CajeroDAO cajeroDAO;

    //
    // Modo del formulario
    //
    private int modo;

    //
    // Identificador del registro que se edita cuando la opción es MODIFICAR
    //
    private long id;

    //
    // Elementos de la vista
    //
    private EditText direccion;
    private EditText latitud;
    private EditText longitud;
    private EditText zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_cajero);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if (extra == null) return;
        //
        // Obtenemos los elementos de la vista
        //
        direccion = (EditText) findViewById(R.id.direccion);
        latitud = (EditText) findViewById(R.id.latitud);
        longitud = (EditText) findViewById(R.id.longitud);
        zoom = (EditText) findViewById(R.id.zoom);

        //
        // Creamos el DAO
        //
        cajeroDAO = new CajeroDAO(this);
        try {
            cajeroDAO.abrir();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //
        // Obtenemos el identificador del registro si viene indicado
        //
        if (extra.containsKey(CajeroDAO.C_COLUMNA_ID)) {
            id = extra.getLong(CajeroDAO.C_COLUMNA_ID);
            consultar(id);
        }
        //
        // Establecemos el modo del formulario
        //
        establecerModo(extra.getInt(Constantes.C_MODO));

    }

    private void establecerModo(int m) {
        this.modo = m;
        // Si estamos solamente visualizando establecemos el modo edicion desactivado a todo el formulario
        if (modo == Constantes.C_VISUALIZAR) {
            this.setTitle(direccion.getText().toString());
            this.setEdicion(false);
        }
    }

    private void consultar(long id) {
        //
        // Consultamos el centro por el identificador
        //
        cursor = cajeroDAO.getRegistro(id);
        direccion.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_DIRECCION)));
        latitud.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_LAT)));
        longitud.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_LNG)));
        zoom.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_ZOOM)));

    }

    private void setEdicion(boolean opcion) {
        direccion.setEnabled(opcion);
        latitud.setEnabled(opcion);
        longitud.setEnabled(opcion);
        zoom.setEnabled(opcion);
    }
}