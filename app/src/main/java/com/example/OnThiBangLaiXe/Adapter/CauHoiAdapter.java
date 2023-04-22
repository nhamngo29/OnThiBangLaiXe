package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.R;

import java.util.List;

public class CauHoiAdapter extends RecyclerView.Adapter<CauHoiAdapter.ViewHolder>
{
    private List<CauHoi> dsCauHoi;
    private Context context;

    public CauHoiAdapter(List<CauHoi> dsCauHoi, Context context) {
        this.context = context;
        this.dsCauHoi = dsCauHoi;
    }

    @NonNull
    @Override
    public CauHoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CauHoiAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cau_hoi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CauHoiAdapter.ViewHolder holder, int position) {
        CauHoi ch = dsCauHoi.get(position);

        holder.txtSoCauHoi.setText("Câu " + (position + 1) + "/" + dsCauHoi.size() + " câu |");

        if (ch.getDungSai() == null)
        {
            holder.txtDungSai.setText(" Chưa học");
        }
        else
        {
            holder.txtDungSai.setText(" Đã học");

            if (Boolean.TRUE.equals(ch.getDungSai()))
            {
                holder.ivDungSai.setImageResource(R.drawable.baseline_check_circle_12);
            }
            else
            {
                holder.ivDungSai.setImageResource(R.drawable.baseline_cancel_12);
            }
        }

        holder.txtNoiDungCauHoi.setText(ch.getNoiDung());

//        holder.ivCauHoi.setImageResource(context.getResources().getIdentifier(
//                "Tên file hình", "drawable", context.getPackageName()));

        if (ch.getDapAnA() != null)
        {
            holder.rbA.setText(ch.getDapAnA());
            holder.rbA.setVisibility(View.VISIBLE);

            holder.rbA.setOnCheckedChangeListener((i, v) -> setDapAn(holder, position, ch.getDapAnDung().equals("A")));
        }

        if (ch.getDapAnB() != null)
        {
            holder.rbB.setText(ch.getDapAnB());
            holder.rbB.setVisibility(View.VISIBLE);

            holder.rbB.setOnCheckedChangeListener((i, v) -> setDapAn(holder, position, ch.getDapAnDung().equals("B")));
        }

        if (ch.getDapAnC() != null)
        {
            holder.rbC.setText(ch.getDapAnC());
            holder.rbC.setVisibility(View.VISIBLE);

            holder.rbC.setOnCheckedChangeListener((i, v) -> setDapAn(holder, position, ch.getDapAnDung().equals("C")));
        }

        if (ch.getDapAnD() != null)
        {
            holder.rbD.setText(ch.getDapAnD());
            holder.rbD.setVisibility(View.VISIBLE);

            holder.rbD.setOnCheckedChangeListener((i, v) -> setDapAn(holder, position, ch.getDapAnDung().equals("D")));
        }
    }

    private void setDapAn(CauHoiAdapter.ViewHolder holder, int position, boolean value)
    {
        CauHoi ch = dsCauHoi.get(position);
        holder.txtGiaiThichCauHoi.setText(ch.getGiaiThich());
        holder.txtGiaiThichCauHoi.setVisibility(View.VISIBLE);
        ch.setDungSai(value);

        holder.txtDungSai.setText(" Đã học");

        if (Boolean.TRUE.equals(ch.getDungSai()))
        {
            holder.ivDungSai.setImageResource(R.drawable.baseline_check_circle_16);
        }
        else
        {
            holder.ivDungSai.setImageResource(R.drawable.baseline_cancel_16);
        }
    }

    @Override
    public int getItemCount() {
        return dsCauHoi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtNoiDungCauHoi, txtGiaiThichCauHoi, txtSoCauHoi, txtDungSai;
        private final RadioButton rbA, rbB, rbC, rbD;
        private final RecyclerView rvCauHoi;
        private final ImageView ivDungSai, ivCauHoi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvCauHoi = itemView.findViewById(R.id.rvGiaiThichBienBao);
            txtSoCauHoi = itemView.findViewById(R.id.txtSoCauHoi);
            txtDungSai = itemView.findViewById(R.id.txtDungSai);
            txtNoiDungCauHoi = itemView.findViewById(R.id.txtNoiDungCauHoi);
            txtGiaiThichCauHoi = itemView.findViewById(R.id.txtGiaiThichCauHoi);
            ivDungSai = itemView.findViewById(R.id.ivDungSai);
            ivCauHoi = itemView.findViewById(R.id.ivCauHoi);
            rbA = itemView.findViewById(R.id.rbA);
            rbB = itemView.findViewById(R.id.rbB);
            rbC = itemView.findViewById(R.id.rbC);
            rbD = itemView.findViewById(R.id.rbD);
        }
    }
}
