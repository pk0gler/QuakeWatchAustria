package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.support.v7.app.ActionBar;
import android.widget.AbsListView;

/**
 * Created by pkogler on 24.10.2015.
 */
public class MyOnScrollListner implements AbsListView.OnScrollListener {

    ActionBar mActionBar;

    public MyOnScrollListner(ActionBar mActionBar) {
        this.mActionBar = mActionBar;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //float y = listView.getChildAt(0).getY();
        if (view.getFirstVisiblePosition()>=1 && mActionBar.isShowing()) {
            mActionBar.hide();
        } else if ( view.getFirstVisiblePosition()==0 && !mActionBar.isShowing()) {
            mActionBar.show();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
