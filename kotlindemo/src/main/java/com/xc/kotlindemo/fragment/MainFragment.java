package com.xc.kotlindemo.fragment;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.androidkun.xtablayout.XTabLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xc.kotlindemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_main, container, false);
        init(root);
        return root;
    }

    private void init(View root) {
        TabLayout xtab = root.findViewById(R.id.xtab);
        ViewPager2 vp2 = root.findViewById(R.id.vp2);
        Myadapter myadapter = new Myadapter(getActivity().getSupportFragmentManager(), getLifecycle(), getList());
        vp2.setAdapter(myadapter);

        new TabLayoutMediator(xtab, vp2, (tab, position) -> {
            //这里可以自定义TabView
//            TextView tabView = new TextView(getActivity());
//            int[][] states = new int[2][];
//            states[0] = new int[]{android.R.attr.state_selected};
//            states[1] = new int[]{};
//            int[] colors = new int[]{activeColor, normalColor};
//            ColorStateList colorStateList = new ColorStateList(states, colors);
//            tabView.setText(tabs[position]);
//            tabView.setTextSize(normalSize);
//            tabView.setTextColor(colorStateList);
//            tab.setCustomView(tabView);
            tab.setText(initPageTitleList().get(position));
        }).attach();
        vp2.setCurrentItem(0);
        vp2.setOffscreenPageLimit(getList().size());


        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int tabCount = xtab.getTabCount();
                for (int i = 0; i < tabCount; i++) {
                    TabLayout.Tab tab = xtab.getTabAt(i);
                    TextView tabView = (TextView) tab.getCustomView();

                    Log.e("TAG-----", (tabView == null) + "");
//                    if (tab.getPosition() == position) {
//                        tabView.setTextSize(25f);
//                        tabView.setTypeface(Typeface.DEFAULT_BOLD);
//                    } else {
//                        tabView.setTextSize(20f);
//                        tabView.setTypeface(Typeface.DEFAULT);
//                    }
                }
            }
        });

    }

    private List<String> initPageTitleList() {
        return Arrays.asList("推荐", "关注", "娱乐", "游戏", "电影", "电视剧", "实时新闻", "推荐", "关注", "娱乐", "游戏", "电影", "电视剧", "实时新闻", "推荐", "关注", "娱乐", "游戏", "电影", "电视剧", "实时新闻");
    }

    private List<Fragment> getList() {
        ArrayList<Fragment> AllList = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            AllList.add(PageFragment.newInstance(i + ""));
        }
        return AllList;
    }


    class Myadapter extends FragmentStateAdapter {
        List<Fragment> fragmentList;

        public Myadapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> list) {
            super(fragmentManager, lifecycle);
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
