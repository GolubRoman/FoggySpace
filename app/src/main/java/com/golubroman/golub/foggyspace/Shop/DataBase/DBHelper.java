package com.golubroman.golub.foggyspace.Shop.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.golubroman.golub.foggyspace.R;

/**
 * Created by roman on 27.03.17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private ContentValues contentValues;
    final static String tableGOODS = "tableGOODS";
    final static String columnTASTE = "columnTASTE";
    final static String columnDESCRIPTION = "columnDESCRIPTION";
    final static String columnPRICE = "columnPRICE";
    final static String column_ID = "_ID";
    final static String createTABLE = "CREATE TABLE ";
    final static String primaryKEY = " INTEGER PRIMARY KEY AUTOINCREMENT, ";
    final static String textNotNULL = " TEXT NOT NULL";
    private static String data;


    public DBHelper(Context context) {
        super(context, "goodsDB", null, 1);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        data = context.getString(R.string.data);
        db.execSQL(createTABLE + tableGOODS + " ("
                + column_ID + primaryKEY
                + columnTASTE + textNotNULL + ", "
                + columnDESCRIPTION + textNotNULL + ", "
                + columnPRICE + textNotNULL + ");");

        parseNPutToDB(db, context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void parseNPutToDB(SQLiteDatabase db, Context context){
        contentValues = new ContentValues();
        String dbString = data;
        String[] goods = dbString.split(";");
        for(int i = 0; i < goods.length; i++){
            String[] specifications = goods[i].split("#");
            contentValues.clear();
            contentValues.put(columnTASTE, specifications[0]);
            contentValues.put(columnDESCRIPTION, specifications[1]);
            contentValues.put(columnPRICE, specifications[2]);
            db.insert(tableGOODS, null, contentValues);
        }
    }

}

