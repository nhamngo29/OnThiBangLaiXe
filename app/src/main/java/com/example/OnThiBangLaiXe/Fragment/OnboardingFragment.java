package com.example.OnThiBangLaiXe.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.R;
import com.google.android.material.textfield.TextInputLayout;

import com.example.OnThiBangLaiXe.Custom.MyDB;
import com.example.OnThiBangLaiXe.Custom.MySharedPreferences;


public class OnboardingFragment extends Fragment {


    int img;
    String title,content;
    View mView;
    Boolean a=false;
    AutoCompleteTextView autoCompleteTextView;
    TextInputLayout textInputLayout;
    ArrayAdapter<String> adapter;
    MyDB myDB;
    public Button btnNext;
    public OnboardingFragment(int img, String title, String content, Boolean a, Context context) {
        this.img=img;
        this.title=title;
        this.content=content;
        this.a=a;
        myDB=new MyDB(context);
    }

    public OnboardingFragment(int img, String title, String content) {
        this.img = img;
        this.title = title;
        this.content = content;
        this.mView = mView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ImageView imgV;
        TextView titleV,contentV;

        mView= inflater.inflate(R.layout.fragment_onboarding, container, false);
        imgV=mView.findViewById(R.id.img);
        titleV=mView.findViewById(R.id.title);
        contentV=mView.findViewById(R.id.conntent);

        if(a)
        {
            btnNext=mView.findViewById(R.id.btn_next);
            autoCompleteTextView=mView.findViewById(R.id.auto_complete_txt);
            textInputLayout=mView.findViewById(R.id.TextInputLayout);
            textInputLayout.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            adapter=new ArrayAdapter<String>(mView.getContext(),R.layout.list_item_loai_bang, DanhSach.getDsBang());
            autoCompleteTextView.setAdapter(adapter);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(autoCompleteTextView.getText().toString()==null||autoCompleteTextView.getText().toString().equals(""))
                    {
                        Toast.makeText(mView.getContext(),"Vui lòng chọn loại bằng",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(mView.getContext(),autoCompleteTextView.getText(),Toast.LENGTH_SHORT).show();
                    myDB.kiemTraPhienBan();
                    MySharedPreferences mySharedPreferences=new MySharedPreferences(mView.getContext());
                    if(autoCompleteTextView.getText().toString().equals("A1"))
                        mySharedPreferences.puttIntValue("LOAI_GPLX",1);
                    else if(autoCompleteTextView.getText().toString().equals("B1"))
                        mySharedPreferences.puttIntValue("LOAI_GPLX",2);
                    DanhSach.setLoaiBang(mySharedPreferences.getIntValue("LOAI_GPLX"));
                }
            });
        }

        imgV.setImageResource(img);
        titleV.setText(title);
        contentV.setText(content);
        return mView;
    }
}