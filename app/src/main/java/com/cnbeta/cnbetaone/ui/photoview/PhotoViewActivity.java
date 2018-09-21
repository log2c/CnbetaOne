package com.cnbeta.cnbetaone.ui.photoview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.cnbeta.cnbetaone.R;

public class PhotoViewActivity extends AppCompatActivity {
    public static final String TAG = PhotoViewActivity.class.getSimpleName();
    public static final String FLAG_IMG_LIST = "img_list";
    public static final String FLAG_IMG_POSITION = "img_position";

    private ViewPager mViewPager;
    private String[] mImgList;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photo_view);
        Intent intent = getIntent();
        mImgList = intent.getStringArrayExtra(FLAG_IMG_LIST);
        mPosition = intent.getIntExtra(FLAG_IMG_POSITION, 0);
        if (mImgList.length == 0 || mPosition < 0) {
            Toast.makeText(getApplicationContext(), R.string.parameter_error, Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mImgList.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(PhotoViewActivity.this);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Glide.with(PhotoViewActivity.this).load(mImgList[position]).into(view);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        mViewPager.setCurrentItem(mPosition);

    }
}
