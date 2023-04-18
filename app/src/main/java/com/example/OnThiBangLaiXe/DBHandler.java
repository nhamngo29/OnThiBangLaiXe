package com.example.OnThiBangLaiXe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
    private SQLiteDatabase mDatabase;
    private static final String DB_NAME = "db.sqlite";

    private final int DB_VERSION;

    public DBHandler(Context context, int DB_VERSION) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        this.DB_VERSION = DB_VERSION;
        this.mDatabase = sqLiteDatabase;
        Log.d("SQLite", "Database được tạo hoặc mở");

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void checkVersion()
    {
        int version = mDatabase.getVersion();

        if (DB_VERSION != version)
        {
            Log.d("SQlite", "An update is necessary");
            // Thêm hàm update database vào
        }
    }
}
