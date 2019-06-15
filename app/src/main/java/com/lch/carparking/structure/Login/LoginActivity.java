package com.lch.carparking.structure.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lch.carparking.MainActivity;
import com.lch.carparking.R;
import com.lch.carparking.dao.UsersDao;
import com.lch.carparking.dao.UsersDaoImpl;
import com.lch.carparking.network.RequestModel;
import com.lch.carparking.network.ResponseModel;
import com.lch.carparking.sharePreferences.MyPreferences;
import com.lch.carparking.utils.GsonUtils;
import com.lch.carparking.utils.ObjectUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener{


    @BindView(R.id.username)
    EditText edit_username;
    @BindView(R.id.password)
    EditText edit_password;
    @BindView(R.id.btn_sign_in)
    Button btn_sign_in;
    @BindView(R.id.btn_back)
    Button btn_back;

    UsersDao usersDao;
    MyPreferences myPreferences;

    String movementRecord_str = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        myPreferences = new MyPreferences(this,"login_information");
        usersDao = new UsersDaoImpl(this);
        ButterKnife.bind(this);
        btn_sign_in.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        edit_username.setText(myPreferences.getPreferences_loginInformation().get("username"));
        edit_password.setText(myPreferences.getPreferences_loginInformation().get("password"));

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                movementRecord_str = null;
            } else {
                movementRecord_str = extras.getString("targetPage");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_in:
                String user_name = ObjectUtils.isNotNullEmpty(edit_username.getText().toString()) ? edit_username.getText().toString().toUpperCase() : "";
                String password = ObjectUtils.isNotNullEmpty(edit_password.getText().toString()) ? edit_password.getText().toString().toUpperCase() : "";
                View focusView = null;
                boolean cancel = false;
                if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(password)) {
                    if(TextUtils.isEmpty(user_name)){
                        edit_username.setError("必需輸入");
                        focusView = edit_username;
                        cancel = true;
                    }
                    if(TextUtils.isEmpty(password)){
                        edit_password.setError("必需輸入");
                        focusView = edit_password;
                        cancel = true;
                    }
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {
                    RequestModel requestModel = new RequestModel();
                    requestModel.setLicense(user_name);
                    requestModel.setHkid(password);
                    String json = GsonUtils.modelToJson(requestModel);
                    ResponseModel responseModel = usersDao.login(json);
                    if(responseModel.isMessage_status()){
                        myPreferences.setPreferences_loginInformation(responseModel.getUser_data());
                        Intent intent = new Intent(this,MainActivity.class);
//                        if("CarParkActivity".equals(movementRecord_str)){
//                             i = new Intent(this, CarParkActivity.class);
//                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    Toast.makeText(this,responseModel.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_back:
                Intent intent = new Intent(this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            break;
        }
    }
}

