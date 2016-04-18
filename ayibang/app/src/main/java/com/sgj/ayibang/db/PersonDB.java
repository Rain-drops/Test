package com.sgj.ayibang.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.sgj.ayibang.model.Person;

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
                "(_ID INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, age INTEGER, phone VARCHAR UNIQUE);";
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
        values.put(PersonColumns.PHONE, person.getPhone());

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
                values.put(PersonColumns.PHONE, person.getPhone());
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

        database.update(PersonColumns.TABLE_NAME, values, PersonColumns.PHONE + "=? ", new String[]{name});

    }

    public synchronized Person getPersonByPhone(String phone){
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor = database.query(PersonColumns.TABLE_NAME,
                new String[]{}, PersonColumns.PHONE + "=?", new String[]{phone}, null, null, null);
        return getPersonForCursor(cursor);
    }

    public synchronized void getAll(){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        database.beginTransaction();

    }

    public Person getPersonForCursor(Cursor cursor){

        Person person = new Person();
        if((cursor != null) && (cursor.moveToFirst())){
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String phone = cursor.getString(3);
            person.setName(name);
            person.setAge(age);
            person.setPhone(phone);
        }
        if(cursor != null){
            cursor.close();
        }

        return person;
    }

    public ArrayList<Person> getPersonsForCursor(Cursor cursor){
        ArrayList<Person> personList = new ArrayList<>();
        if((cursor != null) && (cursor.moveToFirst())){
            do{
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String phone = cursor.getString(3);
                Person person = new Person(name, phone, age);
                personList.add(person);
            }while (cursor.moveToNext());
        }
        if(cursor != null){
            cursor.close();
        }
        return personList;
    }

    public class PersonColumns{
        public static final String TABLE_NAME = "person";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String PHONE = "phone";
    }
}
