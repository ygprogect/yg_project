package com.example.yg;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter2 extends PagerAdapter {

    Context context;

    int images2[] = {

            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4

    };

    int headings2[] = {
            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three,
            R.string.heading_fourth

    };

    int description2[] = {
            R.string.desc_one,
            R.string.desc_two,
            R.string.desc_three,
            R.string.desc_fourth

    };

    public ViewPagerAdapter2(Context context){

        this.context = context;

    }

    @Override
    public int getCount() {
        return  headings2.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.instruction_slider,container,false);

        ImageView slidetitleimage = (ImageView) view.findViewById(R.id.title_image2);
        TextView slideHeading = (TextView) view.findViewById(R.id.text_title2);
        TextView slideDesciption = (TextView) view.findViewById(R.id.text_deccription2);

        slidetitleimage.setImageResource(images2[position]);
        slideHeading.setText(headings2[position]);
        slideDesciption.setText(description2[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout)object);

    }
}
