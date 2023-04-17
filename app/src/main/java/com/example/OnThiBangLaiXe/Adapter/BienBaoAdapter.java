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

import java.util.List;

public class BienBaoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BienBao> lBienBao;

    public BienBaoAdapter(Context context, int layout, List<BienBao> lBienBao) {
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
        ViewHolder viewHolder;

        if(view==null)
        {

            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder=new ViewHolder();

            viewHolder.imgBB=view.findViewById(R.id.imgBB);
            viewHolder.txtID=view.findViewById(R.id.txtIDBB);
            viewHolder.txtTitleBB=view.findViewById(R.id.txtTitleBB);
            viewHolder.txtContentBB=view.findViewById(R.id.txtContentBB);
            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();

        }
        BienBao bb=lBienBao.get(i);
        viewHolder.imgBB.setImageResource(bb.getImg());
        viewHolder.txtID.setText(bb.getiD());
        viewHolder.txtTitleBB.setText(bb.getTitle());
        viewHolder.txtContentBB.setText(bb.getContent());
        return view;
    }
    private class ViewHolder
    {
        ImageView imgBB;
        TextView txtID;
        TextView txtTitleBB;
        TextView txtContentBB;
    }
}
