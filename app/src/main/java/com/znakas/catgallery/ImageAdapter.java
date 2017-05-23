package com.znakas.catgallery;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by andriy on 5/16/17.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
//    private int[] images;
    private ArrayList<ImageView> arrImages = new ArrayList<>();

    public ImageAdapter(Context context, ArrayList<ImageView> arrImages) {
        this.mContext = context;
        this.arrImages = arrImages;
    }

    @Override
    public int getCount() {
//        return images.length;
        return arrImages.size();
    }

    @Override
    public Object getItem(int position) {
        return arrImages.get(position);
//        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
//        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        imageView = new ImageView(mContext);
//        if (position+1 == images.length){
//            images[8] = R.drawable.add;
//            imageView.setImageResource(images.length+1);
//        }
//

        //resizer
        ImageResizer imgRes = new ImageResizer(mContext, arrImages.get(position));
//        imageView = imgRes.getSmallImage();

//        if (position + 1 == arrImages.size()) {
//            imageView.setImageResource(R.drawable.add);
//        }
//        imageView.setImageResource(arrImages.size());
//        imageView = imgRes.getSmallImage();
        imageView = arrImages.get(position);
        imageView.setPadding(1, 1, 1, 1);
        imageView.setScaleType(ImageView.ScaleType.FIT_START);
        imageView.setLayoutParams(new GridView.LayoutParams(imgRes.getScreenSize(), imgRes.getScreenSize() / 2));

        return imageView;
    }
}

//    private int getWidth(){
//        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        return size.x/2;
//    }
