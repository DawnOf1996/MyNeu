package com.example.mouse.myneu;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.mouse.myneu.Adapter.TabViewPagerAdapter;
import com.example.mouse.myneu.View.JobInfoFragment;
import com.example.mouse.myneu.View.JobStudyFragment;
import com.lyp.networkhelper.activity.BaseActivity;

public class JobDetailActivity extends BaseActivity {
    private Toolbar jobDetailToolbar;
    private ViewPager jobDetailViewPager;
    private TabLayout jobDetailTab;
    private TabViewPagerAdapter tabViewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_job_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        jobDetailToolbar= (Toolbar) findViewById(R.id.job_detail_toolbar);
        //设置返回
        setToolbarWithBack(jobDetailToolbar, 0);
        jobDetailViewPager= (ViewPager) findViewById(R.id.job_detail_view_pager);
        jobDetailTab= (TabLayout) findViewById(R.id.job_detail_tab);

        jobDetailTab.addTab(jobDetailTab.newTab().setText("岗位信息"));
        jobDetailTab.addTab(jobDetailTab.newTab().setText("阶段学习"));

        tabViewPagerAdapter=new TabViewPagerAdapter(getSupportFragmentManager());
        tabViewPagerAdapter.addFragments(new JobInfoFragment(),"岗位信息");
        tabViewPagerAdapter.addFragments(new JobStudyFragment(),"阶段学习");

        jobDetailViewPager.setOffscreenPageLimit(2);
        jobDetailViewPager.setAdapter(tabViewPagerAdapter);
        jobDetailTab.setupWithViewPager(jobDetailViewPager);
    }

    @Override
    protected void initEvents() {

    }
}
