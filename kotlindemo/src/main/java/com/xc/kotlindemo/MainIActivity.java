package com.xc.kotlindemo;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.xc.kotlindemo.fragment.MainFragment;
import com.xc.kotlindemo.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainIActivity extends AppCompatActivity {
    private List<Fragment> mList;
    MainFragment mainFragment = new MainFragment();
    MineFragment mineFragment = new MineFragment();
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 vp2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maini);
        init();
    }

    void init() {
        mList = new ArrayList<>();
        mList.add(mainFragment);
        mList.add(mineFragment);

        vp2 = findViewById(R.id.vp2);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        MyAdapter myAdapter =
                new MyAdapter(this, mList);
        vp2.setAdapter(myAdapter);
        //重点 设置 bottomNavigationView 的item 的点击事件 设置viewPager2的联动
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.home_item:
                        vp2.setCurrentItem(0);
                        break;
                    case R.id.setting_item:
                        vp2.setCurrentItem(1);
                        break;
                }
                return true;
            }
        });
        //禁用滑动
        vp2.setUserInputEnabled(false);
        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.home_item);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.setting_item);
                        break;
                }
            }
        });
    }


    class MyAdapter extends FragmentStateAdapter {

        List<Fragment> fragmentList;

        public MyAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> list) {
            super(fragmentActivity);
            this.fragmentList = list;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }
}
