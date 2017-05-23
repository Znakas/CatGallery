package com.znakas.catgallery;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.znakas.catgallery.api.AsyncRequest;
import com.znakas.catgallery.api.Cat;

import java.util.ArrayList;

//import com.znakas.catgallery.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ImageAdapter mAdapter;
    private GridView gvCats;
    private int[] images = {
            R.drawable.sb_unlocked_video_1,
            R.drawable.sb_unlocked_video_2,
            R.drawable.sb_unlocked_video_3,
            R.drawable.sb_unlocked_video_4,
            R.drawable.sb_unlocked_video_5,
            R.drawable.sb_unlocked_video_6,
            R.drawable.sb_unlocked_video_7,
            R.drawable.sb_unlocked_video_8
    };
    private ArrayList<ImageView> arrImages = new ArrayList<>();
    private Button btn_add;
    private AsyncRequest asyncRequest;
    private int progress;
    private TextView tv_status;
//    private ImageView im_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = (Button) findViewById(R.id.btn_add);
        tv_status = (TextView) findViewById(R.id.tv_status);
        gvCats = (GridView) findViewById(R.id.gv_Cats);
//        im_image = (ImageView) findViewById(R.id.im_image);
        mAdapter = new ImageAdapter(this, getArrImages());
        gvCats.setAdapter(mAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Load cat", Toast.LENGTH_LONG).show();
                //checkStatusNetworks();
                asyncRequest = new AsyncRequest(getApplicationContext(), tv_status, progress, arrImages, mAdapter, gvCats);
                asyncRequest.execute();
//                gvCats.setAdapter(mAdapter);

            }
        });
        gvCats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList<ImageView> getArrImages() {
//        for (int i = 0; i < images.length; i++) {
//            ImageView arrImg = new ImageView(getApplicationContext());
//            arrImg.setImageResource(images[i]);
//            arrImages.add(arrImg);
//            Log.d("MyLog", arrImages.size() + "");
//
//        }
//        if (arrImages != null) {
//            arrImages.add(arrImages);
//        }
        return arrImages;
    }
}
