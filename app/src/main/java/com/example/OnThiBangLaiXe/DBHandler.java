package com.example.OnThiBangLaiXe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.OnThiBangLaiXe.Model.LoaiBienBao;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private SQLiteDatabase mDatabase;
    private static final String DB_NAME = "db.sqlite";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, 1);
        this.mDatabase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        Log.d("SQLite", "Database được tạo hoặc mở");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean isLastestVersion(int version)
    {
        Cursor cursor = mDatabase.rawQuery("select GiaTri from ThongTin where TenThongTin='PB'", null);

        int pb = 0;

        if (cursor.moveToFirst()) {
            do {
                pb = Integer.parseInt(cursor.getString(0));
                Log.d("SQLite", cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return pb == version;
    }

    public void ghiLoaiBienBao(List<LoaiBienBao> dsLoaiBienBao)
    {
        for (LoaiBienBao lbb : dsLoaiBienBao)
        {
            ContentValues cv = new ContentValues();
            cv.put("MaLoaiBB", lbb.getMaLoaiBB());
            cv.put("TenLoaiBB", lbb.getTenLoaiBB());

            mDatabase.insert("LoaiBienBao", null, cv);
        }
    }

    public List<LoaiBienBao> docLoaiBienBao()
    {
        List<LoaiBienBao> dsLoaiBienBao = new ArrayList<>();

        Cursor cursor = mDatabase.rawQuery("select * from LoaiBienBao", null);

        if (cursor.moveToFirst()) {
            do {
                dsLoaiBienBao.add(new LoaiBienBao(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return dsLoaiBienBao;
    }
}
