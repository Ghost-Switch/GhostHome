package com.example.ghosthome.ui.addroom.device.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ghosthome.R;

public class CustomPagerAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mLayouts = {R.layout.step_one_viewpage, R.layout.step_two_viewpage,R.layout.step_three_viewpage,R.layout.step_four_viewpage,R.layout.step_five_viewpage};
    private LinearLayout mDotLayout;
    private ImageView[] mDots;
    private String mText;
    public CustomPagerAdapter(Context context, LinearLayout dotLayout,String text) {
        mContext = context;
        mDotLayout = dotLayout;
        mText = text;
        setupDotIndicators();
    }

    private void setupDotIndicators() {
        mDots = new ImageView[mLayouts.length];
        for (int i = 0; i < mLayouts.length; i++) {
            mDots[i] = new ImageView(mContext);
            mDots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.dot_indicator_inactive));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            mDotLayout.addView(mDots[i], params);
        }
        // Set first dot as active
        if (mDots.length > 0) {
            mDots[0].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.dot_indicator_active));
        }
    }

    @Override
    public int getCount() {
        return mLayouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View layout = inflater.inflate(mLayouts[position], container, false);

        TextView textView = layout.findViewById(R.id.textView);
        Log.d("CustomPagerAdapter", "TextView: " + textView);
        if (textView != null) {
            textView.setText(mText);
        } else {
            Log.e("CustomPagerAdapter", "TextView is null");
        }

        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        // Update dot indicators
        for (int i = 0; i < mLayouts.length; i++) {
            mDots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.dot_indicator_inactive));
        }
        mDots[position].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.dot_indicator_active));
    }

    // Method to get the dot at a specific index
    public ImageView getDot(int position) {
        return mDots[position];
    }
}
