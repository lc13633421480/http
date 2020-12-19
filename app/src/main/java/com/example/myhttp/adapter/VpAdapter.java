package com.example.myhttp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myhttp.bean.CityData;

import java.util.ArrayList;
import java.util.List;

public class VpAdapter extends FragmentStatePagerAdapter {
    private ArrayList<VpFrag> vpFrags;
    private List<CityData.ResultBean> list;

    public VpAdapter(@NonNull FragmentManager fm, ArrayList<VpFrag> vpFrags, List<CityData.ResultBean> list) {
        super(fm);
        this.vpFrags = vpFrags;
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return vpFrags.get(position);
    }

    @Override
    public int getCount() {
        return vpFrags.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getCity();
    }
}
