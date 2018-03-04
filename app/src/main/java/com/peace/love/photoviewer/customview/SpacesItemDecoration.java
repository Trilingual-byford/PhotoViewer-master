package com.peace.love.photoviewer.customview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ProjectName:YiSai
 * Date:2017/9/5 19:19
 * Created by CBF
 *
 * gap in recyclerview itemview
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = space;
        outRect.left = space;
        outRect.bottom = space;

        int position = parent.getChildLayoutPosition(view);
        if (position % 2 == 0) {
            outRect.right = space / 2;
        } else {
            outRect.left = space / 2;
        }
        if (position == 0 || position == 1) {
            outRect.top = space;
        }
    }
}
