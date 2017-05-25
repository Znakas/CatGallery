package com.znakas.catgallery.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.znakas.catgallery.ImageAdapter;
import com.znakas.catgallery.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by andriy on 5/22/17.
 */

public class AsyncRequest extends AsyncTask<String, Integer, ArrayList<Bitmap>> {
    private Context context;
    private TextView tv_status;
    private ArrayList<ImageView> arrImages;
    private ImageAdapter mAdapter;
    private Cat mCat;
    private ArrayList<Bitmap> arrBitmaps = new ArrayList<>();

    public AsyncRequest(Context context, TextView tv_status, ArrayList<ImageView> arrImages,
                        ImageAdapter mAdapter) {
        this.context = context;
        this.tv_status = tv_status;
        this.arrImages = arrImages;
        this.mAdapter = mAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tv_status.setText("onPreExecute");
        arrImages.clear();
        Log.d(Constants.MY_LOG, "#onPreExecute...");
    }

    @Override
    protected ArrayList<Bitmap> doInBackground(String... params) {
        Log.d(Constants.MY_LOG, "#doInBackground");
        for (Cat cat : getArrCats()) {
            arrBitmaps.add(getBitmapFromURL(cat));
        }
        Log.d(Constants.MY_LOG, "#return bmps");
        return arrBitmaps;
    }

    private ArrayList<Cat> getArrCats() {
        String xmlDoc = "";
        try {
            URL url = new URL(Constants.BASE_QUERY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();

            Log.d("Log", "The response code is: " + code);
            if (code == 200) {
                Log.d(Constants.MY_LOG, "onProgressUpdate code == 200");
                xmlDoc = parseResponse(connection.getInputStream());
            } else {
                Log.d(Constants.MY_LOG, "onProgressUpdate code fail");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.d(Constants.MY_LOG, "onProgressUpdate code ex");
        }
        return getArrCatsFromString(xmlDoc);
    }

    private String parseResponse(InputStream in) {
        Log.d(Constants.MY_LOG, "parseResponse");
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.d("Log", "Ex parseResponse(InputStream in");
        }
        return sb.toString();
    }

    private ArrayList<Cat> getArrCatsFromString(String str) {
        int counter = 0;
        ArrayList<Cat> arrCats = new ArrayList<>();
        String url = "";
        String id = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(str));
            int evenType = parser.getEventType();
            while (evenType != XmlPullParser.END_DOCUMENT) {
                if (evenType == XmlPullParser.START_DOCUMENT) {

                    Log.d(Constants.MY_LOG, "Start doc");
                } else if (evenType == XmlPullParser.START_TAG) {
                    Log.d(Constants.MY_LOG, "Start tag: " + parser.getName());

                    if (parser.getName().equals("url")) {
                        url = parser.nextText();
                    }
                    if (parser.getName().equals("id")) {
                        id = parser.nextText();
                    }
                    if (!url.equals("") && !id.equals("")) {
                        publishProgress(++counter); //count
                        Log.d("counter", "counter " + counter);
                        mCat = new Cat(url, id);
                        arrCats.add(mCat);
                        url = "";
                        id = "";
                    }
                }
                Log.d(Constants.MY_LOG, "size:" + arrCats.size());
                evenType = parser.next();
            }
            Log.d(Constants.MY_LOG, "End document");
            Log.d(Constants.MY_LOG, str);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrCats;
    }

    private Bitmap getBitmapFromURL(Cat cat) {
        Bitmap bmp = null;
        try {

            URL ulr = new URL(cat.getUrl());
            HttpURLConnection con = (HttpURLConnection) ulr.openConnection();
            InputStream is = con.getInputStream();
            bmp = BitmapFactory.decodeStream(is);

            if (null != bmp)
                return bmp;


        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_not_available);
        } catch (Exception e) {
            Log.d(Constants.MY_LOG, "#Exception " + e);
        }
        Log.d(Constants.MY_LOG, "#return bmp");
        return bmp;
    }

    protected void onProgressUpdate(Integer... progress) {
        tv_status.setText("Cats loaded: " + progress[0]);
        Log.d(Constants.MY_LOG, "onProgressUpdate " + progress[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<Bitmap> arrBitmaps) {
        super.onPostExecute(arrBitmaps);
        tv_status.setText("onPostExecute");
        Log.d(Constants.MY_LOG, "#Executed");
        for (Bitmap mBitmap : arrBitmaps) {
            ImageView imageView = new ImageView(context);
            imageView.setImageBitmap(mBitmap);
            arrImages.add(imageView);
            mAdapter.notifyDataSetChanged();
        }

        Log.d(Constants.MY_LOG, "#onPostExecute");
    }
}
