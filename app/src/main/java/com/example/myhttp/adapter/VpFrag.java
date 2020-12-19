package com.example.myhttp.adapter;

import android.Manifest;
import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.LocationUtils;
import com.example.myhttp.R;
import com.example.myhttp.bean.CityData;
import com.example.myhttp.bean.WeatherData;
import com.example.myhttp.presenter.HomePresenter;
import com.example.myhttp.ui.HomeActivity;
import com.example.myhttp.view.HomeView;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VpFrag extends Fragment implements HomeView.View{
    private String  city;
    private RecyclerView rlv;
    private HashMap<String, String> map;
    private HomeView.Presenter homePresenter;
    private List<WeatherData.ResultBean.DailyBean> dails;
    private HomeAdapter homeAdapter;
    private TextView tv_addr;
    //申请的权限
    private static final String[] mPermissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            , Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.vp, null);
        homePresenter = (HomeView.Presenter) new HomePresenter(this);
        rlv = inflate.findViewById(R.id.rlv);
        tv_addr = inflate.findViewById(R.id.tv_addr);
        initRlv();
        initData();
        requestPermissions();
        return inflate;
    }

    /**
     * 请求权限
     */
    private void requestPermissions() {
        if (PermissionsUtil.hasPermission(getActivity(), mPermissions)) {
            //有访问权限
            initLoad();
        } else {
            PermissionsUtil.requestPermission(getActivity(), new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permissions) {
                    //用户授予了访问权限
                    initLoad();
                }

                @Override
                public void permissionDenied(@NonNull String[] permissions) {
                    //用户拒绝了访问的申请
                }
            }, mPermissions);
        }
    }

    private void initRlv() {
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        dails = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(), dails);
        rlv.setAdapter(homeAdapter);
    }

    private void initData() {
        map = new HashMap<>();
        map.put("city",city);
        homePresenter.getWeather(map);
    }

    public void setData(String city) {
        this.city = city;
    }

    @Override
    public void getCityReturn(CityData result) {

    }

    @Override
    public void getWeatherReturn(WeatherData result) {
        List<WeatherData.ResultBean.DailyBean> daily = result.getResult().getDaily();
        dails.addAll(daily);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void tips(String tip) {

    }

    @Override
    public void loading(int visible) {

    }
    private void initLoad() {
        LocationUtils.getInstance(getActivity()).setAddressCallback(new LocationUtils.AddressCallback() {
            @Override
            public void onGetAddress(Address address) {
                String countryName = address.getCountryName();//国家
                String adminArea = address.getAdminArea();//省
                String locality = address.getLocality();//市
                String subLocality = address.getSubLocality();//区
                String featureName = address.getFeatureName();//街道
//                LogUtils.eTag("定位地址",countryName,adminArea,locality,subLocality,featureName);
                Toast.makeText(getActivity(), "定位地址" + countryName + adminArea + locality + subLocality + featureName, Toast.LENGTH_SHORT).show();
                tv_addr.setText("定位地址" + countryName + adminArea + locality + subLocality + featureName);
            }

            @Override
            public void onGetLocation(double lat, double lng) {
//                LogUtils.eTag("定位经纬度",lat,lng);
                Toast.makeText(getActivity(), "定位经纬度" + lat + lng, Toast.LENGTH_SHORT).show();
                tv_addr.setText("定位经纬度" + lat + lng);
            }
        });
    }
}
