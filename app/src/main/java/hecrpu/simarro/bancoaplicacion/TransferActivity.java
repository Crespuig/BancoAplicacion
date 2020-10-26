package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity {

    private GridView gridView;
    private GridAdapter gridAdapter;

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

    }


}