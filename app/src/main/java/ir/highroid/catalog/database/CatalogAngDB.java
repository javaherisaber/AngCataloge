package ir.highroid.catalog.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Script;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by Mahdizit on 20/06/2016.
 */
public class CatalogAngDB {

    private static final String DB_PATH = "/data/data/ir.highroid.catalog/databases/";
    private static final String DATABASE_NAME = "CatalogAng.db";
    private static final int    DATABASE_VERSION = 1;
    private static final String TAG = "CatalogAng_Database";

    public static final String TABLE_CATEGORY="category";
    public static final String TABLE_GALLERY="gallery";

    private static final String FIELD_ID = "_id";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_PIC = "pic";
    private static final String FIELD_IS_FAVORITE = "is_favorite";
    private static final String FIELD_FK_CATEGID= "fk_categID";


    public static final String[] COLUMNS_CATEGORY = {FIELD_ID,FIELD_TITLE,FIELD_PIC};
    public static final String[] COLUMNS_GALLERY = {FIELD_ID,FIELD_TITLE,FIELD_PIC,FIELD_IS_FAVORITE,FIELD_FK_CATEGID};


    private final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public CatalogAngDB(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
        try {

            DBHelper.createDataBase();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        private Context context;

        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
//            db.execSQL(CREATE_TABLE_FAVORITE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
        }

        private boolean checkDataBase() {
            try {
                final String DbPath = DB_PATH + DATABASE_NAME;
                final File file = new File(DbPath);//file for checking our database existence
                if (file.exists())
                {
                    Log.d(TAG, "Database already exist");
                    return true;
                }
                else
                {
                    return false;
                }
            } catch (SQLiteException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void createDataBase() throws IOException {
            boolean mDataBaseExist = checkDataBase();
            if (!mDataBaseExist) {
                this.getReadableDatabase();//makes our database readable
                try {
                    copyDataBase();//copy database from assets folder
                } catch (IOException mIOException) {
                    mIOException.printStackTrace();
                    throw new Error("Error copying database");
                } finally {
                    this.close();//closing this IO operation after accomplishing it
                }
            }
        }

        private void copyDataBase() throws IOException {
            try {
                //using an inputstram to open database from assets
                InputStream mInputStream = context.getAssets().open(DATABASE_NAME);
                String outFileName = DB_PATH + DATABASE_NAME;//output file dir
                OutputStream mOutputStream = new FileOutputStream(outFileName);//outputstream to make new database
                byte[] buffer = new byte[1024];//buffer size for write operation
                int length;
                while ((length = mInputStream.read(buffer)) > 0) {
                    mOutputStream.write(buffer, 0, length);//writing output byte by byte
                }
                mOutputStream.flush();//flushes the stream
                mOutputStream.close();
                mInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //---opens the database---
    public CatalogAngDB open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public synchronized void close()
    {
        DBHelper.close();
    }

    public long insertFavorite(int galleryID)
    {
        Cursor cursor = getFavoriteWithGalleryID(galleryID);
        cursor.moveToFirst();
        if(cursor.getCount() != 0)
            return -2;
        else {
            ContentValues values = new ContentValues();
            values.put(FIELD_IS_FAVORITE,1);

            return db.update(TABLE_GALLERY,values,FIELD_ID + " = " + galleryID,null);
        }
    }

    public boolean deleteFavorite(int galleryID){

        ContentValues values = new ContentValues();
        values.put(FIELD_IS_FAVORITE,0);

        return db.update(TABLE_GALLERY,values,FIELD_ID + " = " + galleryID,null)>0;
    }

    public Cursor getAllFavorites(){
        String sql="Select * from "+TABLE_GALLERY+" where "+FIELD_IS_FAVORITE +" = "+1;
        return db.rawQuery(sql,null);
    }

    public Cursor getAllRecords(String table, String[] columns)
    {
        return db.query(table, columns, null, null, null, null, null);
    }

    public Cursor getGalleriesWithCategID(int categID)
    {
        String sql="Select * from "+TABLE_GALLERY+" where "+FIELD_FK_CATEGID+" = "+categID;
        return db.rawQuery(sql,null);
    }

    public Cursor getFavoriteWithGalleryID(int galleryID)
    {
        String sql="Select * from "+TABLE_GALLERY+" where "+FIELD_ID+" = "+galleryID;
        return db.rawQuery(sql,null);
    }

    public String getCategTitleWithID(int categID){
        String sql="Select title from "+TABLE_CATEGORY+" where "+FIELD_ID+" = "+categID;
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

}