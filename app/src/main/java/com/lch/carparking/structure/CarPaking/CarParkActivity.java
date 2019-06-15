package com.lch.carparking.structure.CarPaking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.lch.carparking.R;
import com.lch.carparking.core.model.CarParkingModel;
import com.lch.carparking.core.presenter.CarParkPresenter;
import com.lch.carparking.core.view.CarParkView;
import com.lch.carparking.dao.CarparkDao;
import com.lch.carparking.dao.CarparkDaoImpl;
import com.lch.carparking.element.ClassicDialog;
import com.lch.carparking.element.PreBookingDialogBox;
import com.lch.carparking.network.RequestModel;
import com.lch.carparking.sharePreferences.MyPreferences;
import com.lch.carparking.structure.photo.AExit;
import com.lch.carparking.structure.photo.BExit;
import com.lch.carparking.utils.GsonUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarParkActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener, CarParkView , AdapterView.OnItemSelectedListener, View.OnClickListener {

    @BindView(R.id.top_left_coner)
    GridView top_left_coner;
    @BindView(R.id.top_middle)
    GridView top_middle;
    @BindView(R.id.top_middle_2)
    GridView top_middle_2;
    @BindView(R.id.top_right_coner)
    GridView top_right_coner;
    @BindView(R.id.middle_left)
    GridView middle_left;
    @BindView(R.id.middle_center)
    GridView middle_center;
    @BindView(R.id.middle_center_2)
    GridView middle_center_2;
    @BindView(R.id.middle_right)
    GridView middle_right;
    @BindView(R.id.bottom_center)
    GridView bottom_center;
    @BindView(R.id.top_4)
    TextView top_4;
    @BindView(R.id.bottom_2)
    TextView bottom_2;


    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout laySwipe;
    @BindView(R.id.sp_provinces)
    SmartMaterialSpinner spProvince;
    //
    @BindView(R.id.image_layout)
    LinearLayout image_layout;
    @BindView(R.id.img)
    ImageView img;
    //
    @BindView(R.id.carpark_layout)
    LinearLayout carpark_layout;

    CarParkPresenter carParkPresenter;
    CarparkDao carparkDao;
    String request_json;
    List<CarParkingModel> carParkingModel_array;
    ClassicDialog classicDialog;
    private List<String> provinceList;
    //
    MyPreferences myPreferences;
    int user_id = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_park);
        ButterKnife.bind(this, this);
        getSupportActionBar().hide();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null)
            if(extras.getBoolean("arrived")){
                classicDialog.showBasicDialog(this,"到達信息", "到達時間為01：59PM \n ");
            }else if(extras.getBoolean("left")){
                classicDialog.showBasicDialog(this,"離開信息", "離開時間為03：00PM \n費用為$50");
            }
        }

        myPreferences = new MyPreferences(this,"login_information");
        if(!TextUtils.isEmpty(myPreferences.getPreferences_loginInformation().get("user_id"))){
            user_id = Integer.parseInt(myPreferences.getPreferences_loginInformation().get("user_id"));
        }
        spProvince.setOnItemSelectedListener(this);
        classicDialog = new ClassicDialog();
        carParkPresenter = new CarParkPresenter(this);
        carparkDao = new CarparkDaoImpl(this);
        request_json = getRequestModel_Json();
        carParkingModel_array = carparkDao.findByUserId(request_json);
        carParkPresenter.updateGridView(carParkingModel_array);
        setUpSpinner();
        top_4.setOnClickListener(this);
        bottom_2.setOnClickListener(this);
    }

    public String getRequestModel_Json(){
        RequestModel requestModel = new RequestModel();
        requestModel.setUser_id(user_id);
        String json = GsonUtils.modelToJson(requestModel);
        return json;
    }

    @Override
    public void onBackPressed() {
        // your code.
        classicDialog.back_map(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int click_position = 0;
        switch (parent.getId()){
            case R.id.top_left_coner:
                click_position = position;
            break;
            case R.id.top_middle:
                click_position = position + 3;
            break;
            case R.id.top_middle_2:
                click_position = position + 6;
                break;
            case R.id.top_right_coner:
                click_position = position + 9;
            break;
            case R.id.middle_left:
                click_position = position + 12;
            break;
            case R.id.middle_center:
                click_position = position + 15;
            break;
            case R.id.middle_center_2:
                click_position = position + 18;
                break;
            case R.id.middle_right:
                click_position = position + 21;
            break;
            case R.id.bottom_center:
                click_position = position + 24;
            break;
        }
        Log.v("asd : " , "Id : " + parent.getId());
        Log.v("asd : " , "click_position : " + click_position);

        //Check Login
        if(user_id == 0){
            PreBookingDialogBox preBookingDialogBox = new PreBookingDialogBox();
            FragmentManager fm = getSupportFragmentManager();
            preBookingDialogBox.show(fm,"12");
            return;
        }
        //
        RequestModel requestModel = new RequestModel();
        requestModel.setUser_id(user_id);
        requestModel.setPark_id(carParkingModel_array.get(click_position).getId());
        String json = GsonUtils.modelToJson(requestModel);

        String operation_status = carParkingModel_array.get(click_position).getOperation_status();
        String booking_status = carParkingModel_array.get(click_position).getBooking_status();


        if("active".equals(operation_status)){
            classicDialog.show_holding(this,carParkingModel_array.get(click_position),carparkDao,json,carParkPresenter);
        }else if("inactive".equals(operation_status)){
            if(TextUtils.isEmpty(booking_status)){

            }else {
                switch (booking_status){
                    case "holding":
                        classicDialog.showQ_R_arrvial(this,carParkingModel_array.get(click_position),carparkDao,json,carParkPresenter);
                        break;
                    case "paid":
                        classicDialog.show_arrvial(this,carParkingModel_array.get(click_position),carparkDao,json,carParkPresenter);
                        break;
                    case "arrival":
                        classicDialog.show_level(this,carParkingModel_array.get(click_position),carparkDao,json,carParkPresenter);
                        break;
                    default:
                        classicDialog.show_used_park(this);
                    break;
                }
            }
        }
    }

    @Override
    public void onRefresh() {
        laySwipe.setRefreshing(true);
        carParkingModel_array = carparkDao.findByUserId(request_json);
        updateGridView(carParkingModel_array);
        laySwipe.setRefreshing(false);
    }


    @Override
    public void updateGridView(List<CarParkingModel> carParkingModel_array) {
        this.carParkingModel_array = carParkingModel_array;
        update_top_left_coner( this.carParkingModel_array);
        update_top_middle(this.carParkingModel_array);
        update_top_middle_2(this.carParkingModel_array);
        update_top_right_coner(this.carParkingModel_array);
        update_middle_left(this.carParkingModel_array);
        update_middle_center(this.carParkingModel_array);
        update_middle_center_2(this.carParkingModel_array);
        update_middle_right(this.carParkingModel_array);
        update_buttom_center(this.carParkingModel_array);

        laySwipe.setOnRefreshListener(this);


    }

    public void update_top_left_coner(List<CarParkingModel> carParkingModel_array){
        List<CarParkingModel> grid_model = new ArrayList<>(carParkingModel_array.subList(0, 3));
        top_left_coner.setNumColumns(1);
        top_left_coner.setAdapter(new CarParkAdapter(this, grid_model,"vertical"));
        top_left_coner.setOnItemClickListener(this);
    }
    public void update_top_middle(List<CarParkingModel> carParkingModel_array){
        List<CarParkingModel> grid_model = new ArrayList<>(carParkingModel_array.subList(3, 6));
        top_middle.setNumColumns(1);
        top_middle.setAdapter(new CarParkAdapter(this, grid_model,"horizontal_right"));
        top_middle.setOnItemClickListener(this);
    }
    public void update_top_middle_2(List<CarParkingModel> carParkingModel_array){
        List<CarParkingModel> grid_model = new ArrayList<>(carParkingModel_array.subList(6, 9));
        top_middle_2.setNumColumns(1);
        top_middle_2.setAdapter(new CarParkAdapter(this, grid_model,"horizontal_left"));
        top_middle_2.setOnItemClickListener(this);
    }
    public void update_top_right_coner(List<CarParkingModel> carParkingModel_array){
        List<CarParkingModel> grid_model = new ArrayList<>(carParkingModel_array.subList(9,12));
        top_right_coner.setNumColumns(1);
        top_right_coner.setAdapter(new CarParkAdapter(this, grid_model,"vertical"));
        top_right_coner.setOnItemClickListener(this);
    }
    public void update_middle_left(List<CarParkingModel> carParkingModel_array){
        List<CarParkingModel> grid_model = new ArrayList<>(carParkingModel_array.subList(12,15));
        middle_left.setNumColumns(1);
        middle_left.setAdapter(new CarParkAdapter(this, grid_model,"vertical"));
        middle_left.setOnItemClickListener(this);
    }
    public void update_middle_center(List<CarParkingModel> carParkingModel_array){
        List<CarParkingModel> grid_model = new ArrayList<>(carParkingModel_array.subList(15,18));
        middle_center.setNumColumns(1);
        middle_center.setAdapter(new CarParkAdapter(this, grid_model,"horizontal_right"));
        middle_center.setOnItemClickListener(this);
    }
    public void update_middle_center_2(List<CarParkingModel> carParkingModel_array){
        List<CarParkingModel> grid_model = new ArrayList<>(carParkingModel_array.subList(18,21));
        middle_center_2.setNumColumns(1);
        middle_center_2.setAdapter(new CarParkAdapter(this, grid_model,"horizontal_left"));
        middle_center_2.setOnItemClickListener(this);
    }
    public void update_middle_right(List<CarParkingModel> carParkingModel_array){
        List<CarParkingModel> grid_model = new ArrayList<>(carParkingModel_array.subList(21,24));
        middle_right.setNumColumns(1);
        middle_right.setAdapter(new CarParkAdapter(this, grid_model,"vertical"));
        middle_right.setOnItemClickListener(this);
    }
    public void update_buttom_center(List<CarParkingModel> carParkingModel_array){
        List<CarParkingModel> grid_model = new ArrayList<>(carParkingModel_array.subList(24,28));
        bottom_center.setNumColumns(4);
        bottom_center.setAdapter(new CarParkAdapter(this, grid_model,"vertical"));
        bottom_center.setOnItemClickListener(this);
    }
    public void setUpSpinner(){
        provinceList = new ArrayList<>();
        provinceList.add("停車場");
        provinceList.add("麥當勞");
        provinceList.add("洗手間");
        provinceList.add("百老滙");
        spProvince.setItems(provinceList);
        spProvince.setSelection(0,false);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this,"abcd -- " + position,Toast.LENGTH_SHORT).show();
        if(position >= 1){
            carpark_layout.setVisibility(View.GONE);
            image_layout.setVisibility(View.VISIBLE);
            if(position == 1 || position == 3){
                String imgURL = "http://13.229.0.90/report_parking/a1.png";
                new DownLoadImageTask().execute(imgURL);
            }else if(position == 2){
                String imgURL = "http://13.229.0.90/report_parking/b1.png";
                new DownLoadImageTask().execute(imgURL);
            }
        }else if(position == 0 ){
            carpark_layout.setVisibility(View.VISIBLE);
            image_layout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()){
            case R.id.top_4:
                i.setClass(this, AExit.class);
            break;
            case R.id.bottom_2:
                i.setClass(this, BExit.class);
            break;
        }
        startActivity(i);
    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            img.setImageBitmap(result);
        }
    }
}
