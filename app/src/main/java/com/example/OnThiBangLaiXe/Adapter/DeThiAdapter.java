package com.example.OnThiBangLaiXe.Adapter;

import static android.view.View.VISIBLE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.DBHandler;
import com.example.OnThiBangLaiXe.KetQuaActivity;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.Model.DeThi;
import com.example.OnThiBangLaiXe.R;
import com.example.OnThiBangLaiXe.ThiThuActivity;

import java.util.ArrayList;
import java.util.List;

public class DeThiAdapter extends RecyclerView.Adapter<DeThiAdapter.ViewHolder>
{
    private List<DeThi> dsDeThi;
    private Context context;
    DBHandler db;
    public DeThiAdapter(List<DeThi> dsDeThi, Context context) {
        this.context = context;
        this.dsDeThi = dsDeThi;
        db=new DBHandler(context);
    }

    @NonNull
    @Override
    public DeThiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeThiAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_de_thi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeThiAdapter.ViewHolder holder, int position) {
        holder.txtTenDeThi.setText(dsDeThi.get(position).getTenDeThi());
        int CauTraLoiDung=0,CauTraLoiSai=0;
        List<CauTraLoi> dsCTL= db.getListCauTraLoiByMaDeThi(dsDeThi.get(position).getMaDeThi());
        for(CauTraLoi ctl:dsCTL)
        {
            CauHoi a=db.getCauHoiByID(ctl.getMaCH());
            if(ctl.getDapAnChon()!=null)
            {
                if(a.getDapAnDung().equals(ctl.getDapAnChon()))
                    CauTraLoiDung++;
                else if(!a.getDapAnDung().equals(ctl.getDapAnChon())||ctl.getDapAnChon().equals("0"))
                    CauTraLoiSai++;
            }

        }
        if(CauTraLoiSai>=5&&CauTraLoiDung<20)
        {
            holder.txtTenDeThi.setText("Rớt");
            holder.txtTenDeThi.setTextColor(Color.RED);
        }
        else if(CauTraLoiDung>=20&&CauTraLoiSai<5)
        {
            holder.txtTenDeThi.setText("ĐẬU");
            holder.txtTenDeThi.setTextColor(Color.GREEN);
        }
        if(CauTraLoiDung!=0||CauTraLoiSai!=0)
        {
            holder.ivSai.setVisibility(VISIBLE);
            holder.ivDung.setVisibility(VISIBLE);
            holder.txtSoCauDung.setVisibility(VISIBLE);
            holder.txtSoCauSai.setVisibility(VISIBLE);
            holder.txtSoCauDung.setText(CauTraLoiDung+"");
            holder.txtSoCauSai.setText(CauTraLoiSai+"");
        }
        int finalCauTraLoiDung = CauTraLoiDung;
        int finalCauTraLoiSai = CauTraLoiSai;
        holder.itemView.setOnClickListener(view -> {
            if(finalCauTraLoiDung !=0|| finalCauTraLoiSai !=0)
            {
                Intent intent = new Intent(context, KetQuaActivity.class);
                intent.putExtra("MaDeThi", dsDeThi.get(position).getMaDeThi());
                context.startActivity(intent);
            }
            else
            {
                if (position == 0)
                {
                    List<CauTraLoi> temp = new ArrayList<>();
                    for (CauTraLoi ctl : DanhSach.getDsCauTraLoi())
                    {
                        if (ctl.getMaDeThi() == 0)
                        {
                            temp.add(ctl);
                        }
                    }
                    DanhSach.getDsCauTraLoi().removeAll(temp);
                    db.RandomQuizz();
                }

                Intent intent = new Intent(context, ThiThuActivity.class);
                intent.putExtra("MaDeThi", dsDeThi.get(position).getMaDeThi());
                context.startActivity(intent);
            }


        });

    }

    @Override
    public int getItemCount() {
        return dsDeThi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtTenDeThi,txtSoCauDung,txtSoCauSai;
        private final ImageView ivDung,ivSai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenDeThi = itemView.findViewById(R.id.txt_DeThi);
            txtSoCauDung=itemView.findViewById(R.id.txtSoCauDung);
            txtSoCauSai=itemView.findViewById(R.id.txtSoCauSai);
            ivDung=itemView.findViewById(R.id.ivCauDung);
            ivSai=itemView.findViewById(R.id.ivSai);

        }
    }
}