package com.peace.love.photoviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.peace.love.photoviewer.customview.photoview.PhotoView;
import com.peace.love.photoviewer.model.PhotoData.InfoBean.PhotoBean;

/**
 * @author CBF
 *         2018/3/4 14:39
 */

public class PhotoDetailActivity extends AppCompatActivity {

    PhotoView mPhotoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_detail);
        mPhotoView = findViewById(R.id.pv);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //mPhotoView is shared element
        ViewCompat.setTransitionName(mPhotoView, "photoView");

        Intent intent = getIntent();
        PhotoBean photoBean = intent.getParcelableExtra("photoBean");

        float ratio = (float) photoBean.getOriginal_height() / (float) photoBean.getOriginal_width();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        Glide.with(this)
                .load(photoBean.getOriginal_image_url())
                .override(width, (int) (width * ratio))
                .crossFade()
                .into(mPhotoView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
