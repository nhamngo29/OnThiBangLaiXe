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
        CauHoi ch = new CauHoi();

        CauTraLoi ctl = dsCauTraLoi.get(position);

        for (CauHoi cauHoi : DanhSach.getDsCauHoi())
        {
            if (cauHoi.getMaCH() == ctl.getMaCH())
            {
                ch = cauHoi;
            }
        }
        if(ch.getLuu()==1)
        {
            holder.ivSave.setImageResource(R.drawable.ico_save_gree);
            holder.ivSave.setTag(R.drawable.ico_save_gree);
        }
        CauHoi finalCh = ch;
        holder.ivSave.setOnClickListener(view -> {
            ImageView imageView = (ImageView) view;
            assert(R.id.ivSave == imageView.getId());
            Integer integer = (Integer) imageView.getTag();
            integer = integer == null ? 0 : integer;

            switch(integer) {
                case R.drawable.ico_save_gree:
                    imageView.setImageResource(R.drawable.ico_save);
                    imageView.setTag(R.drawable.ico_save);
                    db.updateLuuLaiCauHoi(finalCh.getMaCH(),0);
                    break;
                case R.drawable.ico_save:
                default:
                    imageView.setImageResource(R.drawable.ico_save_gree);
                    imageView.setTag(R.drawable.ico_save_gree);
                    db.updateLuuLaiCauHoi(finalCh.getMaCH(),1);
                    break;
            }

        });
        holder.txtSoCauHoi.setText("Câu " + (position + 1) + "/" + dsCauTraLoi.size() + " câu |");

        holder.txtNoiDungCauHoi.setText(ch.getNoiDung());

        if (ch.getDapAnA() != null && !ch.getDapAnA().equals("null"))
        {
            holder.rbA.setText(ch.getDapAnA());
            holder.rbA.setVisibility(View.VISIBLE);
            holder.rbA.setOnCheckedChangeListener((compoundButton, b) -> setDapAn("A", position));
        }

        if (ch.getDapAnB() != null && !ch.getDapAnB().equals("null"))
        {
            holder.rbB.setText(ch.getDapAnB());
            holder.rbB.setVisibility(View.VISIBLE);
            holder.rbB.setOnCheckedChangeListener((compoundButton, b) -> setDapAn("B", position));
        }

        if (ch.getDapAnC() != null && !ch.getDapAnC().equals("null"))
        {
            holder.rbC.setText(ch.getDapAnC());
            holder.rbC.setVisibility(View.VISIBLE);
            holder.rbC.setOnCheckedChangeListener((compoundButton, b) -> setDapAn("C", position));
        }

        if (ch.getDapAnD() != null && !ch.getDapAnD().equals("null"))
        {
            holder.rbD.setText(ch.getDapAnD());
            holder.rbD.setVisibility(View.VISIBLE);
            holder.rbD.setOnCheckedChangeListener((compoundButton, b) -> setDapAn("D", position));
        }

        if(ch.getHinhAnh() != null &&! ch.getHinhAnh().equals("null"))
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

    private void setDapAn(String dapAn, int position)
    {
        for (CauTraLoi ctl : DanhSach.getDsCauTraLoi())
        {
            if (ctl.getMaCH() == dsCauTraLoi.get(position).getMaCH()
                    && ctl.getMaDeThi() == dsCauTraLoi.get(position).getMaDeThi())
            {
                ctl.setDapAnChon(dapAn);

                List<CauTraLoi> check = DanhSach.getDsCauTraLoi();

                db.updateDapAnChon(ctl);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dsCauTraLoi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtNoiDungCauHoi, txtSoCauHoi;
        private final RadioButton rbA, rbB, rbC, rbD;
        private final ImageView  ivCauHoi,ivSave;;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSoCauHoi = itemView.findViewById(R.id.txtSoCauHoi);
            txtNoiDungCauHoi = itemView.findViewById(R.id.txtNoiDungCauHoi);
            ivCauHoi = itemView.findViewById(R.id.ivCauHoi);
            ivSave=itemView.findViewById(R.id.ivSave);
            rbA = itemView.findViewById(R.id.rbA);
            rbB = itemView.findViewById(R.id.rbB);
            rbC = itemView.findViewById(R.id.rbC);
            rbD = itemView.findViewById(R.id.rbD);
        }
    }
}
