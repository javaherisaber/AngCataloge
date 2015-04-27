package ir.highroid.catalog.database;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;

import ir.highroid.catalog.bundle.BundleCategory;
import ir.highroid.catalog.bundle.BundleGallery;

/**
 * Created by Mahdizit on 20/06/2016.
 */
public class DatabaseInteract {

    Context context;
    CatalogAngDB db;

    public DatabaseInteract(Context ctx){
        this.context = ctx;
        db  = new CatalogAngDB(context);
    }

    public ArrayList<BundleCategory> getCategories(){
        openDB();
        ArrayList<BundleCategory> categories = new ArrayList<>();
        Cursor cursor = db.getAllRecords(CatalogAngDB.TABLE_CATEGORY,CatalogAngDB.COLUMNS_CATEGORY);
        cursor.moveToFirst();
        if(cursor.getCount() != 0) {
            do {

                BundleCategory category = new BundleCategory();
                category.id = cursor.getInt(0);
                category.title = cursor.getString(1);
                category.pic = cursor.getString(2);
                categories.add(category);

            }while (cursor.moveToNext());
        }
        db.close();
        return categories;
    }

    public ArrayList<BundleGallery> getGalleries(int categID){
        openDB();
        ArrayList<BundleGallery> galleries = new ArrayList<>();
        Cursor cursor = db.getGalleriesWithCategID(categID);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            do {
                BundleGallery gallery = new BundleGallery();
                gallery.id = cursor.getInt(0);
                gallery.title = cursor.getString(1);
                gallery.pic = cursor.getString(2);
                gallery.is_favorite = cursor.getInt(3);
                gallery.fk_categID = cursor.getString(4);
                galleries.add(gallery);

            }while (cursor.moveToNext());
        }
        db.close();
        return galleries;
    }

    public ArrayList<BundleGallery> getFavorites(){
        openDB();
        ArrayList<BundleGallery> favorites = new ArrayList<>();
        Cursor cursor = db.getAllFavorites();
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            do {
                BundleGallery favorite = new BundleGallery();
                favorite.id = cursor.getInt(0);
                favorite.title = cursor.getString(1);
                favorite.pic = cursor.getString(2);
                favorite.is_favorite = cursor.getInt(3);
                favorite.fk_categID = cursor.getString(4);
                favorites.add(favorite);
            }while (cursor.moveToNext());
        }
        db.close();
        return favorites;
    }

    public boolean insertFavorite(int galleryID){
        openDB();
        boolean result = db.insertFavorite(galleryID) > 0;
        db.close();
        return result;
    }

    public boolean deleteFavorite(int galleryID){
        openDB();
        boolean result = db.deleteFavorite(galleryID);
        db.close();
        return result;
    }

    public String getCategTitleWithID(int categID){
        openDB();
        String result = db.getCategTitleWithID(categID);
        db.close();
        return result;
    }

    private void openDB(){
        try{
            db.open();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
