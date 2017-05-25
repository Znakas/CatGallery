package com.znakas.catgallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.znakas.catgallery.api.AsyncRequest;
import java.util.ArrayList;

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
    private TextView tv_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = (Button) findViewById(R.id.btn_add);
        tv_status = (TextView) findViewById(R.id.tv_status);
        gvCats = (GridView) findViewById(R.id.gv_Cats);
        mAdapter = new ImageAdapter(this, arrImages);
        gvCats.setAdapter(mAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Load cat", Toast.LENGTH_LONG).show();
                //checkStatusNetworks();
                asyncRequest = new AsyncRequest(getApplicationContext(), tv_status, arrImages, mAdapter);
                asyncRequest.execute();

            }
        });
        gvCats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_LONG).show();
            }
        });
    }
}
