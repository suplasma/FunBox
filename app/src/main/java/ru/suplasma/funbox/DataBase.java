package ru.suplasma.funbox;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBase implements DBInterface {

    private DBHelper dbHelper;

    public DataBase(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public void read() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int priceIndex = cursor.getColumnIndex(DBHelper.KEY_PRICE);
            int quantityIndex = cursor.getColumnIndex(DBHelper.KEY_QUANTITY);
            do {
                Progress.names.add(cursor.getString(nameIndex));
                Progress.prices.add(cursor.getDouble(priceIndex));
                Progress.quantities.add(cursor.getInt(quantityIndex));
            } while (cursor.moveToNext());
        }

        cursor.close();

        dbHelper.close();
    }

    @Override
    public void write(int id, String name, String price, String quantity) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_NAME, name);
        contentValues.put(DBHelper.KEY_PRICE, price);
        contentValues.put(DBHelper.KEY_QUANTITY, quantity);

        if (id == -1) {
            database.insert(DBHelper.TABLE_NAME, null, contentValues);
        } else {
            id++;
            database.update(DBHelper.TABLE_NAME, contentValues, DBHelper.KEY_ID + " = " + id, null);
        }

        dbHelper.close();
    }
}
