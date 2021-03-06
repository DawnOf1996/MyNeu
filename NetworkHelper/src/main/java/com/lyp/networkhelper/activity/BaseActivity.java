package com.lyp.networkhelper.activity;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.lyp.networkhelper.R;
import com.lyp.networkhelper.receiver.NetworkReceiver;
import com.lyp.networkhelper.util.NetworkUtil;
import com.lyp.networkhelper.view.DefaultLoadingLayout;
import com.lyp.networkhelper.view.NetworkHelperLayout;


/**
 * Created by lyp on 2016/4/26.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = getClass().getSimpleName();

    private Context mContext;

    private DefaultLoadingLayout mLoadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerNetworkReceiver();

        mContext = this;

        this.setTranslucentStatusBar(this, false);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        mLoadingLayout = (DefaultLoadingLayout) NetworkHelperLayout.createDefaultLayout(this, getRootView(this));

        // 检测网络状态
        if (NetworkUtil.isNetworkAvailable(this)) {

            initViews();
            initData();
            initEvents();
        } else {
            mLoadingLayout.onError();
            mLoadingLayout.setErrorButtonListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (NetworkReceiver.isNetworkAvailable()) {

                        mLoadingLayout.onDone();
                        initViews();
                        initData();
                        initEvents();
                    } else {
                        toast("请检查网络连接！");
                    }
                }
            });
        }

    }


    /**
     * 设置activity布局
     *
     * @return 返回activity的layout资源文件id，没有布局返回0
     */
    protected abstract int getLayoutId();


    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化view，并设置属性
     */
    protected abstract void initViews();

    /**
     * 设置view的事件监听
     */
    protected abstract void initEvents();


    /**
     * 为toolbar设置返回按钮
     * @param toolbar
     */
    protected void setToolbarWithBack(Toolbar toolbar, int colorRes) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (colorRes != 0) {
            toolbar.setBackgroundColor(getResources().getColor(colorRes));
        }
    }

    /**
     * 通过泛型来简化findViewById
     */
    protected final <E extends View> E findView(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            Log.e(TAG, "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }

    /**
     * 获得根视图
     */
    private static View getRootView(Activity context)
    {
        return ((ViewGroup)context.findViewById(android.R.id.content)).getChildAt(0);
    }


    /**
     * 注册网络监听
     */
    private void registerNetworkReceiver()
    {
        NetworkReceiver receiver = NetworkReceiver.getNetWorkReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.gzcpc.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver, filter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //注销广播接收器
        unregisterReceiver(NetworkReceiver.getNetWorkReceiver());
    }

    /**
     * Android 4.4 以上的版本实现系统状态栏透明
     * @param activity
     */
    public static void setTranslucentStatusBar(Activity activity, Boolean enable) {
        if (enable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                activity.getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        } else {
            return;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void toast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    public void toast(int resId) {
        toast(mContext.getResources().getText(resId)+"");
    }
}
