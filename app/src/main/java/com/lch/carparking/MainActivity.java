package com.lch.carparking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lch.carparking.core.model.MainModel;
import com.lch.carparking.core.presenter.MainPresenter;
import com.lch.carparking.core.view.MainView;
import com.lch.carparking.sharePreferences.MyPreferences;
import com.lch.carparking.structure.Login.LoginActivity;
import com.lch.carparking.structure.Map.MapFragment;
import com.lch.carparking.structure.Register.RegisterActivity;
import com.lch.carparking.utils.ObjectUtils;

import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MainView, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    Button navBtnHeader;
    Button navBtnHeader_2;
    TextView navLicense;
    TextView navHKID;
    TextView navId;
    //Register
    private MainPresenter mainPresenter;
    private Fragment fragment = null;

    //Permission
    private static final int INITIAL_REQUEST=1337;
    private static final int LOCATION_REQUEST=INITIAL_REQUEST+3;

    //
    View hearder;
    private MyPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Register
        mainPresenter = new MainPresenter(this,new MainModel());
        mainPresenter.onCreate();
        mainPresenter.onClickNav("Home");
        mainPresenter.setBtnHeader();

        if(mainPresenter.checkPermission()){
            fragment = new MapFragment();
            mainPresenter.showFagment(fragment);
        }
    }

    public void onResume(){
        super.onResume();
        mainPresenter.setBtnHeader();
//        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {
        Log.w("asd","v.getId :  " + v.getId());
        Log.w("asd","header_btn :  " + R.id.header_btn);
        switch (v.getId()) {
            case R.id.header_btn:
                String ButtonText = navBtnHeader.getText().toString();
                Log.w("asd","ButtonText :  " + "ButtonText");
  
                if("登入".equals(ButtonText)){
                    Intent i = new Intent(this, LoginActivity.class);
                    i.putExtra("targetPage","MainActivity");
                    startActivity(i);
                }else if("登出".equals(ButtonText)){
                    navBtnHeader_2.setVisibility(View.VISIBLE);
                    if( myPreferences.getPreferences_loginInformation() != null){
                        String username = myPreferences.getPreferences_loginInformation().get("username");
                        String password = myPreferences.getPreferences_loginInformation().get("password");
                        myPreferences.setPreferences_loginInformation(username,password);
                        Intent intent = new Intent();
                        intent.setClass(this, MainActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    Toast.makeText(this,"登出", Toast.LENGTH_SHORT).show();
                }
            break;
            case R.id.header_btn_2 :
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
            break;
        }
    }

    @Override
    public void setBtnHeader() {
        LinkedHashMap<String,String> userMap =  myPreferences.getPreferences_loginInformation();
        if(ObjectUtils.isNotNullEmpty(userMap.get("user_id"))){
            navId.setText(userMap.get("user_id"));
            navLicense.setText(userMap.get("username"));
            navHKID.setText(userMap.get("password"));
            navBtnHeader.setText("登出");
            navBtnHeader_2.setVisibility(View.GONE);
        }else {
            navBtnHeader.setText("登入");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode){
            case LOCATION_REQUEST :
                fragment = new MapFragment();
                mainPresenter.showFagment(fragment);
            break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            fragment = new MapFragment();
            mainPresenter.showFagment(fragment);
            mainPresenter.onClickNav("Home");
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean setPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.CAMERA},
                    LOCATION_REQUEST);
            return false;
        }

        return true;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hearder = navigationView.getHeaderView(0);
        navLicense  = (TextView) hearder.findViewById(R.id.navLicense);
        navHKID  = (TextView) hearder.findViewById(R.id.navHKID);
        navId = (TextView) hearder.findViewById(R.id.navId);
        navBtnHeader  = (Button) hearder.findViewById(R.id.header_btn);
        navBtnHeader_2  = (Button) hearder.findViewById(R.id.header_btn_2);
        navBtnHeader.setOnClickListener(this);
        navBtnHeader_2.setOnClickListener(this);
        myPreferences = new MyPreferences(this,"login_information");
    }

    @Override
    public void setNav() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setSupportActionBar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFagment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }
    }
}
