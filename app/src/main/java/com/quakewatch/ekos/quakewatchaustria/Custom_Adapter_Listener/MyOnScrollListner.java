package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.support.v7.app.ActionBar;
import android.widget.AbsListView;

/**
 * Created by pkogler on 24.10.2015.
 * Usage:   This Listiner can be used
 * to controll the scroll behavior from listViews
 */
public class MyOnScrollListner implements AbsListView.OnScrollListener {

    ActionBar mActionBar;

    /**
     * @param mActionBar
     */
    public MyOnScrollListner(ActionBar mActionBar) {
        this.mActionBar = mActionBar;
    }

    /**
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (view.getFirstVisiblePosition() >= 1 && mActionBar.isShowing()) {
            mActionBar.hide();
        } else if (view.getFirstVisiblePosition() == 0 && !mActionBar.isShowing()) {
            mActionBar.show();
        }
    }

    /**
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
