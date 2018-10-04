package com.log2c.cnbetaone.ui.photoview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.log2c.cnbetaone.App;
import com.log2c.cnbetaone.R;
import com.log2c.cnbetaone.helper.DownLoadImageHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class PhotoViewActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = PhotoViewActivity.class.getSimpleName();
    public static final String FLAG_IMG_LIST = "img_list";
    public static final String FLAG_IMG_POSITION = "img_position";
    private static final int REQUEST_EXTERNAL_STORAGE = 0x01;

    private ViewPager mViewPager;
    private String[] mImgList;
    private int mPosition;
    private TextView mPhotoCountTV;
    private ImageView mSaveIV;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_photo_view);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        Intent intent = getIntent();
        mImgList = intent.getStringArrayExtra(FLAG_IMG_LIST);
        mPosition = intent.getIntExtra(FLAG_IMG_POSITION, 0);
        if (mImgList.length == 0 || mPosition < 0) {
            Toast.makeText(getApplicationContext(), R.string.parameter_error, Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        mPhotoCountTV = findViewById(R.id.tv_photo_count);
        mSaveIV = findViewById(R.id.iv_save);
        mSaveIV.setOnClickListener(this);
        setupTextView(mPhotoCountTV, mPosition, mImgList.length);

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
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setupTextView(mPhotoCountTV, position, mImgList.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mHandler = new AnyHandler();
    }

    private static void setupTextView(TextView textView, int position, int total) {
        position++;
        SpannableString spannableString = new SpannableString(position + "/" + total);
        spannableString.setSpan(new RelativeSizeSpan(1.6f), 0, String.valueOf(position).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //根据请求是否通过的返回码进行判断，然后进一步运行程序
        if (grantResults.length > 0 && requestCode == REQUEST_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            savePic();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_save:
                savePic();
                break;
        }
    }

    /**
     * 保存当前相片到相册
     */
    private void savePic() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
            return;
        }

        new Thread(new DownLoadImageHelper(getApplicationContext(), mImgList[mPosition], new DownLoadImageHelper.ImageDownLoadCallBack() {
            @Override
            public void onDownLoadSuccess(Bitmap bitmap) {
                mHandler.sendEmptyMessage(AnyHandler.WHAT_SUCCESS);
            }

            @Override
            public void onDownLoadFailed() {
                mHandler.sendEmptyMessage(AnyHandler.WHAT_FAILED);
            }
        })).start();
    }

    private static class AnyHandler extends Handler {
        public static final int WHAT_SUCCESS = 1;
        public static final int WHAT_FAILED = 2;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == WHAT_SUCCESS) {
                Toast.makeText(App.getApp(), App.getApp().getText(R.string.download_success), Toast.LENGTH_LONG).show();
            } else if (msg.what == WHAT_FAILED) {
                Toast.makeText(App.getApp(), App.getApp().getText(R.string.download_failed), Toast.LENGTH_LONG).show();
            }
        }
    }
}
