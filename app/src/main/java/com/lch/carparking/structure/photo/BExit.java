package com.lch.carparking.structure.photo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.lch.carparking.R;

import java.io.InputStream;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BExit extends AppCompatActivity {
    @BindView(R.id.b_photo)
    ImageView b_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bexit);
        ButterKnife.bind(this, this);
        try {
            String imgURL = "http://13.229.0.90/report_parking/exit_a_1.jpg";
            new DownLoadImageTask().execute(imgURL);
//            b_photo.setImageBitmap(getBitmapFromURL(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            b_photo.setImageBitmap(result);
        }
    }
}
