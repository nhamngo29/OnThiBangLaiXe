package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.ChiTietKetQuaActivity;
import com.example.OnThiBangLaiXe.DBHandler;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class CauHoiResultAdapter extends RecyclerView.Adapter<CauHoiResultAdapter.ViewHolder>  {
    private List<CauTraLoi> dsCTL;
    private Context context;
    DBHandler db;
    public CauHoiResultAdapter(List<CauTraLoi> dsCTL, Context context) {
        this.dsCTL = dsCTL;
        this.context = context;
        db=new DBHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cau_hoi_result,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CauTraLoi ctl=dsCTL.get(position);
        if(ctl==null)
            return;
        CauHoi a=db.getCauHoiByID(ctl.getMaCH());
        holder.cau.setText("Câu số "+(position+1));
        if(a.getMaLoaiCH()==1)
        {
            holder.imageCauHoiLiet.setVisibility(View.VISIBLE);
            try {
                holder.imageCauHoiLiet.setImageResource(R.drawable.ico_fire);
            }
            catch (Exception e)
            {
                holder.imageCauHoiLiet.setImageResource(R.drawable.ico_fire);
            }
        }

        if(ctl.getDapAnChon().equals("0"))
        {
            holder.image.setImageResource(R.drawable.ico_error_40);
        }
        else if(ctl.getDapAnChon()==a.getDapAnDung()||ctl.getDapAnChon().equals(a.getDapAnDung()))
            holder.image.setImageResource(R.drawable.baseline_check_circle_24);
        else if(ctl.getDapAnChon()!=a.getDapAnDung()||!ctl.getDapAnChon().equals(a.getDapAnDung()))
            holder.image.setImageResource(R.drawable.baseline_cancel_24);
        if(a.getNoiDung()!=null)
        {
            holder.noidung.setText(a.getNoiDung());
        }
        if(a.getHinhAnh()!=null&&!a.getHinhAnh().equals("null"))
        {
            holder.imageBB.setVisibility(View.VISIBLE);
            try {
                File f = new File(context.getDataDir() + "/app_images/", a.getHinhAnh());

                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                holder.imageBB.setImageBitmap(b);
            } catch (FileNotFoundException e) {
                holder.imageBB.setImageResource(R.drawable.p101);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietKetQuaActivity.class);
                intent.putExtra("MaDeThi", dsCTL.get(position).getMaDeThi());
                intent.putExtra("ViTri", position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if(dsCTL!=null)
            return dsCTL.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        final TextView cau,noidung;
        final ImageView image,imageBB,imageCauHoiLiet;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cau=itemView.findViewById(R.id.txtCau);
            image=itemView.findViewById(R.id.img);
            noidung=itemView.findViewById(R.id.txtContent);
            imageBB=itemView.findViewById(R.id.imgBB);
            imageCauHoiLiet=itemView.findViewById(R.id.img_cauhoidiemliet);

        }
    }
}
