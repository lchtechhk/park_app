package com.lch.carparking.structure.CarPaking;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lch.carparking.R;
import com.lch.carparking.core.model.CarParkingModel;
import com.lch.carparking.utils.ObjectUtils;

import java.util.List;

public class CarParkAdapter extends BaseAdapter {
    private Context context;
    public List<CarParkingModel> carParkingModel_array;
    public String orientation;

    public CarParkAdapter(Context context, List<CarParkingModel> carParkingModel_array,String orientation) {
        this.context = context;
        this.carParkingModel_array = carParkingModel_array;
        this.orientation = orientation;
    }

    @Override
    public int getCount() {
        return carParkingModel_array.size();
    }

    @Override
    public Object getItem(int position) {
        return carParkingModel_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return carParkingModel_array.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {

            // get layout from mobile.xml
            if(this.orientation.equals("horizontal_left")){
                gridView = inflater.inflate(R.layout.grid_item_hor_left, null);
            }else if(this.orientation.equals("horizontal_right")){
                gridView = inflater.inflate(R.layout.grid_item_hor_right, null);
            }else {
                gridView = inflater.inflate(R.layout.grid_item, null);
            }

            // set value into textview
            TextView park_id = (TextView) gridView.findViewById(R.id.park_id);
            TextView car_status = (TextView) gridView.findViewById(R.id.car_status);
            LinearLayout color_park = (LinearLayout) gridView.findViewById(R.id.color_park);
            park_id.setText(carParkingModel_array.get(position).getCode());

            //
            String operation_status = carParkingModel_array.get(position).getOperation_status();
            if(ObjectUtils.isNullEmpty(operation_status)){
                color_park.setBackgroundColor(Color.parseColor("#FD0E0E"));
//                car_status.setText("缺");
            }else if("active".equals(operation_status)){
                color_park.setBackgroundColor(Color.parseColor("#74FD0E"));
//                car_status.setText("有空位");
            }else if("inactive".equals(operation_status)){
                if(TextUtils.isEmpty(carParkingModel_array.get(position).getBooking_status())){
                    color_park.setBackgroundColor(Color.parseColor("#FD0E0E"));
                }else {
                    switch (carParkingModel_array.get(position).getBooking_status()){
                        case "paid":
                        case "holding":
                            color_park.setBackgroundColor(Color.parseColor("#F2FD0E"));
//                        car_status.setText("保留中");
                            break;
                        case "arrival":
                            color_park.setBackgroundColor(Color.parseColor("#FD0E0E"));
//                        car_status.setText("已到達");
                            break;
                        default:
                            color_park.setBackgroundColor(Color.parseColor("#FD0E0E"));
//                        car_status.setText("缺");
                            break;
                    }
                }

            }
            car_status.setText(carParkingModel_array.get(position).getBooking_status_descr());
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
