package com.lch.carparking.element;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.lch.carparking.R;
import com.lch.carparking.dao.CarparkDao;
import com.lch.carparking.dao.CarparkDaoImpl;
import com.lch.carparking.network.ResponseModel;
import com.lch.carparking.structure.CarPaking.CarParkActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QRActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST_CAMERA = 1011;
    @BindView(R.id.resultTextView)
    TextView resultTextView;

    CarparkDao carparkDao;
    String arrvial_json = "";
    String leave_json = "";
    ViewGroup viewGroup ;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        Log.w("asd","requestCode : " + requestCode);
        Log.w("asd","resultCode : " + resultCode);
        Log.w("asd","data : " + data);
        ResponseModel responseModel = new ResponseModel();
        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 100:
                    responseModel = carparkDao.p_a(arrvial_json);
                break;
                case 200:
                    responseModel = carparkDao.request_leave(leave_json);
                    Toast.makeText(this,"已付HKD50",Toast.LENGTH_SHORT).show();
                break;
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        Intent intent = new Intent(this, CarParkActivity.class);
        startActivity(intent);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        ButterKnife.bind(this, this);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                arrvial_json = extras.getString("pre_arrival");
                leave_json = extras.getString("pre_leave");
            }
        }
        carparkDao = new CarparkDaoImpl(this);
        setup_qr_scanner();


    }

    public void setup_qr_scanner(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a barcode");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(true);
        if(!TextUtils.isEmpty(arrvial_json)){
            integrator.setRequestCode(100);
        }else if(!TextUtils.isEmpty(leave_json)){
            integrator.setRequestCode(200);
        }
        integrator.initiateScan();
    }
}