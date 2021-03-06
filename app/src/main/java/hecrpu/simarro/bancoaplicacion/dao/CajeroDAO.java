package hecrpu.simarro.bancoaplicacion.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hecrpu.simarro.bancoaplicacion.bd.MiBD;

public class CajeroDAO {

    /**
     * Definimos constante con el nombre de la tabla
     */
    public static final String C_TABLA = "cajeros";
    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID = "_id";
    public static final String C_COLUMNA_DIRECCION = "direccion";
    public static final String C_COLUMNA_LAT = "latitud";
    public static final String C_COLUMNA_LNG = "longitud";
    public static final String C_COLUMNA_ZOOM = "zoom";

    private Context contexto;
    private MiBD miBD;
    private SQLiteDatabase db;
    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{C_COLUMNA_ID, C_COLUMNA_DIRECCION, C_COLUMNA_LAT,
            C_COLUMNA_LNG, C_COLUMNA_ZOOM};

    public CajeroDAO(Context context) {
        this.contexto = context;
    }

    public CajeroDAO abrir() {
        miBD = new MiBD(contexto);
        db = miBD.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        miBD.close();
    }

    /**
     * Devuelve un cursor con todas las filas y todas las columnas de la tabla
     * SELECT * FROM hipotecas
     */
    public Cursor getCursor() {
        Cursor c = db.query(true, C_TABLA, columnas, null, null, null, null, null, null);
        return c;
    }

    //Devuelve un cursor con el resultado del select
    //SELECT * FROM hipotecas WHERE _id = 2
    public Cursor getRegistro(long id){
        String condicion = C_COLUMNA_ID + "=" + id;
        Cursor c = db.query( true, C_TABLA, columnas, condicion, null, null, null, null, null);
        //Nos movemos al primer registro de la consulta
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insert(ContentValues reg){
        if (db == null)
            abrir();
        return db.insert(C_TABLA, null, reg);
    }
    public long update(ContentValues reg){
        long result = 0;
        if (db == null)
            abrir();
        if (reg.containsKey(C_COLUMNA_ID)) {
            //
            // Obtenemos el id y lo borramos de los valores a actualizar, ya que el id no se actualizar
            //
            long id = reg.getAsLong(C_COLUMNA_ID);
            reg.remove(C_COLUMNA_ID);
            //
            // Actualizamos el registro con el identificador que hemos extraido
            //
            result = db.update(C_TABLA, reg, "_id=" + id, null);
        }
        return result;
    }
    public void delete(long _id){
        String condicion = C_COLUMNA_ID + "=" + _id;

        //Se borra el cliente indicado en el campo de texto
        MiBD.getDB().delete("cajeros", condicion, null);
    }
    public Cursor getAll(){
        Cursor cursor = MiBD.getDB().query("cajeros", columnas, null, null, null, null, null);
        return cursor;
    }
}
