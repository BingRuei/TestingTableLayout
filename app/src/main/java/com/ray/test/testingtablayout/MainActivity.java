package com.ray.test.testingtablayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    private android.support.design.widget.TabLayout mTabs;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabs = (android.support.design.widget.TabLayout) findViewById(R.id.tabs);

        for(int i=0;i<10;i++)
            mTabs.addTab(mTabs.newTab().setText("Tab "+(i+1)));

        // The title of the page is black, when it is selected, else be gray
        mTabs.setTabTextColors(Color.GRAY, Color.BLACK);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));

        // Setting the default Tab
        mViewPager.setCurrentItem(2);

        // Making Tabs slipped, if length of tabs longer than the length of screen
        mTabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        // The page show correct page, when the user click the Tab
        mTabs.setupWithViewPager(mViewPager);
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // Setting maximum of Tabs
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Setting title name of the Tab
            return "Item " + (position + 1);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // Setting your content of page on the ViewPager
            View view = getLayoutInflater().inflate(R.layout.pager_item,
                    container, false);
            container.addView(view);
            TextView title = (TextView) view.findViewById(R.id.item_title);
            title.setText(String.valueOf(position + 1));
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

}