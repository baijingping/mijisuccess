
package com.umeng.soexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.umeng.soexample.fragment.FengFragment;
import com.umeng.soexample.fragment.GoushopFragment;
import com.umeng.soexample.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout ment;
    private TextView home,feng,goushop;
    private FragmentTransaction transaction;
    private FragmentManager supportFragmentManager;
    private HomeFragment homeFragment;
    private FengFragment fengFragment;
    private GoushopFragment goushopFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ment=findViewById(R.id.ment);
        home=findViewById(R.id.home);
        feng=findViewById(R.id.feng);
        goushop=findViewById(R.id.goushop);

        home.setOnClickListener(this);
        feng.setOnClickListener(this);
        goushop.setOnClickListener(this);

        homeFragment = new HomeFragment();
        fengFragment = new FengFragment();
        goushopFragment = new GoushopFragment();

        supportFragmentManager = getSupportFragmentManager();
        transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.ment, homeFragment).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home:
                supportFragmentManager.beginTransaction().replace(R.id.ment,homeFragment).commit();
                break;
            case R.id.feng:
                supportFragmentManager.beginTransaction().replace(R.id.ment,fengFragment).commit();
                break;
            case R.id.goushop:
                supportFragmentManager.beginTransaction().replace(R.id.ment,goushopFragment).commit();
                break;
        }
    }
}
