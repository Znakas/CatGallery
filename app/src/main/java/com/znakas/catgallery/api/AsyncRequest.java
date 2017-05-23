package com.znakas.catgallery.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.znakas.catgallery.ImageAdapter;
import com.znakas.catgallery.MainActivity;
import com.znakas.catgallery.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by andriy on 5/22/17.
 */

public class AsyncRequest extends AsyncTask<String, Integer, Bitmap> {
    private Context context;
    private TextView tv_status;
    private int mProgress;
//    private ImageView im_image;
    private ArrayList<ImageView> arrImages;
    private ImageAdapter mAdapter;
    GridView gvCats;

    public AsyncRequest(Context context, TextView tv_status, int progress, ArrayList<ImageView> arrImages, ImageAdapter mAdapter,
    GridView gvCats) {
        this.context = context;
        this.tv_status = tv_status;
        this.mProgress = progress;
        this.arrImages = arrImages;
        this.mAdapter = mAdapter;
        this.gvCats = gvCats;
    }

//
//    public AsyncRequest typeRequest(final TypeRequest type) {
//        return this;
//    }
//
//    public AsyncRequest addParam(final String key, final Object value) {
//        return this;
//    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tv_status.setText("onPreExecute");
//        arrImages.clear();

        Log.d(Constants.MY_LOG, "#onPreExecute...");
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Log.d(Constants.MY_LOG, "#doInBackground");

//            URL url = new URL(Constants.BASE_URL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setReadTimeout(10000);
//            connection.setConnectTimeout(15000);
//            connection.setRequestMethod("GET");
//            connection.connect();
//
//            int code = connection.getResponseCode();
//
//            Log.d(Constants.MY_LOG, "#The response code is " + code);
//
//            if (code == 200) {
//                Log.d(Constants.MY_LOG, "#Connected");
//            } else {
//                Log.d(Constants.MY_LOG, "Fail");
//            }

        Bitmap bmp = null;
        try {
            int counter = 0;
            URL ulrn = new URL(Constants.BASE_URL);
            HttpURLConnection con = (HttpURLConnection) ulrn.openConnection();
            InputStream is = con.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
            publishProgress(++counter); //count
            if (null != bmp)
                return bmp;

        } catch (Exception e) {
            Log.d(Constants.MY_LOG, "#Exception " + e);
        }
        Log.d(Constants.MY_LOG, "#return bmp");

        return bmp;
    }

    protected void onProgressUpdate(Integer... progress) {
        tv_status.setText("Cats loaded: " + progress[0]);

//        setProgressPercent(progress[0]);
        Log.d(Constants.MY_LOG, "onProgressUpdate " + progress[0]);
    }

    @Override
    protected void onPostExecute(Bitmap cat) {
        super.onPostExecute(cat);
        tv_status.setText("onPostExecute");
        Log.d(Constants.MY_LOG, "#Executed");
//        im_image = new ImageView(context);
//        im_image.setImageBitmap(cat);
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(cat);
        arrImages.add(imageView);
        mAdapter = new ImageAdapter(context, arrImages);
        gvCats.setAdapter(mAdapter);
        Log.d(Constants.MY_LOG, "#onPostExecute");
    }
}
