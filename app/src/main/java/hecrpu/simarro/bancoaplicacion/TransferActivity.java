package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity {

    private GridView gridView;
    private GridAdapter gridAdapter;
    private Spinner spinnerCuenta;
    private Spinner spinnerDivisa;
    private DivisaAdapter divisaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("ES161254587896023002145");
        arrayList.add("ES165552145872002156589");
        arrayList.add("ES165458887895210231254");
        arrayList.add("ES151252425587854521544");

        gridView = (GridView) findViewById(R.id.gridTransfer);
        gridAdapter = new GridAdapter(this, arrayList);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Intent intent = new Intent(TransferActivity.this, DatosCuenta.class);
                intent.putExtra("Número cuenta", gridAdapter.getItem(posicion).toString());
                startActivity(intent);
            }
        });

        // Spinner cuenta
        spinnerCuenta = (Spinner) findViewById(R.id.spinnerTransfer);
        spinnerCuenta.setAdapter(gridAdapter);

        //Spinner divisa
        ArrayList<String> divisaArray = new ArrayList<>();
        divisaArray.add("€");
        divisaArray.add("$");
        spinnerDivisa = (Spinner) findViewById((R.id.divisaTransfer));
        divisaAdapter = new DivisaAdapter(this, divisaArray);
        spinnerDivisa.setAdapter(divisaAdapter);


    }


}