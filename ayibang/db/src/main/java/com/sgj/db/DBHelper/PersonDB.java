package com.sgj.db.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sgj.db.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2016/4/11.
 */
public class PersonDB {

    private static PersonDB mPersonDB = null;
    private DBHelper mDbHelper = null;

    public static final synchronized PersonDB getInstance(Context context){
        if(mPersonDB == null){
            mPersonDB = new PersonDB(context);
        }
        return mPersonDB;
    }

    public PersonDB(Context context) {
        mDbHelper = DBHelper.getInstance(context);
    }

    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE IF NOT EXISTS person " +
                "(_ID INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, age INTEGER, phone VARCHAR);";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion < 2 && newVersion >=2){
            onCreate(db);
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF NOT EXISTS person;";
        db.execSQL(sql);
        onCreate(db);
    }

    public synchronized void savePerson(Person person){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PersonColumns.NAME, person.getName());
        values.put(PersonColumns.AGE, person.getAge());
        values.put(PersonColumns.PHONE, person.getPhong());

        database.insert(PersonColumns.TABLE_NAME, null, values);

    }

    public synchronized void savePerson(ArrayList<Person> persons){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        database.beginTransaction();

        try {

            for(int i=0; i< persons.size(); i++){
                Person person = persons.get(i);
                ContentValues values = new ContentValues();
                values.put(PersonColumns.NAME, person.getName());
                values.put(PersonColumns.AGE, person.getAge());
                values.put(PersonColumns.PHONE, person.getPhong());
                database.insert(PersonColumns.TABLE_NAME, null, values);
            }
            database.setTransactionSuccessful();
        }catch (Exception e){

        }finally {
            database.endTransaction();
        }


    }

    public synchronized void deletePersonByPhone(String phone){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        database.delete(PersonColumns.TABLE_NAME, PersonColumns.PHONE + "=?", new String[]{});

    }

    public synchronized void deleteAll(){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        database.delete(PersonColumns.TABLE_NAME, null, null);
    }

    public synchronized void updatePersonByPhone(String phone, String name){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PersonColumns.NAME, name);

        database.update(PersonColumns.TABLE_NAME, values,PersonColumns.PHONE + "=? ", new String[]{name});

    }

    public synchronized Cursor getPersonByPhone(String phone){
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
;
        return database.query(PersonColumns.TABLE_NAME,
                new String[]{}, PersonColumns.PHONE + "=?", new String[]{phone}, null, null, null);
    }

    public synchronized void getAll(){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        database.beginTransaction();

    }

    public class PersonColumns{
        public static final String TABLE_NAME = "person";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String PHONE = "phone";
    }
}
