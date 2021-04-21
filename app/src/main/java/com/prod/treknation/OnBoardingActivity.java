package com.prod.treknation;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.rd.PageIndicatorView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class OnBoardingActivity extends AppCompatActivity implements OnBoardingFragment.OnBoardingListener {

    private TNViewPager viewPager;
    private ViewPagerAdapter myViewPagerAdapter;
    private PageIndicatorView pagerIndicator;
    private FrameLayout flContainer;
    private RelativeLayout rlLayout;
    public Users mOnBoarding;
//    private int[] layouts;

    private final List<Fragment> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }

        setContentView(R.layout.activity_onboarding);

        viewPager = (TNViewPager) findViewById(R.id.view_pager);
        pagerIndicator = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
        rlLayout = (RelativeLayout) findViewById(R.id.rlIntroSliding);
        flContainer = (FrameLayout) findViewById(R.id.flContainer);

        mOnBoarding = new Users();
        mOnBoarding.setSource("ANDROID");
        mOnBoarding.setVersion("V 1.0");

        for (int i=0;i<5;i++) {
            mList.add(new OnBoardingFragment(i).attachOnBoardListener(this));
        }

        pagerIndicator.setSelection(0);
//        changeStatusBarColor();

        myViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext(), new DecelerateInterpolator());
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }

        viewPager.setPagingEnabled(false);
    }

    @Override
    public void onClick(int pageCount) {
        if (pageCount < 4) {
            viewPager.setCurrentItem(pageCount+1);
        } else {
            flContainer.setVisibility(View.VISIBLE);
            rlLayout.setVisibility(View.GONE);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flContainer, new OnBoardingFragment(5),OnBoardingFragment.class.getName())
                    .setCustomAnimations(android.R.anim.decelerate_interpolator, android.R.anim.decelerate_interpolator)
                    .commit();

        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

//    private void changeStatusBarColor() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
//    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            pagerIndicator.setSelection(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int i) {
            return mList.get(i);
        }
        @Override
        public int getCount() {
            return mList.size();
        }

    }

//    public class MyViewPagerAdapter extends PagerAdapter {
//        private LayoutInflater layoutInflater;
//
//        public MyViewPagerAdapter() {
//        }
//
////        @Override
////        public Object instantiateItem(ViewGroup container, int position) {
////            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////
////            View view = layoutInflater.inflate(layouts[position], container, false);
////            container.addView(view);
////
////            return view;
////        }
//
//        @Override
//        public int getCount() {
//            return layouts.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object obj) {
//            return view == obj;
//        }
//
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            View view = (View) object;
//            container.removeView(view);
//        }
//    }
}
