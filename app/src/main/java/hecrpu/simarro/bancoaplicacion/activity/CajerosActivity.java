package hecrpu.simarro.bancoaplicacion.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.adaptador.GestionCajeroAdapter;
import hecrpu.simarro.bancoaplicacion.bd.Constantes;
import hecrpu.simarro.bancoaplicacion.bd.MiBD;
import hecrpu.simarro.bancoaplicacion.dao.CajeroDAO;

public class CajerosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //Definimos la lista
    private ListView lista;
    private CajeroDAO cajeroDAO;
    private GestionCajeroAdapter gestionCajeroAdapter;
    private Cursor cursor;
    private TextView v_txtSinDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajeros);


        lista = (ListView) findViewById(R.id.lista);
        // Creamos la clase que nos permitira acceder a las operaciones de la db
        cajeroDAO = new CajeroDAO(this);

        lista.setOnItemClickListener(this);

        /*
         * Declaramos el controlador de la BBDD y accedemos en modo escritura
         */
        /*MiBD dbHelper = new MiBD(getBaseContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();*/

        try {
            // Abrimos la base de datos
            cajeroDAO.abrir();

            // Obtenemos el cursor
            cursor = cajeroDAO.getCursor();

            // Se indica que a la Actividad principal que controle los recursos
            // cursor. Es decir, si se termina la Actividad, se elimina este cursor de la memoria
            startManagingCursor(cursor);

            // Creamos el adaptador
            gestionCajeroAdapter = new GestionCajeroAdapter(this, cursor);

            // Asignamos el adaptador a la lista
            lista.setAdapter(gestionCajeroAdapter);
            // Si hay datos no se muestra la etiqueta de Sin Datos
            if (cursor.getCount() > 0) {
                v_txtSinDatos = (TextView) findViewById(R.id.txtSinDatos);
                v_txtSinDatos.setVisibility(View.INVISIBLE);
                v_txtSinDatos.invalidate();
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Se ha producido un error al abrir la base de datos.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();

        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        // Creamos el intent para abrir el formulario de hipotecas
        Intent i = new Intent(CajerosActivity.this, GestionCajeroActivity.class);

        // Le pasamos que el modo en que lo vamos a abrir es solo de visualizacion
        i.putExtra(Constantes.C_MODO, Constantes.C_VISUALIZAR);

        // Le pasamos el valor del identificador de la hipoteca
        i.putExtra(CajeroDAO.C_COLUMNA_ID, id);

        // Iniciamos la actividad esperando un resultado, que en este caso no nos importa cual sea
        startActivityForResult(i, Constantes.C_VISUALIZAR);
    }
}