package com.example.OnThiBangLaiXe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.OnThiBangLaiXe.Adapter.BienBaoAdapter;
import com.example.OnThiBangLaiXe.Model.BienBao;

import java.util.ArrayList;

public class BBCamFragment extends Fragment {
    ListView listView;
    ArrayList<BienBao> arrayList;
    BienBaoAdapter adapter;
    public BBCamFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b_b_cam,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.lvBB);
        arrayList=new ArrayList<>();
        arrayList.add(new BienBao(R.drawable.signp101,"P.101","Đường cấm","a)Cấm các loại phương tiện đi lại cả hai hướng trừ các xe được ưu tiên theo quy định \n\nb)Nếu đường cấm vì lý do đường, cầu bị tắc thì biển cấm đặt ở giữa phần xxe chạy kèm theo có hàng rào chắn ngang"));
        arrayList.add(new BienBao(R.drawable.signp102,"P.102","Cấm đi ngược chiều","a)Cấm các loại phương tiện đi lại cả hai hướng trừ các xe được ưu tiên theo quy định \n\nb)Nếu đường cấm vì lý do đường, cầu bị tắc thì biển cấm đặt ở giữa phần xxe chạy kèm theo có hàng rào chắn ngang"));
        arrayList.add(new BienBao(R.drawable.signp103a,"P.102","Cấm xe ô tô","a)Cấm các loại phương tiện đi lại cả hai hướng trừ các xe được ưu tiên theo quy định \n\nb)Nếu đường cấm vì lý do đường, cầu bị tắc thì biển cấm đặt ở giữa phần xxe chạy kèm theo có hàng rào chắn ngang"));
        // Inflate the layout for this fragment
        adapter=new BienBaoAdapter(getActivity(),R.layout.layout_bien_bao,arrayList);
        listView.setAdapter(adapter);
        event();
    }
    void event()
    {
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent=new Intent(getActivity(),DetailsBienBaoActivity.class);
            BienBao bb=arrayList.get(i);
            intent.putExtra("bienbao", bb);
            startActivity(intent);
        });
    }
}