package com.example.testegraficos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataBaseHelser extends SQLiteOpenHelper {

    private String nomeTabela = "tbl_Reg_Tabuas";

    public DataBaseHelser(@Nullable Context context) {
        super(context,"dataBase_name",null,1);
    }

    //todo converter data para datetime
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+ nomeTabela
                + "(id INTEGER primary key autoincrement, data TEXT, categoria TEXT, valor FLOAT, turno INTEGER)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS "+ nomeTabela;

        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public void insert (String data, String categoria,double valor, int turno) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("data",data);
        contentValues.put("categoria",categoria);
        contentValues.put("valor",valor);
        contentValues.put("turno",turno);

        sqLiteDatabase.insertWithOnConflict(nomeTabela,null,contentValues, SQLiteDatabase.CONFLICT_REPLACE);

        sqLiteDatabase.close();
    }

    public void update (int id, String data, String categoria,double valor, int turno) {
        SQLiteDatabase database = getWritableDatabase();

        String query = "UPDATE "+ nomeTabela+ " SET data = '"+ data + "', categoria = '"+categoria+"', valor = '"+valor+"', turno = '"+turno+"' WHERE id = '" + id+"'";

        database.execSQL(query);
        database.close();
    }

    public void delete (String id) {
        SQLiteDatabase database = getWritableDatabase();

        String query = "DELETE FROM "+ nomeTabela +" WHERE id = '"+id+"'";

        database.execSQL(query);

        database.close();
    }

    public  void truncate () {
        SQLiteDatabase database = getWritableDatabase();

        String query = "DELETE FROM '" + nomeTabela + "'";

        String query2 = "DELETE FROM sqlite_sequence where name = '" + nomeTabela + "'";

        database.execSQL(query);
        database.execSQL(query2);

        database.close();

    }

    public ArrayList<Dado> getArray2 () {
        SQLiteDatabase database = getWritableDatabase();

        ArrayList<Dado> arr = new ArrayList<>();

        String sQuery = "SELECT * FROM "+ nomeTabela;

        Cursor cursor = database.rawQuery(sQuery, null);

        if (cursor.moveToFirst()) {
            do {
                try {

                    Dado dado = new Dado(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                    arr.add(dado);

                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return arr;
    }
}
