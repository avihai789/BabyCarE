package com.example.babycare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * this class is handling the database
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "History.db";
    public static final String CREATE_TABLE = "create table History (DATE text)";
    public DatabaseHandler(Context context){
        super(context, DB_NAME, null, 1);
    }

    /**
     * creates the table
     * @param db the databse
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    /**
     *
     * upgardes the table (not in use)
     * @param db tha data base
     * @param oldVersion databases old version
     * @param newVersion databases new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists History ");
        onCreate(db);
    }

    /**
     * shows all the data from the table
     * @return a cruser
     */
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM History ", null);
    }

    /**
     * deletes all the data from the table
     */
    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM History");
    }

}
