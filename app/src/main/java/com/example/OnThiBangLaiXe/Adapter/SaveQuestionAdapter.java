package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.R;

import java.util.List;

public class SaveQuestionAdapter extends RecyclerView.Adapter<SaveQuestionAdapter.SaveQuestionViewHolder> {

    private Context context;
    private List<CauHoi> cauHois;

    public SaveQuestionAdapter(Context context, List<CauHoi> cauHois) {
        this.context = context;
        this.cauHois = cauHois;
    }

    public void setCauHois(List<CauHoi> cauHois) {
        this.cauHois = cauHois;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SaveQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SaveQuestionViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cau_hoi_save, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SaveQuestionViewHolder holder, int position) {
        CauHoi cauHoi = cauHois.get(position);

        if (cauHoi == null) return;

        holder.onBind(cauHoi);
    }

    @Override
    public int getItemCount() {
        return cauHois == null ? 0 : cauHois.size();
    }

    public class SaveQuestionViewHolder extends RecyclerView.ViewHolder {

        private TextView textContent;

        public SaveQuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            textContent = itemView.findViewById(R.id.textIdContentQuestion);
        }

        public void onBind(final CauHoi cauHoi) {
            textContent.setText(cauHoi.getNoiDung());
        }
    }
}
