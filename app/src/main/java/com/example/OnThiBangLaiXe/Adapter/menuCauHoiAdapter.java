package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.R;
import com.example.OnThiBangLaiXe.ThiThuActivity;

import java.util.List;

public class menuCauHoiAdapter extends RecyclerView.Adapter<menuCauHoiAdapter.ViewHolder>
{
    private List<CauTraLoi> dsCauTraLoi;
    private Context context;

    public menuCauHoiAdapter(List<CauTraLoi> dsCauTraLoi, Context context) {
        this.dsCauTraLoi = dsCauTraLoi;
        this.context = context;
    }

    @NonNull
    @Override
    public menuCauHoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new menuCauHoiAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cau_tra_loi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull menuCauHoiAdapter.ViewHolder holder, int position) {
        CauHoi ch = null;

        CauTraLoi ctl = dsCauTraLoi.get(position);

        for (CauHoi cauHoi : DanhSach.getDsCauHoi())
        {
            if (cauHoi.getMaCH() == ctl.getMaCH())
            {
                ch = cauHoi;
            }
        }

        if (ch.getMaLoaiCH() == 1) // mã loại câu hỏi điểm liệt
        {
            holder.btnCauHoi.setBackgroundColor(Color.RED);
            Log.d("Ma CH", ch.getMaCH()+"");
        }

        if (ctl.getDapAnChon() != null) // mã loại câu hỏi điểm liệt
        {
            holder.btnCauHoi.setBackgroundColor(Color.BLUE);
        }

        int text = position + 1;
        holder.btnCauHoi.setOnClickListener(v -> ThiThuActivity.vp.setCurrentItem(position, false));
        Log.d("Test", String.valueOf(text));
        holder.btnCauHoi.setText(String.valueOf(text));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dsCauTraLoi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private Button btnCauHoi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCauHoi = itemView.findViewById(R.id.btnCauHoi);
        }
    }
}
