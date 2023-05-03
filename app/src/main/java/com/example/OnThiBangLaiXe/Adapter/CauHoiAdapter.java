package com.example.OnThiBangLaiXe.Adapter;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.DBHandler;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class CauHoiAdapter extends RecyclerView.Adapter<CauHoiAdapter.ViewHolder>
{
    private List<CauHoi> dsCauHoi;
    private Context context;
    private DBHandler db;

    public CauHoiAdapter(List<CauHoi> dsCauHoi, Context context) {
        this.context = context;
        this.dsCauHoi = dsCauHoi;
        db=new DBHandler(context);
    }

    @NonNull
    @Override
    public CauHoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CauHoiAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cau_hoi, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull CauHoiAdapter.ViewHolder holder, int position) {
        CauHoi ch = dsCauHoi.get(position);
        holder.txtSoCauHoi.setText("Câu " + (position + 1) + "/" + dsCauHoi.size() + " câu |");
        if(ch.getLuu()==1)
        {
            holder.ivSave.setImageResource(R.drawable.ico_save_gree);
            holder.ivSave.setTag(R.drawable.ico_save_gree);
        }
        if (ch.getDaTraLoiDung()!=0)
        {
            holder.txtDungSai.setText("Đã học");
            if (ch.getDaTraLoiDung()==1)
            {
                holder.ivDungSai.setImageResource(R.drawable.ico_true);
            }
            else if(ch.getDaTraLoiDung()==2)
            {
                holder.ivDungSai.setImageResource(R.drawable.ico_false);
            }

        }
        else
        {
            holder.txtDungSai.setText("Chưa học");
        }

        holder.txtNoiDungCauHoi.setText(ch.getNoiDung());

//        holder.ivCauHoi.setImageResource(context.getResources().getIdentifier(
//                "Tên file hình", "drawable", context.getPackageName()));
        holder.ivSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ImageView imageView = (ImageView) view;
                assert(R.id.ivSave == imageView.getId());
                Integer integer = (Integer) imageView.getTag();
                integer = integer == null ? 0 : integer;
                switch(integer) {
                    case R.drawable.baseline_bookmark_24_green:
                        imageView.setImageResource(R.drawable.baseline_bookmark_24);
                        imageView.setTag(R.drawable.baseline_bookmark_24);
                        db.updateLuuLaiCauHoi(ch.getMaCH(),0);
                        break;
                    case R.drawable.baseline_bookmark_24:
                    default:
                        imageView.setImageResource(R.drawable.baseline_bookmark_24_green);
                        imageView.setTag(R.drawable.baseline_bookmark_24_green);
                        db.updateLuuLaiCauHoi(ch.getMaCH(),1);
                        break;
                }

            }
        });

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
        if (ch.getDapAnA() != null&&!ch.getDapAnA().equals("null"))
        {
            holder.rbA.setText(ch.getDapAnA());
            holder.rbA.setVisibility(View.VISIBLE);

            holder.rbA.setOnCheckedChangeListener((i, v) -> setDapAn(holder, position, "A"));


        }
        if (ch.getDapAnB() != null&&!ch.getDapAnB().equals("null"))
        {
            holder.rbB.setText(ch.getDapAnB());
            holder.rbB.setVisibility(View.VISIBLE);
            holder.rbB.setOnCheckedChangeListener((i, v) -> setDapAn(holder, position, "B"));
        }

        if (ch.getDapAnC() != null&&!ch.getDapAnC().equals("null"))
        {
            holder.rbC.setText(ch.getDapAnC());
            holder.rbC.setVisibility(View.VISIBLE);
            holder.rbC.setOnCheckedChangeListener((i, v) -> setDapAn(holder, position, "C"));
        }

        if (ch.getDapAnD() != null&&!ch.getDapAnD().equals("null"))
        {
            holder.rbD.setText(ch.getDapAnD());
            holder.rbD.setVisibility(View.VISIBLE);
            holder.rbD.setOnCheckedChangeListener((i, v) -> setDapAn(holder, position, "D"));
        }

    }

    private void setDapAn(CauHoiAdapter.ViewHolder holder, int position, String value)
    {
        CauHoi ch = dsCauHoi.get(position);

        if(ch.getGiaiThich() != null || !ch.getGiaiThich().isEmpty() || !ch.getGiaiThich().equals("null"))
        {
            holder.txtGiaiThichCauHoi.setText(ch.getGiaiThich());
            holder.txtGiaiThichCauHoi.setVisibility(View.VISIBLE);
        }

        holder.rbA.setEnabled(false);
        holder.rbB.setEnabled(false);
        holder.rbC.setEnabled(false);
        holder.rbD.setEnabled(false);

        holder.txtDungSai.setText("Đã học");


        if (ch.getDapAnDung().equals(value))
        {
            holder.ivDungSai.setImageResource(R.drawable.ico_true);

            switch (value)
            {
                case "A":
                    holder.rbA.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_correct));
                    holder.rbA.setTextColor(context.getColor(R.color.black));
                    break;
                case "B":
                    holder.rbB.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_correct));
                    holder.rbB.setTextColor(context.getColor(R.color.black));
                    break;
                case "C":
                    holder.rbC.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_correct));
                    holder.rbC.setTextColor(context.getColor(R.color.black));
                    break;
                case "D":
                    holder.rbD.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_correct));
                    holder.rbA.setTextColor(context.getColor(R.color.black));
                    break;
            }
        }
        else
        {
            holder.ivDungSai.setImageResource(R.drawable.ico_false);

            switch (value)
            {
                case "A":
                    holder.rbA.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_wrong));
                    holder.rbA.setTextColor(context.getColor(R.color.black));
                    break;
                case "B":
                    holder.rbB.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_wrong));
                    holder.rbB.setTextColor(context.getColor(R.color.black));
                    break;
                case "C":
                    holder.rbC.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_wrong));
                    holder.rbC.setTextColor(context.getColor(R.color.black));
                    break;
                case "D":
                    holder.rbD.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_wrong));
                    holder.rbA.setTextColor(context.getColor(R.color.black));
                    break;
            }

            switch (ch.getDapAnDung())
            {
                case "A":
                    holder.rbA.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_correct));
                    holder.rbA.setTextColor(context.getColor(R.color.black));
                    break;
                case "B":
                    holder.rbB.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_correct));
                    holder.rbB.setTextColor(context.getColor(R.color.black));
                    break;
                case "C":
                    holder.rbC.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_correct));
                    holder.rbC.setTextColor(context.getColor(R.color.black));
                    break;
                case "D":
                    holder.rbD.setBackground(AppCompatResources.getDrawable(context,
                            R.drawable.radio_button_background_shape_correct));
                    holder.rbA.setTextColor(context.getColor(R.color.black));
                    break;
            }
        }

        DanhSach.getDsCauHoi().get(position).setDaTraLoiDung(Boolean.TRUE.equals(value) ? 1 : 2);
        db.updateDaTraLoi(ch.getMaCH(), Boolean.TRUE.equals(value) ? 1 : 2);


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

        private final ImageView ivDungSai, ivCauHoi,ivSave;
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
            ivSave = itemView.findViewById(R.id.ivSave);
        }

    }
}
