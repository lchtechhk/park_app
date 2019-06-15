package com.lch.carparking.structure.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
public class RegisterActivity extends AppCompatActivity implements OnClickListener{


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // Set up the login form.
        myPreferences = new MyPreferences(this,"login_information");
        usersDao = new UsersDaoImpl(this);
        btn_sign_in.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_in:
                edit_username.setError(null);
                edit_password.setError(null);
                View focusView = null;
                boolean cancel = false;
                String user_name = ObjectUtils.isNotNullEmpty(edit_username.getText().toString()) ? edit_username.getText().toString().toUpperCase() : "";
                String password = ObjectUtils.isNotNullEmpty(edit_password.getText().toString()) ? edit_password.getText().toString().toUpperCase() : "";
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
                    Toast.makeText(this,user_name + " -- " + password, Toast.LENGTH_SHORT).show();
                    RequestModel requestModel = new RequestModel();
                    requestModel.setLicense(user_name);
                    requestModel.setHkid(password);
                    String json = GsonUtils.modelToJson(requestModel);
                    ResponseModel responseModel = usersDao.save(json);
                    if(responseModel.isMessage_status()){
                        myPreferences.setPreferences_loginInformation(user_name,password);
                        Intent i = new Intent(this,MainActivity.class);
                        startActivity(i);
                    }
                    Toast.makeText(this,responseModel.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_back:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            break;
        }
    }
}

