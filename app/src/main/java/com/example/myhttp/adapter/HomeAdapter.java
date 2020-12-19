package com.example.myhttp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.bean.WeatherData;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.VH> {
    private Context context;
    private List<WeatherData.ResultBean.DailyBean> dails;

    public HomeAdapter(Context context, List<WeatherData.ResultBean.DailyBean> dails) {
        this.context = context;
        this.dails = dails;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rlv, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        WeatherData.ResultBean.DailyBean bean = dails.get(position);
        holder.date.setText(dails.get(position).getDate());
        holder.week.setText(dails.get(position).getWeek());
        holder.sun.setText(dails.get(position).getSunrise());
        String wea = bean.getDay().getWeather();
        holder.weath.setText(bean.getDay().getWeather());
        holder.win.setText(bean.getDay().getWinddirect());
        if(wea.equals("晴")){
                Glide.with(context).load(R.drawable.qing).into(holder.iv);
            }else if(wea.equals("小雨")){
                Glide.with(context).load(R.drawable.yun).into(holder.iv);
            }else if(wea.equals("多云")){
                Glide.with(context).load(R.drawable.ph11).into(holder.iv);
            }else{
            Glide.with(context).load(R.drawable.f).into(holder.iv);
        }
    }

    @Override
    public int getItemCount() {
        return dails.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        private final TextView date;
        private final TextView week;
        private final TextView weath;
        private final TextView win;
        private final TextView sun;
        private final ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            week = itemView.findViewById(R.id.week);
            weath = itemView.findViewById(R.id.weath);
            win = itemView.findViewById(R.id.win);
            sun = itemView.findViewById(R.id.sun);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
