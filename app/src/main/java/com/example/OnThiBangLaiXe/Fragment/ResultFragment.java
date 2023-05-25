package com.example.OnThiBangLaiXe.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Adapter.CauHoiResultAdapter;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.R;

import java.util.List;

public class ResultFragment extends Fragment {


    private RecyclerView rv;
    private List<CauTraLoi> dsCTl;
    private CauHoiResultAdapter adapter;
    private View mView;

    public ResultFragment(List<CauTraLoi> dsCTl) {
        this.dsCTl = dsCTl;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_result, container, false);
        rv=mView.findViewById(R.id.rvCauHoiResult);
        Log.e("DanhSachCauHoi",dsCTl.size()+"");
        adapter=new CauHoiResultAdapter(dsCTl,getContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        return mView;
    }
}