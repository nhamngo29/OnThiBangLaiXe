package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.R;

import java.util.ArrayList;

public class BienBaoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<BienBao> lBienBao;

    public BienBaoAdapter(Context context, int layout, ArrayList<BienBao> lBienBao) {
        this.context = context;
        this.layout = layout;
        this.lBienBao = lBienBao;
    }

    @Override
    public int getCount() {
        return lBienBao.size();
    }

    @Override
    public Object getItem(int i) {
        return lBienBao.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);
        BienBao bb=lBienBao.get(i);
        ImageView imgBB=view.findViewById(R.id.imgBB);
        TextView txtID=view.findViewById(R.id.txtIDBB);
        TextView txtTitleBB=view.findViewById(R.id.txtTitleBB);
        TextView txtContentBB=view.findViewById(R.id.txtContentBB);
        imgBB.setImageResource(bb.getImg());
        txtID.setText(bb.getiD());
        txtTitleBB.setText(bb.getTitle());
        txtContentBB.setText(bb.getContent());
        return view;
    }
}
