package hecrpu.simarro.bancoaplicacion.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.bd.Constantes;
import hecrpu.simarro.bancoaplicacion.dao.CajeroDAO;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;

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

    private Button boton_guardar;
    private Button boton_cancelar;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_cajero);

        /*Bundle bundle = getIntent().getExtras();
        bundle.getSerializable("cliente");*/


        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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

        boton_guardar = (Button) findViewById(R.id.boton_guardar);
        boton_cancelar = (Button) findViewById(R.id.boton_cancelar);

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

        boton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
        boton_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

    }

    private void establecerModo(int m) {
        this.modo = m;
        // Si estamos solamente visualizando establecemos el modo edicion desactivado a todo el formulario
        if (modo == Constantes.C_VISUALIZAR) {
            this.setTitle(direccion.getText().toString());
            this.setEdicion(false);
        } else if (modo == Constantes.C_CREAR) {
            this.setTitle(R.string.cajero_crear_titulo);
            this.setEdicion(true);
        } else if (modo == Constantes.C_EDITAR) {
            this.setTitle(R.string.cajero_editar_titulo);
            this.setEdicion(true);
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

    private void guardar() {
        //
        // Obtenemos los datos del formulario
        //
        ContentValues reg = new ContentValues();

        //
        // Si estamos en modo edición añadimos el identificador del registro que se utilizará en el update
        //
        if (modo == Constantes.C_EDITAR) {
            reg.put(CajeroDAO.C_COLUMNA_ID, id);
        }

        reg.put(CajeroDAO.C_COLUMNA_DIRECCION, direccion.getText().toString());
        reg.put(CajeroDAO.C_COLUMNA_LAT, latitud.getText().toString());
        reg.put(CajeroDAO.C_COLUMNA_LNG, longitud.getText().toString());
        reg.put(CajeroDAO.C_COLUMNA_ZOOM, zoom.getText().toString());

        if (modo == Constantes.C_CREAR) {
            cajeroDAO.insert(reg);
            Toast.makeText(GestionCajeroActivity.this, R.string.cajero_crear_confirmacion,
                    Toast.LENGTH_SHORT).show();
        }else if (modo == Constantes.C_EDITAR) {
            Toast.makeText(GestionCajeroActivity.this, R.string.cajero_editar_confirmacion,
                    Toast.LENGTH_SHORT).show();
            cajeroDAO.update(reg);
        }

        //
        // Devolvemos el control a MainActivity
        //
        setResult(RESULT_OK);
        finish();
    }

    private void cancelar() {
        setResult(RESULT_CANCELED, null);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        if (cliente.isIs_admin()) {
            if (modo == Constantes.C_VISUALIZAR)
                getMenuInflater().inflate(R.menu.menu_gestion_cajeros, menu);
            else
                getMenuInflater().inflate(R.menu.menu_cajeros_editar, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_editar:
                establecerModo(Constantes.C_EDITAR);
                return true;
            case R.id.menu_eliminar:
                borrar(id);
                return true;
            case R.id.menu_cancelar:
                cancelar();
                return true;
            case R.id.menu_guardar:
                guardar();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void borrar(final long id)
    {
        /**
         * Borramos el registro con confirmación
         */
        AlertDialog.Builder dialogEliminar = new AlertDialog.Builder(this);
        dialogEliminar.setIcon(android.R.drawable.ic_dialog_alert);
        dialogEliminar.setTitle(getResources().getString(R.string.cajero_eliminar_titulo));
        dialogEliminar.setMessage(getResources().getString(R.string.cajero_eliminar_mensaje));
        dialogEliminar.setCancelable(false);
        dialogEliminar.setPositiveButton(getResources().getString(android.R.string.ok), new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        cajeroDAO.delete(id);
                        Toast.makeText(GestionCajeroActivity.this, R.string.cajero_eliminar_confirmacion,
                                Toast.LENGTH_SHORT).show();
                        /**
                         * Devolvemos el control
                         */
                        setResult(RESULT_OK);
                        finish();
                    }
                });
        dialogEliminar.setNegativeButton(android.R.string.no, null);
        dialogEliminar.show();
    }
}