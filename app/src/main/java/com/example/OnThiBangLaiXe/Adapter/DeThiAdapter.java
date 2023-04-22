package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.CauHoiActivity;
import com.example.OnThiBangLaiXe.CauTraLoiActivity;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.DeThi;
import com.example.OnThiBangLaiXe.R;

import java.util.List;

public class DeThiAdapter extends RecyclerView.Adapter<DeThiAdapter.ViewHolder>
{
    private List<DeThi> dsDeThi;
    private Context context;

    public DeThiAdapter(List<DeThi> dsDeThi, Context context) {
        this.context = context;
        this.dsDeThi = dsDeThi;
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

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CauTraLoiActivity.class);
            intent.putExtra("MaDeThi", dsDeThi.get(position).getMaDeThi());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dsDeThi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtTenDeThi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenDeThi = itemView.findViewById(R.id.txtTenDeThi);
        }
    }
}
