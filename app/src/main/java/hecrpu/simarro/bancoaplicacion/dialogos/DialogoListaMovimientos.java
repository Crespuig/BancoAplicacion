package hecrpu.simarro.bancoaplicacion.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import hecrpu.simarro.bancoaplicacion.R;

public class DialogoListaMovimientos extends DialogFragment {
    public String info;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_lista_movimientos, null))
                .setTitle("Seleccion")
                .setMessage(info).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("Dialogo", "Accion acapetada");
                dialogInterface.cancel();
            }
        });

        return builder.create();

    }
}
