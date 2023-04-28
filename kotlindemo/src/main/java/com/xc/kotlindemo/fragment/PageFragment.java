package com.xc.kotlindemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xc.kotlindemo.R;

public class PageFragment extends Fragment {
    private String mParam;

    public static PageFragment newInstance(String param1) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString("type", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG-onResume", "---------" + mParam);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("TAG-onHiddenChanged", hidden + "---------" + mParam);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString("type");
        }
        Log.e("TAG", "onCreate-----------------------" + mParam);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("TAG", "onCreateView-----------------------" + mParam);
        switch (mParam) {
            case "1":
                return inflater.inflate(R.layout.frag_vp1, container, false);
            case "2":
                return inflater.inflate(R.layout.frag_vp2, container, false);
            case "3":
                return inflater.inflate(R.layout.frag_vp3, container, false);
            case "4":
                return inflater.inflate(R.layout.frag_vp4, container, false);
            default:
                return inflater.inflate(R.layout.frag_vp5, container, false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy-----------------------" + mParam);
    }
}
