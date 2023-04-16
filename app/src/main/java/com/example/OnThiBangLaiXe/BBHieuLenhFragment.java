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

public class BBHieuLenhFragment extends Fragment {


    ListView listView;
    ArrayList<BienBao> arrayList;
    BienBaoAdapter adapter;

    public BBHieuLenhFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b_b_hieu_lenh,container,false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.lvBB);
        arrayList=new ArrayList<>();



        arrayList.add(new BienBao(R.drawable.signr122,"R.122","Dường lại","Dừng lại|Biển báo hiệu buộc các xe cơ giới và thô sơ kể cả xe được ưu tiên theo quy định phải dừng lại. Chỉ được phép đi nếu có người điều khiển giao thông hoặc đèn cờ cho phép đi. Nếu không thì chỉ được phép đi khi trên đường không còn nguy cơ gây mất an toàn giao thông."));
        arrayList.add(new BienBao(R.drawable.signr301a,"R.301a","Các xe chỉ được đi thẳng","Được đặt trước ngã ba, ngã tư. Các xe chỉ được đi thẳng ở khu vực ngã ba, ngã tư."));
        arrayList.add(new BienBao(R.drawable.signr301b,"R.301b","Các xe chỉ được rẽ phải","Được đặt sau ngã ba, ngã tư. Các xe chỉ được rẽ phải ở khu vực trước mặt biển."));
        // Inflate the layout for this fragment
        adapter=new BienBaoAdapter(getActivity(),R.layout.layout_bien_bao,arrayList);
        listView.setAdapter(adapter);
        event();
    }
    void event()
    {
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getActivity(),DetailsBienBaoActivity.class);
            BienBao bb = arrayList.get(i);
            intent.putExtra("bienbao", bb);
            startActivity(intent);
        });
    }

}