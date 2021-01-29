package hecrpu.simarro.bancoaplicacion.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;

public class CajerosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //Definimos la lista
    private ListView lista;
    private CajeroDAO cajeroDAO;
    private GestionCajeroAdapter gestionCajeroAdapter;
    private Cursor cursor;
    private TextView v_txtSinDatos;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajeros);

        /*Bundle bundle = getIntent().getExtras();
        bundle.getSerializable("cliente");*/
        cliente = (Cliente) getIntent().getSerializableExtra("cliente");
        Log.i("////////////////////", "cliente" + cliente.getNombre());

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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

        i.putExtra("cliente", cliente);

        // Iniciamos la actividad esperando un resultado, que en este caso no nos importa cual sea
        startActivityForResult(i, Constantes.C_VISUALIZAR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (cliente.isIs_admin())
            getMenuInflater().inflate(R.menu.menu_cajeros, menu);
        return true;



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent i;
        switch (item.getItemId()) {
            case R.id.menu_crear:
                i = new Intent(CajerosActivity.this, GestionCajeroActivity.class);
                i.putExtra(Constantes.C_MODO, Constantes.C_CREAR);
                i.putExtra("cliente", cliente);
                startActivityForResult(i, Constantes.C_CREAR);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //
        // Nos aseguramos que es la petición que hemos realizado
        //
        switch(requestCode) {
            case Constantes.C_CREAR:
                if (resultCode == RESULT_OK)
                    recargar_lista();
            case Constantes.C_VISUALIZAR:
                if (resultCode == RESULT_OK)
                    recargar_lista();
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void recargar_lista() {
        CajeroDAO cajeroDAO = new CajeroDAO(getBaseContext());
        cajeroDAO.abrir();
        GestionCajeroAdapter gestionCajeroAdapter = new GestionCajeroAdapter(this, cajeroDAO.getCursor());
        lista.setAdapter(gestionCajeroAdapter);
    }
}