package com.example.OnThiBangLaiXe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new BBCamFragment();
            case 1:
                return new BBHieuLenhFragment();
            case 2:
                return new BBNguyHiemFragment();
            default:
                return new BBCamFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position)
        {
            case 0:
                title="BIỂN BÁO CẤM";
                break;
            case 1:
                title="BIỂN BÁO NGUY HIỂM";
                break;
            case 2:
                title="BIỂN BÁO HIỆU LỆNH";
                break;
            default:
                title="BIỂN BÁO CẤM";
                break;
        }
        return title;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
