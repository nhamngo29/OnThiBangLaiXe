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

import com.example.OnThiBangLaiXe.CauHoiActivity;
import com.example.OnThiBangLaiXe.DBHandler;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.R;

import java.util.List;

public class CauTraLoiAdapter extends RecyclerView.Adapter<CauTraLoiAdapter.ViewHolder>
{
    private List<CauTraLoi> dsCauTraLoi;
    private Context context;
    private DBHandler db;

    public CauTraLoiAdapter(List<CauTraLoi> dsCauTraLoi, Context context) {
        this.context = context;
        this.dsCauTraLoi = dsCauTraLoi;
        db=new DBHandler(context);
    }

    @NonNull
    @Override
    public CauTraLoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CauTraLoiAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cau_hoi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CauTraLoiAdapter.ViewHolder holder, int position) {
        CauHoi ch = null;

        CauTraLoi ctl = dsCauTraLoi.get(position);

        for (CauHoi cauHoi : DanhSach.getDsCauHoi())
        {
            if (cauHoi.getMaCH() == ctl.getMaCH())
            {
                ch = cauHoi;
            }
        }

        holder.txtSoCauHoi.setText("Câu " + (position + 1) + "/" + dsCauTraLoi.size() + " câu |");

        holder.txtNoiDungCauHoi.setText(ch.getNoiDung());

        if (ch.getDapAnA() != null)
        {
            holder.rbA.setText(ch.getDapAnA());
            holder.rbA.setVisibility(View.VISIBLE);

            holder.rbA.setOnCheckedChangeListener((i, v) -> ctl.setDapAnChon("A"));
        }

        if (ch.getDapAnB() != null)
        {
            holder.rbB.setText(ch.getDapAnB());
            holder.rbB.setVisibility(View.VISIBLE);

            holder.rbB.setOnCheckedChangeListener((i, v) -> ctl.setDapAnChon("B"));
        }

        if (ch.getDapAnC() != null)
        {
            holder.rbC.setText(ch.getDapAnC());
            holder.rbC.setVisibility(View.VISIBLE);

            holder.rbC.setOnCheckedChangeListener((i, v) -> ctl.setDapAnChon("C"));
        }

        if (ch.getDapAnD() != null)
        {
            holder.rbD.setText(ch.getDapAnD());
            holder.rbD.setVisibility(View.VISIBLE);

            holder.rbD.setOnCheckedChangeListener((i, v) -> ctl.setDapAnChon("D"));
        }
    }

    private void setDapAn(CauTraLoiAdapter.ViewHolder holder, int position, Boolean value)
    {
        CauHoi ch = null;

        for (CauHoi cauHoi : DanhSach.getDsCauHoi())
        {
            if (cauHoi.getMaCH() == dsCauTraLoi.get(position).getMaCH())
            {

            }
        }

        holder.txtGiaiThichCauHoi.setText(ch.getGiaiThich());
        int s=2;
        if(value)
            s=1;
        ch.setDaTraLoiDung(s);

        holder.txtDungSai.setText(" Đã học");

        if (Boolean.TRUE.equals(ch.getGiaiThich()))
        {
            holder.ivDungSai.setImageResource(R.drawable.ico_true);
        }
        else
        {
            holder.ivDungSai.setImageResource(R.drawable.ico_false);
        }
    }

    @Override
    public int getItemCount() {
        return dsCauTraLoi.size();
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
