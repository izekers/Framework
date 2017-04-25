package com.zoker.ui.widget.BottomNavigation;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.zoker.ui.R;

/**
 * Created by Administrator on 2017/4/24.
 */

public class BottomNavigation extends TabLayout implements TabLayout.OnTabSelectedListener{
    ViewPager mViewPager;

    public BottomNavigation(Context context) {
        super(context);
    }

    public BottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//
//    public void init(){
//        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
//        initTabList();
//        mViewPager.setCurrentItem(0);
//        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置TabLayout的模式
//        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
//            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
//        }
//        mTabLayout.addOnTabSelectedListener(this);//设置TabLayout的选中监听
//    }
//
//
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
//        setTabSelectedState(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
//        setTabUnSelectedState(tab);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
//
//    private void setTabSelectedState(TabLayout.Tab tab) {
//        View customView = tab.getCustomView();
//        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
//        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
//        tabText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
//        String s = tabText.getText().toString();
//        if (getString(R.string.item_home).equals(s)) {
//            tabIcon.setImageResource(R.drawable.home_fill);
//        } else if (getString(R.string.item_location).equals(s)) {
//            tabIcon.setImageResource(R.drawable.location_fill);
//        } else if (getString(R.string.item_like).equals(s)) {
//            tabIcon.setImageResource(R.drawable.like_fill);
//        } else if (getString(R.string.item_person).equals(s)) {
//            tabIcon.setImageResource(R.drawable.person_fill);
//        }
//    }
//
//    private void setTabUnSelectedState(TabLayout.Tab tab) {
//        View customView = tab.getCustomView();
//        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
//        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
//        tabText.setTextColor(ContextCompat.getColor(getActivity(), R.color.black_1));
//        String s = tabText.getText().toString();
//        if (getString(R.string.item_home).equals(s)) {
//            tabIcon.setImageResource(R.drawable.home);
//        } else if (getString(R.string.item_location).equals(s)) {
//            tabIcon.setImageResource(R.drawable.location);
//        } else if (getString(R.string.item_like).equals(s)) {
//            tabIcon.setImageResource(R.drawable.like);
//        } else if (getString(R.string.item_person).equals(s)) {
//            tabIcon.setImageResource(R.drawable.person);
//        }
//    }
}
