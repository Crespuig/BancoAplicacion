package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;
import hecrpu.simarro.bancoaplicacion.pojo.Movimiento;

public class TransferActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView gridView;
    private Spinner spinnerCuenta;
    private Spinner spinnerDivisa;
    private DivisaAdapter divisaAdapter;
    private EditText importeTransfer;
    private EditText cuentaDestinoTransfer;
    private CheckBox checkBoxTransfer;
    private Button botonOk;
    private Button botonCancelar;
    private String cuentaOrigen = "";
    private String cuentaDestino = "";
    private float ingreso = 0;
    private Cliente c;
    private Movimiento movimiento;
    private MiBancoOperacional miBancoOperacional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        c = (Cliente) getIntent().getSerializableExtra("cliente");

        miBancoOperacional = MiBancoOperacional.getInstance(this.getApplicationContext());

        ArrayAdapter<hecrpu.simarro.bancoaplicacion.pojo.Cuenta> adaptador = new GridAdapter<>(this, c.getListaCuentas(), R.layout.item_grid);
        gridView = (GridView) findViewById(R.id.gridTransfer);
        gridView.setAdapter(adaptador);
        spinnerCuenta = (Spinner) findViewById(R.id.spinnerTransfer);
        adaptador = new GridAdapter<hecrpu.simarro.bancoaplicacion.pojo.Cuenta>(this, c.getListaCuentas(), R.layout.item_spinner);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCuenta.setAdapter(adaptador);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cuentaOrigen = ((Cuenta)parent.getItemAtPosition(position)).getNumeroCuenta();
                TextView textView = (TextView) findViewById(R.id.transferTextView4);
                textView.setText("Cuenta seleccionada: " + cuentaOrigen);
            }
        });

        spinnerCuenta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cuentaDestino = ((hecrpu.simarro.bancoaplicacion.pojo.Cuenta)parent.getItemAtPosition(position)).getNumeroCuenta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGrTransfer);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton)findViewById(checkedId);

                if(radioButton.getText().toString().contentEquals("Propia")){
                    spinnerCuenta.setVisibility(View.VISIBLE);
                    importeTransfer.setVisibility(View.VISIBLE);
                    cuentaDestinoTransfer.setVisibility(View.INVISIBLE);
                }else{
                    spinnerCuenta.setVisibility(View.INVISIBLE);
                    cuentaDestinoTransfer.setVisibility(View.VISIBLE);
                    importeTransfer.setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.transferRadioButton1).setOnClickListener(this);*/

        //Spinner divisa
        ArrayList<String> divisaArray = new ArrayList<>();
        divisaArray.add("€");
        divisaArray.add("$");
        spinnerDivisa = (Spinner) findViewById((R.id.divisaTransfer));
        divisaAdapter = new DivisaAdapter(this, divisaArray);
        spinnerDivisa.setAdapter(divisaAdapter);

        //Elementos cuenta ajena
        /*findViewById(R.id.transferRadioButton2).setOnClickListener(this);
        importeTransfer = (EditText) findViewById(R.id.importeTransfer);
        cuentaDestinoTransfer = (EditText) findViewById(R.id.cuentaDestinoTransfer);*/

        //botones
        findViewById(R.id.checkBoxTransfer).setOnClickListener(this);
        findViewById(R.id.transferButtonOk).setOnClickListener(this);
        findViewById(R.id.transferButtonCancelar).setOnClickListener(this);


    }

    // Ocultar y mostrar elementos
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.transferButtonOk:
                movimiento = new Movimiento();
                miBancoOperacional.transferencia(movimiento);
                break;
            case R.id.transferButtonCancelar:
                importeTransfer.setText("");
                cuentaDestinoTransfer.setText("");
                Toast.makeText(getApplicationContext(), "Transferencia cancelada", Toast.LENGTH_LONG).show();
                break;
            case R.id.checkBoxTransfer:
                break;

        }
    }



}