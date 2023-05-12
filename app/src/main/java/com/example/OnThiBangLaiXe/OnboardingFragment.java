package com.example.OnThiBangLaiXe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import c.e.O.custom.MyDB;
import c.e.O.custom.MySharedPreferences;


public class OnboardingFragment extends Fragment {


    int img;
    String title,content;
    View mView;
    Boolean a=false;
    MyDB myDB;
    public Button btnNext;
    public OnboardingFragment(int img, String title, String content,Boolean a) {
        this.img=img;
        this.title=title;
        this.content=content;
        this.a=a;
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
        btnNext=mView.findViewById(R.id.btn_next);
        if(a)
        {
            btnNext.setVisibility(View.VISIBLE);
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB = new MyDB(mView.getContext());
                myDB.kiemTraPhienBan();
                MySharedPreferences mySharedPreferences = new MySharedPreferences(mView.getContext());
                mySharedPreferences.putBooleanValue("KEY_FIRST_INSTALL",true);
            }
        });
        imgV.setImageResource(img);
        titleV.setText(title);
        contentV.setText(content);
        return mView;
    }
}