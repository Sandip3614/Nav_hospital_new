package sk.nav_hospital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper{

    public static final String DATABASENAME ="hospital_new4.db";
    public static final String TABLE_NAME ="hospital_table";
    public static final String COL1 ="hospital_name";
    public static final String COL2 ="address";
    public static final String COL3 ="no_of_bed";
    public static final String COL4 ="no_of_doctor";
    public static final String COL5 ="payment_mode";
    public static final String COL6 ="latitude";
    public static final String COL7 ="longitude";
    public static final String COL8 ="rating";
    public static final String COL9 ="phone";


    public Database(Context context) {
        super(context, DATABASENAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"("+COL1 +" TEXT PRIMARY KEY,"+COL2+" TEXT,"+COL3+" TEXT,"+COL4+" TEXT,"+COL5+" TEXT, "+COL6+" REAL, "+COL7+" REAL,"+COL8+" TEXT,"+COL9+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);


    }

    public boolean insertData(String hospital_name,String address , String no_of_bed,String no_of_doctor,String payment_mode, double latitude, double longitude, String rating,String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVales = new ContentValues();

        contentVales.put(COL1,hospital_name);
        contentVales.put(COL2,address);
        contentVales.put(COL3,no_of_bed);
        contentVales.put(COL4,no_of_doctor);
        contentVales.put(COL5,payment_mode);
        contentVales.put(COL6,latitude);
        contentVales.put(COL7,longitude);
        contentVales.put(COL8,rating);
        contentVales.put(COL9,phone);

        long result = db.insert(TABLE_NAME,null,contentVales);

        if (result == -1)
            return false;
        else
            return true;



    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);

        return res;


    }

    public Cursor getSpecificCol(String place_name)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE hospital_name = ?", new String[] { place_name });
    }


    public  Cursor getSearch(String search_txt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE hospital_name LIKE ?", new String[] { '%'+search_txt+'%' });


    }



}
