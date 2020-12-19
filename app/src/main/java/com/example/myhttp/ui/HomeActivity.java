package com.example.myhttp.ui;

import android.Manifest;
import android.location.Address;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myhttp.LocationUtils;
import com.example.myhttp.R;
import com.example.myhttp.adapter.VpAdapter;
import com.example.myhttp.adapter.VpFrag;
import com.example.myhttp.bean.CityData;
import com.example.myhttp.bean.WeatherData;
import com.example.myhttp.presenter.HomePresenter;
import com.example.myhttp.view.HomeView;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeView.View {

    private HomeView.Presenter homePresenter;
    private CityData cityData;
    private RecyclerView rlv;
    private HashMap<String, String> map;


    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<VpFrag> vpFrags;
    private List<CityData.ResultBean> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }




    private void initData() {
        homePresenter = (HomeView.Presenter) new HomePresenter(this);
        homePresenter.getCity();
//        requestPermissions();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(1, 1, 1, "北京");
//        menu.add(1, 2, 2, "上海");
//        menu.add(1, 3, 3, "天津");
//        return super.onCreateOptionsMenu(menu);
//    }


    @Override
    public void getCityReturn(CityData result) {
        cityData = result;
        list = cityData.getResult();
        vpFrags = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String city = list.get(i).getCity();
            VpFrag vpFrag = new VpFrag();
            vpFrag.setData(city);
            vpFrags.add(vpFrag);
        }
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), vpFrags, list);
        vp.setAdapter(vpAdapter);
        tab.setupWithViewPager(vp);
    }

    @Override
    public void getWeatherReturn(WeatherData result) {

    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == 1) {
//            map = new HashMap<>();
//            map.put("cityid", String.valueOf(cityData.getResult().get(0).getCityid()));
//            homePresenter.getWeather(map);
//        } else if (item.getItemId() == 2) {
//            map.put("cityid", String.valueOf(cityData.getResult().get(23).getCityid()));
//            homePresenter.getWeather(map);
//        } else {
//            map.put("cityid", String.valueOf(cityData.getResult().get(25).getCityid()));
//            homePresenter.getWeather(map);
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void tips(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loading(int visible) {

    }

    private void initView() {
        rlv = findViewById(R.id.rlv);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }


}
