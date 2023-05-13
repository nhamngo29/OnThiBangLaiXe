package com.example.OnThiBangLaiXe.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.OnThiBangLaiXe.R;
import com.example.OnThiBangLaiXe.function;

import java.util.List;

public class FunctionAdapter extends BaseAdapter {
    private Context context;
    private int Layout;
    private List<function> functionList;

    public FunctionAdapter(Context context, int layout, List<function> functionList) {
        this.context = context;
        Layout = layout;
        this.functionList = functionList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return Layout;
    }

    public void setLayout(int layout) {
        Layout = layout;
    }

    public List<function> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<function> functionList) {
        this.functionList = functionList;
    }

    @Override
    public int getCount() {
        return functionList.size();
    }

    @Override
    public Object getItem(int i) {
        return functionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        ImageView img;
        TextView txtTitle;
    }
    @SuppressLint("ServiceCast")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view ==null)
        {
            holder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(Layout,null);
            holder.img=(ImageView) view.findViewById(R.id.imgFunction);
            holder.txtTitle=(TextView) view.findViewById(R.id.txtTitle);
            view.setTag(holder);
        }
        else
        {
            holder=(ViewHolder) view.getTag();
        }
        function fn=functionList.get(i);
        holder.img.setImageResource(fn.getImg());
        holder.txtTitle.setText(fn.getTitle());
        return view;
    }
}
