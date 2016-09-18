package com.bwf.framwork.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bwf.framwork.tools.AppManager;

/**
 * Created by Lizhangfeng on 2016/7/13 0013.
 * Description: Activity基本
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());

        //如果存在actionBar，就隐藏
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        AppManager.getInstance().addActivity(this);

        setTranslucentStatus();

        beforeInitView();
        initView();
        initData();

    }

    public abstract int getContentViewId();//放layoutId

    public abstract void beforeInitView();//初始化View之前做的事

    public abstract void initView();//初始化View

    public abstract void initData();//初始化数据

    /**
     * 状态栏透明只有Android 4.4 以上才支持
     */
    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.setAttributes(layoutParams);
        }
    }

    /**
     * 不用强制转换的findviewbyid
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findViewByIdNoCast(int id) {
        return (T) findViewById(id);
    }

    public void setOnClick(int... ids) {
        for (int id : ids)
            findViewById(id).setOnClickListener(this);

    }

    public void setOnClick(View... views) {
        for (View view : views)
            view.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().remove(this);
        super.onDestroy();
    }
}
