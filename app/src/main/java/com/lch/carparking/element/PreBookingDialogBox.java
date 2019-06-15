package com.lch.carparking.element;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lch.carparking.R;
import com.lch.carparking.dao.UsersDao;
import com.lch.carparking.dao.UsersDaoImpl;
import com.lch.carparking.network.RequestModel;
import com.lch.carparking.network.ResponseModel;
import com.lch.carparking.sharePreferences.MyPreferences;
import com.lch.carparking.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreBookingDialogBox extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.license)
    EditText edit_license;

    @BindView(R.id.hkid)
    EditText edit_hkid;

    @BindView(R.id.back)
    Button back;

    @BindView(R.id.go)
    Button go;

    UsersDao usersDao;

    MyPreferences myPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.license_hkid, container);
        ButterKnife.bind(this, view);
        go.setOnClickListener(this);
        back.setOnClickListener(this);

        myPreferences = new MyPreferences(getActivity(),"login_information");
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        usersDao = new UsersDaoImpl(getActivity());

        String license = edit_license.getText().toString();
        String hkid = edit_hkid.getText().toString();

        if(TextUtils.isEmpty(license) && TextUtils.isEmpty(hkid) ){
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go:
                String license = edit_license.getText().toString();
                String hkid = edit_hkid.getText().toString();

                View focusView = null;
                boolean cancel = false;
                if (TextUtils.isEmpty(license) || TextUtils.isEmpty(hkid)) {
                    if(TextUtils.isEmpty(license)){
                        edit_license.setError("必需輸入");
                        focusView = edit_license;
                        cancel = true;
                    }
                    if(TextUtils.isEmpty(hkid)){
                        edit_hkid.setError("必需輸入");
                        focusView = edit_hkid;
                        cancel = true;
                    }
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {
                    RequestModel requestModel = new RequestModel();
                    requestModel.setLicense(license);
                    requestModel.setHkid(hkid);
                    String json = GsonUtils.modelToJson(requestModel);
                    ResponseModel responseModel = usersDao.register_login(json);
                    if(responseModel.isMessage_status()){
                        myPreferences.setPreferences_loginInformation(responseModel.getUser_data());
                        this.dismiss();
                    }
                    Toast.makeText(getActivity(),responseModel.getMessage(),Toast.LENGTH_SHORT).show();
                }
                getActivity().recreate();
            break;

            case R.id.back :
                this.dismiss();
            break;
        }
    }
}
