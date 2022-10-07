package com.example.testegraficos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataBaseHelser extends SQLiteOpenHelper {

    private String nomeTabela = "tbl_Reg_Tabuas";

    private String [] datas = {"11/07/2002","11/07/2062","11/07/2042","11/07/2022","11/07/2030","11/07/1999","11/07/2007","11/07/2012","11/07/2002","20/07/2002"};
    private String [] categorias = {"categoria1","categoria1","categoria2","categoria5","categoria7","categoria1","categoria4","categoria5","categoria4","categoria4"};
    private double [] valores = {10,20,5,6,7,8,9,0,3,11};
    private int [] turnos = {1,2,1,2,1,1,1,2,3,3};

    public DataBaseHelser(@Nullable Context context) {
        super(context,"dataBase_name",null,1);
    }

    //todo converter data para datetime
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+ nomeTabela
                + "(id INTEGER primary key autoincrement, data TEXT, categoria TEXT, valor FLOAT, turno INTEGER)";

        try {
            sqLiteDatabase.execSQL(query);


            for (int j = 0;j < 10;j++) {
                insert(datas[j],categorias[j],valores[j],turnos[j]);
            }
        } catch (Exception e) {
            Log.e("Erro ao criar tabela",e.getMessage());
        }
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
