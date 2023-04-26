package com.example.OnThiBangLaiXe.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.OnThiBangLaiXe.CauHoiActivity;
import com.example.OnThiBangLaiXe.DBHandler;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class CauTraLoiAdapter extends RecyclerView.Adapter<CauTraLoiAdapter.ViewHolder>
{
    private List<CauTraLoi> dsCauTraLoi;
    private Context context;

    private DBHandler db;

    public CauTraLoiAdapter(List<CauTraLoi> dsCauTraLoi, Context context) {
        this.context = context;
        this.dsCauTraLoi = dsCauTraLoi;
        db=new DBHandler(this.context);
    }

    @NonNull
    @Override
    public CauTraLoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CauTraLoiAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cau_hoi, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull CauTraLoiAdapter.ViewHolder holder,int position) {
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

        if (ch.getDapAnA() != null&&!ch.getDapAnA().equals("null"))
        {
            holder.rbA.setText(ch.getDapAnA());
            holder.rbA.setVisibility(View.VISIBLE);

            holder.rbA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                    {
                        ctl.setDapAnChon("A");
                        setDapAn(holder, position,ctl);
                        db.updateDapAnChon(ctl);
                    }

                }
            });
        }

        if (ch.getDapAnB() != null&&!ch.getDapAnB().equals("null"))
        {
            holder.rbB.setText(ch.getDapAnB());
            holder.rbB.setVisibility(View.VISIBLE);
            holder.rbB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                    {
                        ctl.setDapAnChon("B");
                        setDapAn(holder, position,ctl);
                        db.updateDapAnChon(ctl);
                    }
                }
            });
        }

        if (ch.getDapAnC() != null&&!ch.getDapAnC().equals("null"))
        {
            holder.rbC.setText(ch.getDapAnC());
            holder.rbC.setVisibility(View.VISIBLE);
            holder.rbC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                    {

                        ctl.setDapAnChon("C");
                        setDapAn(holder, position,ctl);
                        db.updateDapAnChon(ctl);
                    }
                }
            });
        }

        if (ch.getDapAnD() != null&&!ch.getDapAnD().equals("null"))
        {
            holder.rbD.setText(ch.getDapAnD());
            holder.rbD.setVisibility(View.VISIBLE);
            holder.rbD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    ctl.setDapAnChon("D");
                    setDapAn(holder, position,ctl);
                    db.updateDapAnChon(ctl);
                }
            });

        }
        if(ch.getHinhAnh()!=null&&!ch.getHinhAnh().equals("null"))
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
    }

    private void setDapAn(CauTraLoiAdapter.ViewHolder holder, int position, CauTraLoi value)
    {
        //Thuwjc hien doi backgroup o day
//        CauHoi ch = null;
//
//        for (CauHoi cauHoi : DanhSach.getDsCauHoi())
//        {
//            if (cauHoi.getMaCH() == dsCauTraLoi.get(position).getMaCH())
//            {
//
//            }
//        }
//
//        ch.setDaTraLoiDung(s);
//
//        holder.txtDungSai.setText(" Đã học");
//
//        if (Boolean.TRUE.equals(ch.getGiaiThich()))
//        {
//            holder.ivDungSai.setImageResource(R.drawable.ico_true);
//        }
//        else
//        {
//            holder.ivDungSai.setImageResource(R.drawable.ico_false);
//        }
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
