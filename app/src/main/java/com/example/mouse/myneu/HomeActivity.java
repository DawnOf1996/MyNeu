package com.example.mouse.myneu;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mouse.myneu.Adapter.TabViewPagerAdapter;
import com.example.mouse.myneu.View.HotFragment;
import com.example.mouse.myneu.View.JobFragment;
import com.example.mouse.myneu.View.TeamFragment;
import com.lyp.networkhelper.activity.BaseActivity;
import com.lyp.networkhelper.view.DefaultLoadingLayout;
import com.lyp.networkhelper.view.NetworkHelperLayout;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

public class HomeActivity extends BaseActivity {
    private Toolbar homeToolbar;
    private ViewPager homeViewPager;
    private TabLayout homeTab;
    private TabViewPagerAdapter tabViewPagerAdapter;

    private DefaultLoadingLayout mLoadingLayout;

    public static String IMAGE_CACHE_PATH = "MyImageCache";//图片缓存路径

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        initImageLoader();//优先初始化
        mLoadingLayout = (DefaultLoadingLayout) NetworkHelperLayout.createDefaultLayout(this, homeViewPager);

        homeToolbar = findView(R.id.home_toolbar);
        homeToolbar.setTitle(getResources().getString(R.string.app_name));
        homeToolbar.inflateMenu(R.menu.menu_main);

        homeViewPager = findView(R.id.home_view_pager);
        homeTab = findView(R.id.home_tab);
        homeTab.addTab(homeTab.newTab().setText("岗位课程"));
        homeTab.addTab(homeTab.newTab().setText("热门课程"));
        homeTab.addTab(homeTab.newTab().setText("团队介绍"));
        tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        tabViewPagerAdapter.addFragments(new JobFragment(), "岗位课程");
        tabViewPagerAdapter.addFragments(new HotFragment(), "热门课程");
        tabViewPagerAdapter.addFragments(new TeamFragment(), "团队介绍");
        homeViewPager.setOffscreenPageLimit(3);
        homeViewPager.setAdapter(tabViewPagerAdapter);
        homeTab.setupWithViewPager(homeViewPager);

    }

    @Override
    protected void initEvents() {
        homeToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.menu_logout:
                        finish();
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    private void initImageLoader() {
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
                .getOwnCacheDirectory(getApplicationContext(), IMAGE_CACHE_PATH);

        DisplayImageOptions defaultOptions = new DisplayImageOptions
                .Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .discCacheSize(32 * 1024 * 1024)
                .discCacheFileCount(100)
//                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        ImageLoader.getInstance().init(config);
    }
}