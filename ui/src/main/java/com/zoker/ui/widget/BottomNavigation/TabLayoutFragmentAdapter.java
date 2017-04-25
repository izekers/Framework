package com.zoker.ui.widget.BottomNavigation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zoker.ui.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class TabLayoutFragmentAdapter extends FragmentPagerAdapter {
    private List<String> mTabList;
    private Context mContext;
    private List<Fragment> mFragments;
    private ImageView mTabIcon;
    private TextView mTabText;
    private int[] mTabImgs;
    private LinearLayout mTabView;

    public TabLayoutFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabLayoutFragmentAdapter(FragmentManager fm, List<String> tabList, Context context, List<Fragment> fragments, int[] tabImgs) {
        super(fm);
        mTabList = tabList;
        mContext = context;
        mFragments = fragments;
        mTabImgs = tabImgs;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }
}
