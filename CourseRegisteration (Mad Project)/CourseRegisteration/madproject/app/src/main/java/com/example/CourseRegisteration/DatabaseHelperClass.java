package com.example.CourseRegisteration;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "BookLibrary.db";

    private static final String TABLE_NAME = "my_library";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_COURSE = "Course";
    private static final String COLUMN_Fees = "Fees";
    private SQLiteDatabase sqLiteDatabase;


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_COURSE + " TEXT, " +
            COLUMN_Fees + " INTEGER);";

    public DatabaseHelperClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addBook(LibraryModelClass libraryModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.COLUMN_NAME, libraryModelClass.getName());
        contentValues.put(DatabaseHelperClass.COLUMN_COURSE, libraryModelClass.getCourse());
        contentValues.put(DatabaseHelperClass.COLUMN_Fees, libraryModelClass.getFees());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<LibraryModelClass> getBookList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<LibraryModelClass> storeBook = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        try{
            if (cursor.moveToFirst()){
                do {
                    int id = Integer.parseInt(cursor.getString(0));
                    String Name = cursor.getString(1);
                    String Course = cursor.getString(2);
                    String Fees = cursor.getString(3);
                    storeBook.add(new LibraryModelClass(id,Name,Course,Fees));
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch (Exception e){

        }
        return storeBook;
    }

    public void updateEmployee(LibraryModelClass libraryModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.COLUMN_NAME, libraryModelClass.getName());
        contentValues.put(DatabaseHelperClass.COLUMN_COURSE, libraryModelClass.getCourse());
        contentValues.put(DatabaseHelperClass.COLUMN_Fees, libraryModelClass.getFees());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,COLUMN_ID + " = ?" , new String[]
                {String.valueOf(libraryModelClass.getId())});
    }

    public void deleteEmployee(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}
