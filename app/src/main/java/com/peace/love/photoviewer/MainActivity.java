package com.peace.love.photoviewer;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.peace.love.photoviewer.customview.SpacesItemDecoration;
import com.peace.love.photoviewer.http.Api;
import com.peace.love.photoviewer.http.Retrofits;
import com.peace.love.photoviewer.model.PhotoData;
import com.peace.love.photoviewer.model.PhotoData.InfoBean.PhotoBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    SearchView mSearchView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView mRecyclerView;
    List<PhotoBean> mData;
    BaseQuickAdapter mAdapter;
    //this is test spot 1.
    String keyWord="こんにちは";
    //konnichiha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCustomActionBar();
        mSearchView = findViewById(R.id.sv);
        mSwipeRefreshLayout = findViewById(R.id.swr);
        mRecyclerView = findViewById(R.id.rv);
        //u are my heart
        //i must be ur heart
        initData();
        initListener();
        //test spot2
        httpRequest();   //Init photo
    }
    private void setCustomActionBar() {
        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View mActionBarView = LayoutInflater.from(this).inflate(R.layout.title, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(mActionBarView, lp);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    private void initData() {
        //finaltest
        mData = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<PhotoBean, BaseViewHolder>(R.layout.item_view, mData) {

            @Override
            protected void convert(BaseViewHolder helper, PhotoBean item) {
                //set title
                helper.setText(R.id.tv, item.getPhoto_title());
                //set img url into IamgeView
                Glide.with(MainActivity.this)
                        .load(item.getThumbnail_image_url())
                        .into((ImageView) helper.getView(R.id.riv));
            }
        };
        //set two span in one row
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //set 10dp gap in recyclerView's itemView
        final float scale = getResources().getDisplayMetrics().density;
        mRecyclerView.addItemDecoration(new SpacesItemDecoration((int) (10 * scale + 0.5f)));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //prevent the keyboard always show when activity resume
                mSearchView.clearFocus();
                hideKeyBoard(mSearchView);
                keyWord = query;
                httpRequest();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //swipe to refresh search data
        mSwipeRefreshLayout.setOnRefreshListener(this::httpRequest);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            PhotoBean photoBean = mData.get(position);
            //add the transition(view is shared element)
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                    Pair.create(view, "photoView"));
            Intent intent = new Intent(MainActivity.this,PhotoDetailActivity.class);
            intent.putExtra("photoBean",photoBean);
            startActivity(intent, options.toBundle());
        });
    }

    /**
     * httpRequest for keyword
     */
    private void httpRequest() {
        mData.clear();
        mSwipeRefreshLayout.setRefreshing(true);
        keyWord = "\"" + keyWord + "\"";
        Retrofits.get(Api.class)
                .getPhotoData(keyWord)
                //http in ioThread
                .subscribeOn(Schedulers.io())
                //update ui in mainThread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        
                    }

                    @Override
                    public void onNext(PhotoData photoData) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        if (photoData.getInfo().getPhoto_num() > 0) {
                            mData.addAll(photoData.getInfo().getPhoto());
                        }else {
                            //no data for keyword or return error code
                            Toast.makeText(MainActivity.this,"no data for " + keyWord,Toast.LENGTH_SHORT).show();
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * hide the keyboard if the click point is not on the editText
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v != null) {
                if (isShouldHideKeyboard(v, ev)) {
                    hideKeyBoard(v);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * hide the keyboard
     * @param v
     */
    private void hideKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();

            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }
}
