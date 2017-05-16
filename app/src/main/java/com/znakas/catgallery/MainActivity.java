package com.znakas.catgallery;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.znakas.catgallery.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private TextView mSelectedText;
    private DataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.mSelectedText = binding.mSelectedText;
        mAdapter = new DataAdapter(this, android.R.layout.simple_list_item_1);
        binding.myGridView.setAdapter(mAdapter);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
