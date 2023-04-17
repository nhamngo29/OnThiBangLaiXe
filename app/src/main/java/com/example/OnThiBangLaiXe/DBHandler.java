package com.example.OnThiBangLaiXe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.Model.LoaiBienBao;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private SQLiteDatabase mDatabase;

    private static final String DB_NAME = "db.db";
    private static final int DB_VERSION = 1;
    public DBHandler(Context context) {
        super(context, DB_NAME, null, 2);
        context.openOrCreateDatabase(DB_NAME, context.MODE_PRIVATE, null);
        this.mDatabase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        mDatabase=this.getWritableDatabase();
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
    public void insertBB(BienBao bb)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaBB",bb.getMaBB());
        contentValues.put("MaLoaiBB",bb.getMaLoaiBB());
        contentValues.put("TieuDe",bb.getTieuDe());
        contentValues.put("NoiDung",bb.getNoidung());
        contentValues.put("HinhAnh",bb.getHinhAnh());
        mDatabase.insert("BienBao",null,contentValues);
        mDatabase.close();
    }
    Boolean findBBByID(String MaBB)
    {
        mDatabase=this.getWritableDatabase();
        Cursor cursor3= mDatabase.rawQuery("select MaBB FROM BienBao WHERE TRIM(MaBB) = '"+MaBB+"'",null);
        cursor3.moveToFirst();
        if(cursor3==null)
        {
            if(MaBB==cursor3.getString(0))
            {
                cursor3.close();
                return true;
            }
        }
        cursor3.close();
        return false;
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        db.setVersion(newVersion);
    }

    public void updateBB(BienBao bb)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaBB",bb.getMaBB());
        contentValues.put("MaLoaiBB",bb.getMaLoaiBB());
        contentValues.put("TieuDe",bb.getTieuDe());
        contentValues.put("NoiDung",bb.getNoidung());
        contentValues.put("HinhAnh",bb.getHinhAnh());
        mDatabase.update("BienBao",contentValues,"MaBB=?", new String[]{"MaBB"});
    }
}
