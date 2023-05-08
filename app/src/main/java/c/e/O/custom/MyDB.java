package c.e.O.custom;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.OnThiBangLaiXe.DBHandler;
import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DeThi;
import com.example.OnThiBangLaiXe.Model.LoaiCauHoi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class MyDB {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference=storage.getReference();
    DBHandler dbHandler;
    Context context;

    public MyDB(Context context) {
        dbHandler=new DBHandler(context);
        this.context=context;
    }

    public void capNhatDatabase()
    {
//        DatabaseReference csdlLoaiCauHoi = database.getReference("LoaiCauHoi");
//        //Đọc loại câu hỏi
//        csdlLoaiCauHoi.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++)
//                {
//                    LoaiCauHoi tlbb = dataSnapshot.child(String.valueOf(i)).getValue(LoaiCauHoi.class);
//
//                    if (tlbb != null)
//                    {
//                        if(dbHandler.findBBByID(tlbb.getMaBB()))
//                        {
//                            dbHandler.updateBB(tlbb);
//                        }
//                        else
//                        {
//                            dbHandler.insertBB(tlbb);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        DatabaseReference csdlBienBao = database.getReference("BienBao");
        //Đọc loại câu hỏi
        csdlBienBao.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++)
                {
                    BienBao tlbb = dataSnapshot.child(String.valueOf(i)).getValue(BienBao.class);

                    if (tlbb != null)
                    {
                        if(dbHandler.findBBByID(tlbb.getMaBB()))
                        {
                            dbHandler.updateBB(tlbb);
                        }
                        else
                        {
                            dbHandler.insertBB(tlbb);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference csdlCauHoi = database.getReference("CauHoi");
        csdlCauHoi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (int i = 0; i < snapshot.getChildrenCount(); i++)
                {
                    CauHoi tlbb =snapshot.child(String.valueOf(i)).getValue(CauHoi.class);
                    if(tlbb != null)
                    {
                        if(dbHandler.findCHByID(tlbb.getMaCH()))
                        {
                            dbHandler.updateCauHoi(tlbb);

                        }
                        else
                        {
                            dbHandler.insertCauHoi(tlbb);

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference csdlDeThi= database.getReference("DeThi");
        csdlDeThi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (int i = 0; i < snapshot.getChildrenCount(); i++)
                {

                    DeThi tlbb =snapshot.child(String.valueOf(i)).getValue(DeThi.class);
                    Log.e("DE de thi",tlbb.getMaDeThi()+"");
                    if(tlbb != null)
                    {

                        if(dbHandler.finDDeThiByID(tlbb.getMaDeThi()))
                        {
                            dbHandler.updateDeThi(tlbb);

                        }
                        else
                        {
                            dbHandler.insertDeThi(tlbb);

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference csdlCauTraLoi= database.getReference("CauTraLoi");
        csdlCauTraLoi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (int i = 0; i < snapshot.getChildrenCount(); i++)
                {

                    CauTraLoi tlbb =snapshot.child(String.valueOf(i)).getValue(CauTraLoi.class);

                    if(tlbb != null)
                    {

                        if(dbHandler.findCauTraLoiByID(tlbb.getMaDeThi(),tlbb.getMaCH()))
                        {
                            dbHandler.updateCauTraLoi(tlbb);

                        }
                        else
                        {
                            dbHandler.insertCauTraLoi(tlbb);

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void downloadWithBytes(String type){
        StorageReference imageRefl = storageReference.child(type);
        imageRefl.listAll().addOnSuccessListener(listResult -> {
            List<StorageReference> srtList=listResult.getItems();
            for (StorageReference sr : srtList)
            {
                long SIZE=500*500;
                sr.getBytes(SIZE).addOnSuccessListener(bytes -> {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    storeImage(bitmap, sr.getName());
                    Log.e("Img",sr.getName());
                });
            }
        });
    }
    private void storeImage(Bitmap bitmap, String name) {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("images", Context.MODE_PRIVATE);
        File file = new File(directory, name);
        if (!file.exists()) {
            Log.d("path", file.toString());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }
}
