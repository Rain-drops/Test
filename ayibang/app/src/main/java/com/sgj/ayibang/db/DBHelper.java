package com.sgj.ayibang.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by John on 2016/4/11.
 */
public class DBHelper extends SQLiteOpenHelper {

    static DBHelper mDbHelper = null;

    private static final String DATABAST_NAME = "sgj.db";
    private static final int DATABAST_VERSION = 1;
    private static final SQLiteDatabase.CursorFactory DATABASE_FACTORY = null;

    private Context mContext;

    public DBHelper(Context context) {
        super(context, DATABAST_NAME, DATABASE_FACTORY, DATABAST_VERSION);
        mContext = context;
    }

    public static final synchronized DBHelper getInstance(Context context){
        if(mDbHelper == null){
            mDbHelper = new DBHelper(context.getApplicationContext());
        }
        return mDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        PersonDB.getInstance(mContext).onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        PersonDB.getInstance(mContext).onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        PersonDB.getInstance(mContext).onCreate(db);
    }
}
