package com.log2c.cnbetaone.helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.log2c.cnbetaone.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片下载
 */
public class DownLoadImageHelper implements Runnable {
    private String url;
    private Context context;
    private ImageDownLoadCallBack callBack;
    private File currentFile;

    public DownLoadImageHelper(Context context, String url, ImageDownLoadCallBack callBack) {
        this.url = url;
        this.callBack = callBack;
        this.context = context;
    }

    @Override
    public void run() {
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .submit().get();
            if (bitmap != null) {
                saveImageToGallery(context, bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bitmap != null && currentFile.exists()) {
                callBack.onDownLoadSuccess(bitmap);
            } else {
                callBack.onDownLoadFailed();
            }
        }
    }

    public void saveImageToGallery(Context context, Bitmap bmp) {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();
        File appDir = new File(file, Constants.FILE_NAME_PHOTO);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        currentFile = new File(appDir, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(currentFile.getPath()))));
    }

    public interface ImageDownLoadCallBack {

        void onDownLoadSuccess(Bitmap bitmap);

        void onDownLoadFailed();
    }
}
