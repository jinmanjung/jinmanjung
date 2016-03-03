package com.drogsin.rsspotal.activity;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.drogsin.rsspotal.R;
import com.drogsin.rsspotal.adapter.MainPagerAdapter;
import com.drogsin.rsspotal.fragment.LiveScoreFragment1;
import com.drogsin.rsspotal.fragment.LiveScoreFragment2;
import com.drogsin.rsspotal.fragment.LiveScoreFragment3;
import com.drogsin.rsspotal.fragment.LiveScoreFragment4;
import com.drogsin.rsspotal.fragment.LiveScoreFragment5;
import com.drogsin.rsspotal.fragment.LiveScoreFragment6;
import com.drogsin.rsspotal.fragment.LiveScoreFragment7;
import com.drogsin.rsspotal.fragment.LiveScoreFragment8;
import com.drogsin.rsspotal.fragment.LiveScoreFragment9;
import com.drogsin.rsspotal.widget.ScrollTabHolder;
import com.flavienlaurent.notboringactionbar.AlphaForegroundColorSpan;
import com.flavienlaurent.notboringactionbar.KenBurnsSupportView;
import com.gc.materialdesign.utils.Utils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.melnykov.fab.FloatingActionButton;
import com.nineoldandroids.view.ViewHelper;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class MainActivity extends ActivityBase implements ScrollTabHolder, ViewPager.OnPageChangeListener {
    private final String TAG = "MainActivity";

    // 공통 component
    private static Toolbar toolbar;
//    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;
    private TextView actionTitleTv;
//    private TextView actionSubTitleTv;

    // 헤더 component
    public static AccelerateDecelerateInterpolator sSmoothInterpolator = new AccelerateDecelerateInterpolator();
    private KenBurnsSupportView mHeaderPicture;
    public static View mHeader;

    private SmartTabLayout mSmartTabLayout;
    public static ViewPager mViewPager;
    private MainPagerAdapter mPagerAdapter;

    private int mActionBarHeight;
    private int mMinHeaderHeight;
    private static int mHeaderHeight;
    public static int mMinHeaderTranslation;
    public static LinearLayout mHeaderLogo;

    private static RectF mRect1 = new RectF();
    private static RectF mRect2 = new RectF();

    private TypedValue mTypedValue = new TypedValue();
    private SpannableString mSpannableTitleString;
    private SpannableString mSpannableSubTitleString;
    private AlphaForegroundColorSpan mAlphaForegroundTitleColorSpan;
    private AlphaForegroundColorSpan mAlphaForegroundSubTitleColorSpan;

    // drawer
    // header
    private TextView nickNameTv;
    private TextView ballCountTv;
    private TextView loginTv;
    private ImageView loginArrowIv;
    private ImageView favorateArrowIv;
    private ImageView settingArrowIv;
    private ImageView simbolIv;

    // custom tab
    private TextView customTabTv;
    private int beforeTabPosition = 0;

    private boolean isPageSelected = false;
    private int pageScrollStateChangePosition = 0;

    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setController();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setController() {
        setActionBarView();
        setHeaderView();
        setMainView();
        setAdView();
    }

    private void setActionBarView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionTitleTv = (TextView) toolbar.findViewById(R.id.tv_title);
//        actionSubTitleTv = (TextView) findViewById(R.id.tv_subtitle);

        setSupportActionBar(toolbar);
//        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
//        dlDrawer.setDrawerListener(dtToggle);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setHeaderView() {
        mMinHeaderHeight = getResources().getDimensionPixelSize(R.dimen.min_header_height);
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mMinHeaderHeight + getActionBarHeight();

        mHeaderPicture = (KenBurnsSupportView) findViewById(R.id.header_picture);
        mHeaderPicture.setResourceIds(R.drawable.all_00, R.drawable.all_01);
        mHeaderLogo = (LinearLayout) findViewById(R.id.header_logo);
        mHeader = findViewById(R.id.header);
        simbolIv = (ImageView) findViewById(R.id.iv_simbol);
        ViewHelper.setAlpha(getActionBarIconView(), 0f);
    }

    private void setMainView() {
        mSmartTabLayout = (SmartTabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(9);

        mPagerAdapter = new MainPagerAdapter(this, getSupportFragmentManager());
        mPagerAdapter.setTabHolderScrollingContent(this);

        mViewPager.setAdapter(mPagerAdapter);
        mSmartTabLayout.setViewPager(mViewPager);
        mSmartTabLayout.setOnPageChangeListener(this);
        setCustomTabLayout();

        mSpannableTitleString = new SpannableString(getString(R.string.actionbar_title));
        mSpannableSubTitleString = new SpannableString(getString(R.string.actionbar_title));
        mAlphaForegroundTitleColorSpan = new AlphaForegroundColorSpan(0xffffffff);
        mAlphaForegroundSubTitleColorSpan = new AlphaForegroundColorSpan(0xffffffff);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
                    switch (mViewPager.getCurrentItem()) {
                        case 0 :
                            LiveScoreFragment1.newInstance().setRSSData();
                            break;
                        case 1 :
                            LiveScoreFragment2.newInstance().setRSSData();
                            break;
                        case 2 :
                            LiveScoreFragment3.newInstance().setRSSData();
                            break;
                        case 3 :
                            LiveScoreFragment4.newInstance().setRSSData();
                            break;
                        case 4 :
                            LiveScoreFragment5.newInstance().setRSSData();
                            break;
                        case 5 :
                            LiveScoreFragment6.newInstance().setRSSData();
                            break;
                        case 6 :
                            LiveScoreFragment7.newInstance().setRSSData();
                            break;
                        case 7 :
                            LiveScoreFragment8.newInstance().setRSSData();
                            break;
                        case 8 :
                            LiveScoreFragment9.newInstance().setRSSData();
                            break;

                    }
				}
			});
    }

    private void setAdView() {
        final AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.setVisibility(View.GONE);
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                mAdView.setVisibility(View.GONE);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mAdView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onPageScrollStateChanged(int position) {
        if (position == 1) {
            isPageSelected = true;
        }
        pageScrollStateChangePosition = position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // nothing
    }

    @Override
    public void onPageSelected(int position) {
        Handler handler = new Handler();
        switch (position) {
            case 0 :
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHeaderPicture.setResourceIds(R.drawable.all_00, R.drawable.all_01);
                    }
                }, 300);
                simbolIv.setImageResource(R.drawable.icon_all);
                break;
            case 1 :
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHeaderPicture.setResourceIds(R.drawable.entertain_00, R.drawable.entertain_01);
                    }
                }, 300);
                simbolIv.setImageResource(R.drawable.icon_entertain);
                break;
            case 2 :
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHeaderPicture.setResourceIds(R.drawable.sports_00, R.drawable.sports_01);
                    }
                }, 300);
                simbolIv.setImageResource(R.drawable.icon_sports);
                break;
            case 3 :
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHeaderPicture.setResourceIds(R.drawable.politics_00, R.drawable.politics_01);
                    }
                }, 300);
                simbolIv.setImageResource(R.drawable.icon_politics);
                break;
            case 4 :
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHeaderPicture.setResourceIds(R.drawable.society_00, R.drawable.society_01);
                    }
                }, 300);
                simbolIv.setImageResource(R.drawable.icon_society);
                break;
            case 5 :
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHeaderPicture.setResourceIds(R.drawable.econo_00, R.drawable.econo_01);
                    }
                }, 300);
                simbolIv.setImageResource(R.drawable.icon_econo);
                break;
            case 6 :
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHeaderPicture.setResourceIds(R.drawable.world_00, R.drawable.world_01);
                    }
                }, 300);
                simbolIv.setImageResource(R.drawable.icon_world);
                break;
            case 7 :
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHeaderPicture.setResourceIds(R.drawable.culture_00, R.drawable.culture_01);
                    }
                }, 300);
                simbolIv.setImageResource(R.drawable.icon_culture);
                break;
            case 8 :
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHeaderPicture.setResourceIds(R.drawable.science_00, R.drawable.science_01);
                    }
                }, 300);
                simbolIv.setImageResource(R.drawable.icon_science);
                break;
        }
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.dialog_main_show_amination);
        simbolIv.setAnimation(animation);

        SparseArrayCompat<ScrollTabHolder> scrollTabHolders = mPagerAdapter.getScrollTabHolders();
        ScrollTabHolder currentHolder = scrollTabHolders.valueAt(position);
        currentHolder.adjustScroll((int) (mHeader.getHeight() + ViewHelper.getTranslationY(mHeader)));

        ((TextView) mSmartTabLayout.getTabAt(beforeTabPosition).findViewById(R.id.tv_tab_title)).setTextColor(getResources().getColor(R.color.tab_default_text_color));
        customTabTv = (TextView) mSmartTabLayout.getTabAt(position).findViewById(R.id.tv_tab_title);
        customTabTv.setTextColor(Color.WHITE);
        beforeTabPosition = position;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {
        if (mViewPager.getCurrentItem() == pagePosition) {
            if (isPageSelected) {
                if (pageScrollStateChangePosition == 0) {
                    isPageSelected = false;
                }
                return;
            }
            int scrollY = getScrollY(view);
            ViewHelper.setTranslationY(mHeader, Math.max(-scrollY, mMinHeaderTranslation));
            float ratio = clamp(ViewHelper.getTranslationY(mHeader) / mMinHeaderTranslation, 0.0f, 1.0f);
            interpolate(mHeaderLogo, getActionBarIconView(), sSmoothInterpolator.getInterpolation(ratio));
            Float transparentAlpha = clamp(5.0F * ratio - 4.0F, 0.0F, 1.0F);
            setTitleAlpha(transparentAlpha);
            if (1 == transparentAlpha) {
                setSubTitleAlpha(0.0F);
            } else {
                setSubTitleAlpha(1.0F);
            }
        }
    }

    @Override
    public void adjustScroll(int scrollHeight) {
        // nothing
    }

    private void setCustomTabLayout() {
        ((TextView) mSmartTabLayout.getTabAt(0).findViewById(R.id.tv_tab_title)).setTextColor(Color.WHITE);
    }

    public static int getScrollY(AbsListView view) {
        View c = view.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = mHeaderHeight;
        }

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

    public static float clamp(float value, float max, float min) {
        return Math.max(Math.min(value, min), max);
    }

    public static void interpolate(View view1, View view2, float interpolation) {
        getOnScreenRect(mRect1, view1);
        getOnScreenRect(mRect2, view2);

        float scaleX = 1.0F + interpolation * (mRect2.width() / mRect1.width() - 1.0F);
        float scaleY = 1.0F + interpolation * (mRect2.height() / mRect1.height() - 1.0F);
        float translationX = 0.5F * (interpolation * (mRect2.left + mRect2.right - mRect1.left - mRect1.right));
        float translationY = 0.5F * (interpolation * (mRect2.top + mRect2.bottom - mRect1.top - mRect1.bottom));

        ViewHelper.setTranslationX(view1, translationX);
        ViewHelper.setTranslationY(view1, translationY - ViewHelper.getTranslationY(mHeader));
        ViewHelper.setScaleX(view1, scaleX);
        ViewHelper.setScaleY(view1, scaleY);
    }

    public static RectF getOnScreenRect(RectF rect, View view) {
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public int getActionBarHeight() {
        if (mActionBarHeight != 0) {
            return mActionBarHeight;
        }

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB){
            getTheme().resolveAttribute(android.R.attr.actionBarSize, mTypedValue, true);
        }else{
            getTheme().resolveAttribute(R.attr.actionBarSize, mTypedValue, true);
        }

        mActionBarHeight = TypedValue.complexToDimensionPixelSize(mTypedValue.data, getResources().getDisplayMetrics());

        return mActionBarHeight;
    }

    private void setTitleAlpha(float alpha) {
        mAlphaForegroundTitleColorSpan.setAlpha(alpha);
        mSpannableTitleString.setSpan(mAlphaForegroundTitleColorSpan, 0, mSpannableTitleString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        actionTitleTv.setText(mSpannableTitleString);
    }

    private void setSubTitleAlpha(float alpha) {
        mAlphaForegroundSubTitleColorSpan.setAlpha(alpha);
        mSpannableSubTitleString.setSpan(mAlphaForegroundSubTitleColorSpan, 0, mSpannableSubTitleString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        actionSubTitleTv.setText(mSpannableSubTitleString);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static ImageView getActionBarIconView() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			return (ImageView) toolbar.findViewById(R.id.iv_logo);
        }
        return null;
//        return (ImageView)findViewById(android.support.v7.appcompat.R.id.home);
    }
}
