package com.aditya.meditrack.uicomponents;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TabWidget;

import com.aditya.meditrack.R;

import java.util.ArrayList;
import java.util.List;

public class PagerTabWidget extends TabWidget implements View.OnClickListener, ViewPager.OnPageChangeListener {

    protected ViewPager mViewPager;
    protected final List<View> tabViews = new ArrayList<View>();
    protected Context mContext;
    protected OnTabSelectedListener mOnTabSelectedListener;

    public void setmOnTabSelectedListener(OnTabSelectedListener mOnTabSelectedListener) {
        this.mOnTabSelectedListener = mOnTabSelectedListener;
    }

    public PagerTabWidget(Context context) {
        super(context);
        init(context);
    }

    public PagerTabWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PagerTabWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context) {
        this.mContext = context;
        //tabViews = new ArrayList<View>();
    }


    public void setDividerInvisible() {
        setDividerDrawable(R.color.black);
    }

    public ViewPager getmViewPager() {
        return mViewPager;
    }

    public void setmViewPager(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
        this.mViewPager.setOnPageChangeListener(this);
        if (tabViews.size() > 0) setCurrentTab(0);
    }

    public void addTab(View view) {
        tabViews.add(view);
        addView(view);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mViewPager != null) {
            mViewPager.setCurrentItem(tabViews.indexOf(v));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        setCurrentTab(position);
        mOnTabSelectedListener.onSelected(tabViews, position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static interface OnTabSelectedListener {

        void onSelected(List<View> tabViews, int position);

    }

}
