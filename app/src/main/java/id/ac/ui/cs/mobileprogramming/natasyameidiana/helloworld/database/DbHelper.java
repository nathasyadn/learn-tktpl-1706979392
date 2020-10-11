package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.entity.UserData;

import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    MyDbHelper helper;

    public DbHelper(Context context) {
        helper = new MyDbHelper(context);
    }

    public long insertData(String name, String email, String description) {
        SQLiteDatabase dbb = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDbHelper.NAME, name);
        contentValues.put(MyDbHelper.EMAIL, email);
        contentValues.put(MyDbHelper.DESCRIPTION, description);
        long id = dbb.insert(MyDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public List<UserData> getData() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {MyDbHelper.UID, MyDbHelper.NAME, MyDbHelper.EMAIL, MyDbHelper.DESCRIPTION};
        Cursor cursor = db.query(MyDbHelper.TABLE_NAME, columns, null, null, null, null, null);

        List<UserData> userList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(MyDbHelper.NAME));
            String email = cursor.getString(cursor.getColumnIndex(MyDbHelper.EMAIL));
            String description = cursor.getString(cursor.getColumnIndex(MyDbHelper.DESCRIPTION));
            userList.add(new UserData(name, email, description));
        }
        return userList;
    }

    static class MyDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";
        private static final String TABLE_NAME = "myTable";
        private static final int DATABASE_Version = 1;
        private static final String UID = "_id";
        private static final String NAME = "name";
        private static final String EMAIL = "email";
        private static final String DESCRIPTION = "description";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," + EMAIL + " VARCHAR(255) ," + DESCRIPTION + " VARCHAR(225));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public MyDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }
}
