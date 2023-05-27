package com.example.OnThiBangLaiXe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.Model.DeThi;
import com.example.OnThiBangLaiXe.Model.LoaiBang;
import com.example.OnThiBangLaiXe.Model.LoaiBienBao;
import com.example.OnThiBangLaiXe.Model.LoaiCauHoi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private SQLiteDatabase mDatabase;

    Context context;
    private static final String DB_NAME = "db.db";
    private static final int DB_VERSION = 1;
    public DBHandler(Context context) {
        super(context, DB_NAME, null, 2);
        this.context=context;
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
    //Kiêm,r tra vervsion
    public boolean isLastestVersion(int version)
    {

        mDatabase=this.getWritableDatabase();
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
    //Upodate version
    public void UpdateVersion(int version)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();

        contentValues.put("GiaTri",version+"");
        mDatabase.update("ThongTin",contentValues,"TenThongTin=?",new String[]{"PB"});
        mDatabase.close();
    }
    //get list de thi
    public List<DeThi> docDeThi()
    {
        mDatabase=this.getWritableDatabase();
        List<DeThi> dsDeThi = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from DeThi where MaLoaiBang='"+DanhSach.getLoaiBang()+"' OR MaLoaiBang IS NULL", null);
        if (cursor.moveToFirst()) {
            do {
                dsDeThi.add(new DeThi(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return dsDeThi;
    }
    public List<LoaiCauHoi> docLoaiCauHoi()
    {
        mDatabase=this.getWritableDatabase();
        List<LoaiCauHoi> dsCauTraLoi = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from LoaiCauHoi", null);
        if (cursor.moveToFirst()) {
            do {
                dsCauTraLoi.add(new LoaiCauHoi(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return dsCauTraLoi;
    }
    public List<CauTraLoi> docCauTraLoi()
    {
        mDatabase=this.getWritableDatabase();
        List<CauTraLoi> dsCauTraLoi = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from CauTraLoi", null);
        if (cursor.moveToFirst()) {
            do {
                dsCauTraLoi.add(new CauTraLoi(cursor.getInt(0), cursor.getInt(1)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return dsCauTraLoi;
    }
    //Get danh sách câu hay sai
    public List<CauHoi> docCauHaySai()
    {
        mDatabase=this.getWritableDatabase();
        List<CauHoi> dsCauHoi = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from CauHoi Where HaySai=1 and MaLoaiBang='"+DanhSach.getLoaiBang()+"'", null);
        if (cursor.moveToFirst()) {
            do {
                dsCauHoi.add(getCauHoiByID(cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return dsCauHoi;
    }
    //Get danh sách câu hỏi sai
    public List<CauHoi> docCauHoiSai()
    {
        mDatabase=this.getWritableDatabase();
        List<CauHoi> dsCauHoiSai = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from CauHoi Where DaTraLoiDung=2 and MaLoaiBang='"+DanhSach.getLoaiBang()+"'", null);
        if (cursor.moveToFirst()) {
            do {
                dsCauHoiSai.add(getCauHoiByID(cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return dsCauHoiSai;
    }
    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    public List<CauHoi> docCauHoiLuu()
    {
        mDatabase=this.getWritableDatabase();
        List<CauHoi> dsCauHoiSai = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("Select * from CauHoi where luu = 1 and MaLoaiBang='"+DanhSach.getLoaiBang()+"'", null);
        if (cursor.moveToFirst()) {
            do {
                dsCauHoiSai.add(getCauHoiByID(cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return dsCauHoiSai;
    }
    //get list loại biển báo
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
    //Get list CauTraLoi By Id ma de thi
    public List<CauTraLoi> getListCauTraLoiByMaDeThi(int id)
    //Get list CauHoi by id loai cau hoi
    {
        mDatabase=this.getWritableDatabase();
        List<CauTraLoi> dsCTL = new ArrayList<>();
        Cursor cursor=mDatabase.rawQuery("select * from CauTraLoi where MaDeThi=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()&&cursor.getCount()>0) {
            do {
                dsCTL.add(new CauTraLoi(cursor.getInt(0),cursor.getInt(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return dsCTL;
    }
    public List<CauHoi> getListCauHoiByMaCH(int id)
    {
        mDatabase=this.getWritableDatabase();
        List<CauHoi> ds = new ArrayList<>();
        Cursor cursor=mDatabase.rawQuery("select * from CauHoi where MaLoaiCH=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()&&cursor.getCount()>0) {
            do {
                ds.add(getCauHoiByID(cursor.getInt(0)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return ds;
    }
    public CauHoi getCauHoiByID(int id)
    {
        mDatabase=this.getWritableDatabase();
        Cursor cursor=mDatabase.rawQuery("select * from CauHoi where MaCH=?",new String[] {String.valueOf(id)});
        cursor.moveToFirst();
        if(cursor.getCount()>0)
            return new CauHoi(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getInt(11),cursor.getInt(12),cursor.getInt(13));
        cursor.close();
        return null;
    }
    //Get list Biển báo
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

    //Get list câu hỏi
    public List<CauHoi> docCauHoi()
    {
        mDatabase=this.getWritableDatabase();
        List<CauHoi> dsCauHoi = new ArrayList<>();
        Cursor cursor=mDatabase.rawQuery("select * from CauHoi where MaLoaiBang='"+DanhSach.getLoaiBang()+"'",null);
        while (cursor.moveToNext())
        {
            Boolean s=true;
            if(cursor.getInt(13)==0)
                s=false;

            dsCauHoi.add(new CauHoi(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getInt(11),cursor.getInt(12),cursor.getInt(13)));
        }
        cursor.close();

        return dsCauHoi;
    }
    //Insert loai bang
    public void insertLoaiBang(LoaiBang lb)
    {
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaLoaiBang",lb.getMaLoaiBang());
        contentValues.put("TenLoaiBang",lb.getTenLoaiBang());
        insert("LoaiBang",contentValues);

    }
    //Insert biển báo
    public void insertBB(BienBao bb)
    {
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaBB",bb.getMaBB());
        contentValues.put("MaLoaiBB",bb.getMaLoaiBB());
        contentValues.put("TieuDe",bb.getTieuDe());
        contentValues.put("NoiDung",bb.getNoidung());
        contentValues.put("HinhAnh",bb.getHinhAnh());
        insert("BienBao",contentValues);

    }
    //Insert Đề thi
    public void insertDeThi(DeThi dt)
    {
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaDeThi",dt.getMaDeThi());
        contentValues.put("TenDeThi","Đề "+dt.getMaDeThi());
        contentValues.put("MaLoaiBang",dt.getMaLoaiBang());
        insert("DeThi",contentValues);
    }
    //Insert câu hỏi
    public void insertCauHoi(CauHoi ch)
    {
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaCH",ch.getMaCH());
        contentValues.put("MaLoaiCH",ch.getMaLoaiCH());
        contentValues.put("MaLoaiBang",ch.getMaLoaiBang());
        contentValues.put("NoiDung",ch.getNoiDung());
        contentValues.put("HinhAnh",ch.getHinhAnh());
        contentValues.put("DapAnA",ch.getDapAnA());
        contentValues.put("DapAnB",ch.getDapAnB());
        contentValues.put("DapAnC",ch.getDapAnC());
        contentValues.put("DapAnD",ch.getDapAnD());
        contentValues.put("DapAnDung",ch.getDapAnDung());
        contentValues.put("GiaiThich",ch.getGiaiThich());
        contentValues.put("HaySai",ch.getHaySai());
        insert("CauHoi",contentValues);
    }
    public void insertLoaiCauHoi(LoaiCauHoi lch)
    {

        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaLoaiCH",lch.getMaLoaiCH());
        contentValues.put("TenLoaiCauHoi",lch.getTenLoaiCauHoi());
        insert("LoaiCauHoi",contentValues);
    }
    //insert cau tra loi
    public void insertCauTraLoi(CauTraLoi ctl)
    {
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaDeThi",ctl.getMaDeThi());
        contentValues.put("MaCauHoi",ctl.getMaCH());
        insert("CauTraLoi",contentValues);
    }
    private void insert(String table,ContentValues c)
    {
        try {
            mDatabase=this.getWritableDatabase();
            mDatabase.insert(table,null,c);
            mDatabase.close();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    //Update đã trả lời câu hỏi
    public void updateDaTraLoi(int MaCH, int DaTraLoiDung)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("DaTraLoiDung",DaTraLoiDung);
        Log.e("123",DaTraLoiDung+"");
        mDatabase.update("CauHoi",contentValues,"MaCH=?", new String[]{String.valueOf(MaCH)});

    }
    //Update câu hỏi luuw lại của user
    public void updateLuuLaiCauHoi(int MaCH,int Luu)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("Luu",Luu);
        mDatabase.update("CauHoi",contentValues,"MaCH=?", new String[]{String.valueOf(MaCH)});

    }
    //Tim kiếm loại băng
    public Boolean findLoaiBang(int MaLB)
    {
        mDatabase=this.getWritableDatabase();
        Cursor cursor3= mDatabase.rawQuery("select MaLoaiBang FROM LoaiBang WHERE MaLoaiBang = '"+MaLB+"'",null);
        cursor3.moveToFirst();
        if(cursor3!=null&&cursor3.getCount()>0)
        {
            if(MaLB==cursor3.getInt(0))
            {

                return true;
            }
        }
        cursor3.close();
        return false;
    }
    //Tìm kiểm Biển báo theo ID
    public Boolean findBBByID(String MaBB)
    {
        mDatabase=this.getWritableDatabase();
        Cursor cursor3= mDatabase.rawQuery("select MaBB FROM BienBao WHERE MaBB = '"+MaBB+"'",null);
        cursor3.moveToFirst();
        if(cursor3!=null&&cursor3.getCount()>0)
        {
            if(MaBB==cursor3.getString(0))
            {

                return true;
            }
        }
        cursor3.close();
        return false;
    }
    //Tìm kiếm loại câu hỏi by id
    public Boolean findLCHByID(int MLCH)
    {
        mDatabase=this.getWritableDatabase();
        Cursor cursor3= mDatabase.rawQuery("select MaLoaiCH FROM LoaiCauHoi WHERE MaLoaiCH = "+MLCH,null);
        cursor3.moveToFirst();
        if(cursor3!=null&&cursor3.getCount()>0)
        {
            if(MLCH==cursor3.getInt(0))
            {

                return true;
            }
        }
        cursor3.close();
        return false;
    }
    //Tìm kiếm câu hỏi theo ID
    public Boolean findCHByID(int ID)
    {
        mDatabase=this.getWritableDatabase();
//        Cursor cursor3= mDatabase.rawQuery("select MaCH FROM CauHoi where MaCH="+1 ,null);
        String selection = "MaCH="+ID;
        String[] columns ={"MaCH"};
        Cursor cursor3=mDatabase.query("CauHoi",columns,selection,null,null,null,null);
        cursor3.moveToFirst();
        if(cursor3!=null&&cursor3.getCount()>0)
        {


            if(ID==cursor3.getInt(0))
            {

                return true;
            }
        }
        cursor3.close();
        return false;
    }
    //Tìm kiếm Ccau tra loi theo ID
    public Boolean findCauTraLoiByID(int idDeThi, int idCauHoi)
    {
        mDatabase=this.getWritableDatabase();
        String selection = "MaDeThi="+idDeThi+" AND "+"MaCauHoi="+idCauHoi;
        String[] columns ={"MaDeThi","MaCauHoi"};
        Cursor cursor3=mDatabase.query("CauTraLoi",columns,selection,null,null,null,null);
        cursor3.moveToFirst();
        if(cursor3!=null&&cursor3.getCount()>0)
        {
            if(idDeThi==cursor3.getInt(0) && idCauHoi==cursor3.getInt(1))
            {
                return true;
            }
        }
        cursor3.close();
        return false;
    }
    public Boolean finDDeThiByID(int ID)
    {
        mDatabase=this.getWritableDatabase();
        String selection = "MaDeThi="+ID;
        String[] columns ={"MaDeThi"};
        Cursor cursor3=mDatabase.query("DeThi",columns,selection,null,null,null,null);
        cursor3.moveToFirst();
        if(cursor3!=null&&cursor3.getCount()>0)
        {
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
    //Update loai bang
    public void updateLoaiBang(LoaiBang lch)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("TenLoaiBang",lch.getTenLoaiBang());
        mDatabase.update("LoaiBang",contentValues,"MaLoaiBang=?", new String[]{String.valueOf(lch.getMaLoaiBang())});
        mDatabase.close();
    }
    //Update biển báo
    public void updateCauTraLoi(List<CauTraLoi> ctl)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        for (CauTraLoi tl:ctl)
        {
            contentValues.put("DapAnChon",tl.getDapAnChon());
            mDatabase.update("CauTraLoi",contentValues,"MaDeThi=? AND MaCauHoi=?", new String[]{String.valueOf(tl.getMaDeThi()),String.valueOf(tl.getMaCH())});
        }
        mDatabase.close();
    }
    //Update loai câu hỏi
    public void updateLoaiCauHoi(LoaiCauHoi lch)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaLoaiCH",lch.getMaLoaiCH());
        contentValues.put("TenLoaiCauHoi",lch.getTenLoaiCauHoi());
        mDatabase.update("LoaiCauHoi",contentValues,"MaLoaiCH=?", new String[]{String.valueOf(lch.getMaLoaiCH())});
        mDatabase.close();
    }
    //Update beeirn báo
    public void updateBB(BienBao bb)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaBB",bb.getMaBB());
        contentValues.put("MaLoaiBB",bb.getMaLoaiBB());
        contentValues.put("TieuDe",bb.getTieuDe());
        contentValues.put("NoiDung",bb.getNoidung());
        contentValues.put("HinhAnh",bb.getHinhAnh());
        mDatabase.update("BienBao",contentValues,"MaBB=?", new String[]{String.valueOf(bb.getMaBB())});
        mDatabase.close();
    }
    //Update đề thi
    public void updateDeThi(DeThi dt)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("TenDeThi","Đề "+dt.getMaDeThi());
        contentValues.put("MaLoaiBang",dt.getMaLoaiBang());
        mDatabase.update("DeThi",contentValues,"MaDeThi=?",new String[]{String.valueOf(dt.getMaDeThi())});
        mDatabase.close();
    }
    //Update câu hỏi
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
        contentValues.put("DapAnD",ch.getDapAnD());
        contentValues.put("DapAnDung",ch.getDapAnDung());
        contentValues.put("GiaiThich",ch.getGiaiThich());
        contentValues.put("HaySai",ch.getHaySai());
        mDatabase.update("CauHoi",contentValues,"MaCH=?", new String[]{String.valueOf(ch.getMaCH())});
        mDatabase.close();
    }
    //Update câu trả lời
    public void updateCauTraLoi(CauTraLoi ctl)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("MaCauHoi",ctl.getMaCH());
        contentValues.put("MaDeThi",ctl.getMaDeThi());
        mDatabase.update("CauTraLoi",contentValues,"MaCauHoi=? AND MaDeThi=?", new String[]{String.valueOf(ctl.getMaCH()),String.valueOf(ctl.getMaDeThi())});
        mDatabase.close();
    }
    //Update dap an da chon trong cau tra loi
    public void updateDapAnChon(CauTraLoi ctl)
    {
        mDatabase=this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("DapAnChon",ctl.getDapAnChon());
        mDatabase.update("CauTraLoi",contentValues,"MaCauHoi=? AND MaDeThi=?", new String[]{String.valueOf(ctl.getMaCH()),String.valueOf(ctl.getMaDeThi())});
        mDatabase.close();
    }
    //Tao de ngau nhien
    public void RandomQuizz()
    {
        //Lấy row cuối cùng
        List<CauHoi> dsCauHoiRanDom=new ArrayList<>();
        mDatabase=this.getWritableDatabase();
        DeThi DeThi = new DeThi();
        DeThi.setTenDeThi("Random");
        DeThi.setMaDeThi(0);
        DanhSach.getDsDeThi().set(0, DeThi);
        mDatabase=this.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM CauHoi WHERE MaLoaiCH=1 and MaLoaiBang='"+DanhSach.getLoaiBang()+"' ORDER BY RANDOM() LIMIT 2;", null);
        while (cursor.moveToNext())
        {
            dsCauHoiRanDom.add(getCauHoiByID(cursor.getInt(0)));
        }
        cursor = mDatabase.rawQuery("SELECT * FROM CauHoi WHERE MaLoaiCH!=1 and MaLoaiBang='"+DanhSach.getLoaiBang()+"' ORDER BY RANDOM() LIMIT 23;", null);
        while (cursor.moveToNext())
        {
            dsCauHoiRanDom.add(getCauHoiByID(cursor.getInt(0)));
        }
        cursor.close();
        List<CauTraLoi> dsCTL = new ArrayList<>();
        for (CauHoi ch:dsCauHoiRanDom)
        {
            dsCTL.add(new CauTraLoi(DeThi.getMaDeThi(),ch.getMaCH()));
        }

        Collections.shuffle(dsCTL);
        DanhSach.getDsCauTraLoi().addAll(dsCTL);
        mDatabase.close();
    }
}