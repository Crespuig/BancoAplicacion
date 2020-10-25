package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TransferActivity extends AppCompatActivity {

    GridView gridView;
    GridAdapter<Cuenta> gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        gridView = (GridView) findViewById(R.id.gridTransfer);
        String[] datos = {"Elem1", "Elem2", "Elem3", "Elem4"};

        gridAdapter = new GridAdapter<Cuenta>(this, DatosCuenta.CUENTAS);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener((AdapterView.OnItemClickListener) this);

    }


}