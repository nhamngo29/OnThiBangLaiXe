package com.example.OnThiBangLaiXe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.OnThiBangLaiXe.Adapter.ViewWelcomeAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import c.e.O.custom.MyDB;
import me.relex.circleindicator.CircleIndicator;

public class OnBoardingActivity extends AppCompatActivity {
    private TextView tvSkip;
    private ViewPager viewPager;
    private RelativeLayout layoutBottom;
    private CircleIndicator circleIndicator;
    private LinearLayout layoutNext;
    private ViewWelcomeAdapter viewWelcomeAdapter;
    String DB_PATH_SUFFIX="/databases/";

    String DATABASE_NAME= "db.db";
    MyDB myDB;
    DBHandler dbHandler;
    List<Fragment> lstFrm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        processCopy();
        myDB=new MyDB(getApplicationContext());
        myDB.capNhatDatabase();
        myDB.downloadWithBytes("BienBao");
        myDB.downloadWithBytes("CauHoi");
        lstFrm=new ArrayList<>();
        lstFrm.add(new OnboardingFragment(R.drawable.ico_car_welcome,"1. Ngày đầu tiên","Bạn nên dành 8 tiếng đề học hết tất cả các loại biển báo hay gặp. Tập trung vào các loại biển báo hay gặp.Tập trung vào các loại biển báo cấm, biển báo hiệu lệnh, biển bảo chỉ dẫn, biển báo nguy hiểm..."));
        lstFrm.add(new OnboardingFragment(R.drawable.ico_moto_welcome,"2. Ngày thứ hai","Bạn hãy vào phần học 450 câu lý thuyết, học đi học lại các câu lý thuyết này và các mẹo làm của từng câu, nhớ các định nghĩa cơ bản như nồng độ cồn, tốc độ cho phép...và học mẹp làm bài các câu hỏi sa hinh..."));
        lstFrm.add(new OnboardingFragment(R.drawable.ico_license_welcome,"3. Ngày thứ ba","Bạn hãy vào làm hết các bộ đề thi của từng hạn bằng lái xe. Sau khi làm xong tất các đề mặc định(đều đạt > 28 câu trơ lên). Bạn hãy trợn 1 đề ngẫu nhiên và làm đến khi nào điểm thi của bạn đều đạt thì chúc mừng bạn đã hoàn thành quá trình ôn luyện",true));
        init();
        viewWelcomeAdapter=new ViewWelcomeAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,lstFrm);
        viewPager.setAdapter(viewWelcomeAdapter);
        circleIndicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==2)
                {
                    tvSkip.setVisibility(View.GONE);
                    layoutBottom.setVisibility(View.GONE);
                }
                else
                {
                    tvSkip.setVisibility(View.VISIBLE);
                    layoutBottom.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void init()
    {
        tvSkip=findViewById(R.id.tvSkip);
        viewPager=findViewById(R.id.vp);
        layoutBottom=findViewById(R.id.lyBottom);
        circleIndicator=findViewById(R.id.CircleIndicator);
        layoutNext=findViewById(R.id.layout_next);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });
        layoutNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem()<2)
                {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
            }
        });
    }
    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists())
        {
            try{CopyDataBaseFromAsset();
                Log.e("SQL","Đã Coppy đến database");
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        Log.e("SQL","Đã tồn tại");

    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
    //Coppy db vao database cua mays
    public void CopyDataBaseFromAsset() {

        try {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            int size = myInput.available();
            byte[] buffer = new byte[size];
            myInput.read(buffer);
            myOutput.write(buffer);
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}