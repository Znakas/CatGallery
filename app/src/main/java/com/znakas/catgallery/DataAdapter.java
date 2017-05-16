package com.znakas.catgallery;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by andriy on 5/16/17.
 */

public class DataAdapter extends ArrayAdapter<String> {

    private static final String[] mContacts = { "Red", "Hat", "Bro", "Myrka", "DamnCat"};
    Context mContext;

    public DataAdapter(@NonNull Context context, @LayoutRes int textViewRecourseid) {
        super(context, textViewRecourseid, mContacts);
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) convertView;
        if (convertView == null){
            convertView = new TextView(mContext);
            label = (TextView) convertView;
        }
        label.setText(mContacts[position]);
        return convertView;
    }
    public String getItem(int position){
        return mContacts[position];
    }
}
