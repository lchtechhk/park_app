package com.lch.carparking.network;

import android.os.AsyncTask;

import com.lch.carparking.utils.AsyncMessage;
import com.lch.carparking.utils.CommonFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by James on 21/1/2017.
 */

public class HttpPostAsync extends AsyncTask<String,Integer,String> {

    private NetworkModel networkModel;

    public HttpPostAsync(NetworkModel networkModel) {
        this.networkModel = networkModel;
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {
        boolean a = CommonFactory.isNetworkConnection(networkModel.getAppCompatActivity());
        if(!a){
            return AsyncMessage.error_network;
        }
        String url = params[0];
        String json = params[1];
        String result="";
        try {
//            publishProgress(25);
            result = post(url,json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {

    }

    @Override
    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
//        if(ObjectUtils.isNotNullEmpty(s) && s.equals(AsyncMessage.error_network)){
//            AppCompatActivity appCompatActivity = (AppCompatActivity)networkModel.getAppCompatActivity();
//            View view = CommonFactory.updateView(appCompatActivity.getLayoutInflater().inflate(R.layout.crouton_custom_view, null), networkModel.getAppCompatActivity().getString(R.string.label_bad_network_connection), ContextCompat.getDrawable(networkModel.getAppCompatActivity(), R.drawable.wifi_scan_black));
//            Crouton.make(appCompatActivity, view).show();
//        }
//        ClassicDialog.dismiss();
    }

    public String post(String url, String json) throws IOException {
        client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
