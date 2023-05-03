package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class KetQuaAdapter extends RecyclerView.Adapter<KetQuaAdapter.ViewHolder>
{
    private List<CauHoi> dsCauHoi;
    private List<CauTraLoi> dsCauTraLoi;
    private List<String> dsDungSai;
    private Context context;

    public KetQuaAdapter(List<CauTraLoi> dsCauTraLoi, Context context,List<String> dsDungSai) {
        this.context = context;
        this.dsCauTraLoi = dsCauTraLoi;
        this.dsDungSai=dsDungSai;
        this.dsCauHoi = new ArrayList<>();

        for (CauTraLoi ctl : dsCauTraLoi)
        {
            for (CauHoi ch : DanhSach.getDsCauHoi())
            {
                if (ch.getMaCH() == ctl.getMaCH())
                {
                    dsCauHoi.add(ch);
                    break;
                }
            }
        }
    }

    @NonNull
    @Override
    public KetQuaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KetQuaAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cau_hoi_chi_tiet, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull KetQuaAdapter.ViewHolder holder, int position) {
        CauHoi ch = dsCauHoi.get(position);
        holder.txtSoCauHoi.setText("Câu " + (position + 1) + "/" + dsCauHoi.size() + " câu |");
        holder.txtNoiDungCauHoi.setText(ch.getNoiDung());
        holder.header.setBackgroundColor(Color.BLUE);

        if (ch.getHinhAnh()!=null&&!ch.getHinhAnh().equals("null"))
        {
            holder.ivCauHoi.setVisibility(View.VISIBLE);
            try {
                File f = new File(context.getDataDir() + "/app_images/", ch.getHinhAnh());
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                holder.ivCauHoi.setImageBitmap(b);
            } catch (FileNotFoundException e) {
                holder.ivCauHoi.setImageResource(R.drawable.p101);
            }
        }

        if (ch.getDapAnA() != null && !ch.getDapAnA().equals("null"))
        {
            holder.rbA.setText(ch.getDapAnA());
            holder.rbA.setVisibility(View.VISIBLE);
            holder.rbA.setEnabled(false);

            String dapAnChon = dsCauTraLoi.get(position).getDapAnChon();

            if (dapAnChon != null && dapAnChon.equals("A"))
            {
                holder.rbA.setChecked(true);
            }
        }

        if (ch.getDapAnB() != null && !ch.getDapAnB().equals("null"))
        {
            holder.rbB.setText(ch.getDapAnB());
            holder.rbB.setVisibility(View.VISIBLE);
            holder.rbB.setEnabled(false);

            String dapAnChon = dsCauTraLoi.get(position).getDapAnChon();

            if (dapAnChon != null && dapAnChon.equals("B"))
            {
                holder.rbB.setChecked(true);
            }
        }

        if (ch.getDapAnC() != null && !ch.getDapAnC().equals("null"))
        {
            holder.rbC.setText(ch.getDapAnC());
            holder.rbC.setVisibility(View.VISIBLE);
            holder.rbC.setEnabled(false);

            String dapAnChon = dsCauTraLoi.get(position).getDapAnChon();

            if (dapAnChon != null && dapAnChon.equals("C"))
            {
                holder.rbC.setChecked(true);
            }
        }

        if (ch.getDapAnD() != null && !ch.getDapAnD().equals("null"))
        {
            holder.rbD.setText(ch.getDapAnD());
            holder.rbD.setVisibility(View.VISIBLE);
            holder.rbD.setEnabled(false);

            String dapAnChon = dsCauTraLoi.get(position).getDapAnChon();

            if (dapAnChon != null && dapAnChon.equals("D"))
            {
                holder.rbD.setChecked(true);
            }
        }

        holder.txtGiaiThich.setText(ch.getGiaiThich());
        holder.txtGiaiThich.setVisibility(View.VISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dsCauHoi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtNoiDungCauHoi;
        private final TextView txtGiaiThich;
        private final TextView txtSoCauHoi;
        private final RadioButton rbA;
        private final RadioButton rbB;
        private final RadioButton rbC;
        private final RadioButton rbD;
        private final ImageView ivCauHoi;
        private final ConstraintLayout header;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSoCauHoi = itemView.findViewById(R.id.txtSoCauHoi);
            txtNoiDungCauHoi = itemView.findViewById(R.id.txtNoiDungCauHoi);
            ivCauHoi = itemView.findViewById(R.id.ivCauHoi);
            rbA = itemView.findViewById(R.id.rbA);
            rbB = itemView.findViewById(R.id.rbB);
            rbC = itemView.findViewById(R.id.rbC);
            rbD = itemView.findViewById(R.id.rbD);
            header = itemView.findViewById(R.id.header);
            txtGiaiThich = itemView.findViewById(R.id.txtGiaiThichCauHoi);
        }
    }
}
