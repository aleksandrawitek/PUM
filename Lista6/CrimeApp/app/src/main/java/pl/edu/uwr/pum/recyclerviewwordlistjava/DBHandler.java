package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "crimes.db";
    public static final String TABLE_CRIMES = "crimestab";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CRIMEID = "crimeid";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_SOLVED = "solved";
    private static final String COLUMN_IMAGE = "image";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CRIMES_TABLE = "CREATE TABLE " +
                TABLE_CRIMES +
                "(" +
                COLUMN_NAME +
                " TEXT," +
                COLUMN_CRIMEID +
                " TEXT," +
                COLUMN_DATE +
                " TEXT," +
                //COLUMN_IMAGE + " TEXT,"+
                COLUMN_SOLVED +
                " BOOLEAN" +
                ")";

        db.execSQL(CREATE_CRIMES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CRIMES);
        onCreate(db);
    }

    public Cursor getCrime(){
        String query = "SELECT * FROM " + TABLE_CRIMES;
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    public void addCrime(Crime crime){

        ContentValues values = new ContentValues();
        values.put(COLUMN_CRIMEID, crime.getId().toString());
        values.put(COLUMN_NAME, crime.getTitle());
        values.put(COLUMN_DATE, crime.getDate().toString());
        values.put(COLUMN_SOLVED, crime.getSolved());
        //values.put(COLUMN_IMAGE,crime.getImage());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CRIMES, null, values);
        db.close();
    }


    public void deleteCrime(Crime crime){


        String query = "SELECT * FROM " +
                TABLE_CRIMES +
                " WHERE " +
                COLUMN_CRIMEID +
                "=" +
                crime.getId().toString();

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CRIMES, COLUMN_CRIMEID + " = ?",new String[]{crime.getId().toString()});
        db.close();
    }

    public void updateCrime(Crime crime){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, crime.getTitle());
        values.put(COLUMN_DATE, crime.getDate().toString());
        values.put(COLUMN_SOLVED, crime.getSolved());
        //values.put(COLUMN_IMAGE, crime.getImage());
        db.update(TABLE_CRIMES, values, COLUMN_CRIMEID + " = ?", new String[]{crime.getId().toString()});
        db.close();
    }

    public Cursor searchCrime(String crimeName) {

        String query;
        if(crimeName.equals("")) {
            query = "SELECT * FROM " + TABLE_CRIMES;
        }
        else{
            query = "SELECT * FROM " + TABLE_CRIMES + " WHERE " + COLUMN_NAME + " LIKE \"" + "%" + crimeName + "%" + "\"";
        }
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query,null);
    }

}
