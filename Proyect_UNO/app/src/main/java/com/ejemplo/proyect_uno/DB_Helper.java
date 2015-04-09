package com.ejemplo.proyect_uno;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper{

    private static final String DB_NAME = "juego_uno.sqlite";
    private static final int DB_SCHEMA_VERSION = 1;

    public DB_Helper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.CREATE_TABLE_PARTIDA);
        db.execSQL(DataBaseManager.CREATE_TABLE_JUGADOR);
        db.execSQL(DataBaseManager.CREATE_TABLE_CARTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
