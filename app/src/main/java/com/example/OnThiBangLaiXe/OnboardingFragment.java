package com.example.OnThiBangLaiXe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import c.e.O.custom.MyDB;


public class OnboardingFragment extends Fragment {


    int img;
    String title,content;
    View mView;
    Boolean a=false;
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
        btnNext=mView.findViewById(R.id.btn_next);
        if(a)
        {
            btnNext.setVisibility(View.VISIBLE);
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.kiemTraPhienBan();
            }
        });
        imgV.setImageResource(img);
        titleV.setText(title);
        contentV.setText(content);
        return mView;
    }
}