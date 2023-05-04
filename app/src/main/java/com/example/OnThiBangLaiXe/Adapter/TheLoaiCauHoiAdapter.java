package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.CauHoiActivity;
import com.example.OnThiBangLaiXe.DBHandler;
import com.example.OnThiBangLaiXe.Interface.RecyclerViewInterface;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.Model.LoaiCauHoi;
import com.example.OnThiBangLaiXe.R;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiCauHoiAdapter extends RecyclerView.Adapter<TheLoaiCauHoiAdapter.ViewHolder>
{
    private final RecyclerViewInterface recyclerViewInterface;
    private List<LoaiCauHoi> dsLoaiCauHoi;
    private Context context;
    private DBHandler db;

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public TheLoaiCauHoiAdapter(List<LoaiCauHoi> dsLoaiCauHoi, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.dsLoaiCauHoi = dsLoaiCauHoi;
        this.context = context;
        this.recyclerViewInterface=recyclerViewInterface;
        db=new DBHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_theloaicauhoi, parent, false),recyclerViewInterface);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiCauHoi tlch = dsLoaiCauHoi.get(position);
        List<CauHoi> cauHoiList=new ArrayList<>();
        int soCauDaTraLoi=0,soCauDaTraLoiDung=0,soCauDaTraLoiSai=0;
        for(CauHoi dsCauHoi: db.docCauHoi())
        {

            if(dsCauHoi.getMaLoaiCH()== tlch.getMaLoaiCH())
            {

                cauHoiList.add(dsCauHoi);
                if(dsCauHoi.getDaTraLoiDung()!=0)
                {
                    soCauDaTraLoi+=1;
                    if(dsCauHoi.getDaTraLoiDung()==1)
                    {
                        soCauDaTraLoiDung+=1;
                    }
                    else
                    {
                        soCauDaTraLoiSai+=1;
                    }
                }

            }
        }
        try {
            holder.ivTheLoaiCauHoi.setImageResource(context.getResources().getIdentifier(
                    tlch.getHinh(), "drawable", context.getPackageName()));
        } catch (Exception e)
        {
            holder.ivTheLoaiCauHoi.setImageResource(R.drawable.ico_exam);
        }

        holder.ten.setText(tlch.getTenLoaiCauHoi());
        holder.soCauHoi.setText(soCauDaTraLoi + "/" + cauHoiList.size() + " câu");
        holder.ketQua.setText(soCauDaTraLoiDung + " câu đúng, "
                + soCauDaTraLoiSai + " câu sai");
        holder.pbKetQua.setMax(cauHoiList.size());
        holder.pbKetQua.setProgress(soCauDaTraLoi);

        holder.itemView.setOnClickListener(v -> {
            Log.e("Ma laoi ch",tlch.getMaLoaiCH()+"");
            Intent intent = new Intent(context, CauHoiActivity.class);
            intent.putExtra("MaLoaiCH", tlch.getMaLoaiCH());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return dsLoaiCauHoi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView ivTheLoaiCauHoi;
        private TextView ten;
        private TextView soCauHoi;
        private TextView ketQua;
        private ProgressBar pbKetQua;
        public ViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            ivTheLoaiCauHoi = itemView.findViewById(R.id.ivTheLoaiCauHoi);
            ten = itemView.findViewById(R.id.txtTheLoaiCauHoi);
            soCauHoi = itemView.findViewById(R.id.txtSoCau);
            ketQua = itemView.findViewById(R.id.txtKetQua);
            pbKetQua = itemView.findViewById(R.id.pbTheLoaiCauHoi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null)
                    {
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION)
                        {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
