package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.Model.LoaiBienBao;
import com.example.OnThiBangLaiXe.R;

import java.util.ArrayList;
import java.util.List;

public class LoaiBienBaoAdapter extends RecyclerView.Adapter<LoaiBienBaoAdapter.ViewHolder>
{
    private List<BienBao> dsBienBao;
    private List<LoaiBienBao> dsLoaiBienBao;
    private Context context;

    public LoaiBienBaoAdapter(List<LoaiBienBao> dsLoaiBienBao, Context context, List<BienBao> dsBienBao) {
        this.dsLoaiBienBao = dsLoaiBienBao;
        this.context = context;
        this.dsBienBao = dsBienBao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loaibienbao, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int maLoaiBB = dsLoaiBienBao.get(position).getMaLoaiBB();

        List<BienBao> dsBienBaoTheoLoai = new ArrayList<>();

        for (BienBao bb : dsBienBao)
        {
            if (bb.getMaLoaiBB() == maLoaiBB)
            {
                dsBienBaoTheoLoai.add(bb);
            }
        }

        holder.rvBienBao.setAdapter(new BienBaoAdapter(dsBienBaoTheoLoai, context));
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        holder.rvBienBao.setLayoutManager(layoutManager);
    }

    @Override
    public int getItemCount() {
        return dsLoaiBienBao.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private final RecyclerView rvBienBao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rvBienBao = itemView.findViewById(R.id.rvBienBao);

        }
    }
}
