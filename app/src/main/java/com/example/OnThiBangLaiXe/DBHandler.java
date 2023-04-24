package com.example.OnThiBangLaiXe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.Model.CauHoi;
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
    void UpdateVersion(int version)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();

        contentValues.put("GiaTri",version+"");
        Log.e("VEr up",version+"");
        mDatabase.update("ThongTin",contentValues,"TenThongTin=?",new String[]{"PB"});
        mDatabase.close();
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
        mDatabase=this.getWritableDatabase();
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
    public List<BienBao> docBienBao()
    {
        mDatabase=this.getWritableDatabase();
        List<BienBao> dsLoaiBienBao = new ArrayList<>();
        Cursor cursor=mDatabase.rawQuery("select * from BienBao",null);
        while (cursor.moveToNext())
        {
            dsLoaiBienBao.add(new BienBao(cursor.getString(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
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
        Log.e("Insert hình ảnh trong sqlite",bb.getHinhAnh());
        mDatabase.insert("BienBao",null,contentValues);
        mDatabase.close();
    }
    public void insertCauHoi(CauHoi ch)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaCH",ch.getMaCH());
        contentValues.put("MaLoaiCH",ch.getMaLoaiCH());
        contentValues.put("MaLoaiBang",ch.getMaLoaiBang());
        contentValues.put("NoiDung",ch.getNoiDung());
        contentValues.put("HinhAnh",ch.getHinhAnh());
        contentValues.put("DapAnA",ch.getDapAnA());
        contentValues.put("DapAnB",ch.getDapAnB());
        contentValues.put("DapAnC",ch.getDapAnC());
        contentValues.put("DapAnC",ch.getDapAnD());
        contentValues.put("DapAnDung",ch.getDapAnDung());
        contentValues.put("GiaiThich",ch.getGiaiThich());
        contentValues.put("HaySai",ch.getHaySai());
        mDatabase.insert("CauHoi",null,contentValues);
        mDatabase.close();
    }
    public void getListBB()
    {

    }
    Boolean findBBByID(String MaBB)
    {
        mDatabase=this.getWritableDatabase();
        Cursor cursor3= mDatabase.rawQuery("select MaBB FROM BienBao WHERE TRIM(MaBB) = '"+MaBB+"'",null);
        cursor3.moveToFirst();
        if(cursor3!=null)
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
    Boolean findCHByID(int ID)
    {
        mDatabase=this.getWritableDatabase();
//        Cursor cursor3= mDatabase.rawQuery("select MaCH FROM CauHoi where MaCH="+1 ,null);
        String selection = "MaCH="+ID;
        String[] columns ={"MaCH"};
        Cursor cursor3=mDatabase.query("CauHoi",columns,selection,null,null,null,null);
        cursor3.moveToFirst();
        if(cursor3!=null&&cursor3.getCount()>0)
        {

            Log.e("MaCH","");
            if(ID==cursor3.getInt(0))
            {

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
        Log.e("Update hình ảnh trong sqlite",bb.getHinhAnh());
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaBB",bb.getMaBB());
        contentValues.put("MaLoaiBB",bb.getMaLoaiBB());
        contentValues.put("TieuDe",bb.getTieuDe());
        contentValues.put("NoiDung",bb.getNoidung());
        contentValues.put("HinhAnh",bb.getHinhAnh());
        mDatabase.update("BienBao",contentValues,"MaBB=?", new String[]{"MaBB"});
    }
    public void updateCauHoi(CauHoi ch)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaCH",ch.getMaCH());
        contentValues.put("MaLoaiCH",ch.getMaLoaiCH());
        contentValues.put("MaLoaiBang",ch.getMaLoaiBang());
        contentValues.put("NoiDung",ch.getNoiDung());
        contentValues.put("HinhAnh",ch.getHinhAnh());
        contentValues.put("DapAnA",ch.getDapAnA());
        contentValues.put("DapAnB",ch.getDapAnB());
        contentValues.put("DapAnC",ch.getDapAnC());
        contentValues.put("DapAnC",ch.getDapAnD());
        contentValues.put("DapAnDung",ch.getDapAnDung());
        contentValues.put("GiaiThich",ch.getGiaiThich());
        contentValues.put("HaySai",ch.getHaySai());
        mDatabase.update("CauHoi",contentValues,"MaCH=?", new String[]{"MaCH"});
    }
}
