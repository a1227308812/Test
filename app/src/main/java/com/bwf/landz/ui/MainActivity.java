package com.bwf.landz.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.tools.AppManager;
import com.bwf.framwork.utils.AssetUtils;
import com.bwf.framwork.utils.DrawableUtils;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.StringUtils;
import com.bwf.framwork.utils.ToastUtil;
import com.bwf.landz.MyApplication;
import com.bwf.landz.R;
import com.bwf.landz.entity.OnlineTypeBean;
import com.bwf.landz.ui.online.OnLineHouseActivity;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements Handler.Callback {

    private TextView tv_main_online;
    private TextView tv_main_seebuild;
    private TextView tv_main_wait_rent;
    private TextView tv_main_onehouse;

    private TextView[] textViews;
    private Integer[] normal_ids = new Integer[]{R.mipmap.main_online_normal, R.mipmap.main_seebuild_normal
            , R.mipmap.main_wait_rent_normal, R.mipmap.main_onehouse_normal};
    private Integer[] select_ids = new Integer[]{R.mipmap.main_online, R.mipmap.main_seebuild
            , R.mipmap.main_wait_rent, R.mipmap.main_onehouse};

    private Handler handler;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void beforeInitView() {
        handler = new Handler(this);
    }

    @Override
    public void initView() {
        tv_main_online = findViewByIdNoCast(R.id.tv_main_online);
        tv_main_seebuild = findViewByIdNoCast(R.id.tv_main_seebuild);
        tv_main_wait_rent = findViewByIdNoCast(R.id.tv_main_wait_rent);
        tv_main_onehouse = findViewByIdNoCast(R.id.tv_main_onehouse);
    }

    @Override
    public void initData() {
        textViews = new TextView[]{tv_main_online, tv_main_seebuild, tv_main_wait_rent, tv_main_onehouse};
        setOnClick(tv_main_online, tv_main_seebuild, tv_main_wait_rent, tv_main_onehouse);

       // getApplication()//获取到application对象，只有activity才有这个方法,context没有这方法但是可以拿到getApplicationContext()

        AssetUtils.getOnlineTypeData(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_main_online://在售豪宅
                setSelect(0);
                IntentUtils.openActivity(MainActivity.this, OnLineHouseActivity.class);
                break;
            case R.id.tv_main_seebuild://楼盘鉴赏
                setSelect(1);
                break;
            case R.id.tv_main_wait_rent://待租豪宅
                setSelect(2);
                break;
            case R.id.tv_main_onehouse://一手豪宅
                setSelect(3);
                break;
        }
    }

    public void setSelect(int postion) {
        for (int i = 0; i < textViews.length; i++) {
            if (i == postion) {
                textViews[i].setTextColor(Color.parseColor("#fff0cb7e"));
                Drawable drawable = ContextCompat.getDrawable(MainActivity.this, select_ids[i]);
                DrawableUtils.drawableTop(MainActivity.this, textViews[i], select_ids[i]);
            } else {
                textViews[i].setTextColor(Color.WHITE);
                DrawableUtils.drawableTop(MainActivity.this, textViews[i], normal_ids[i]);
            }
        }

    }

    private static final int TIMES = 2000;

    private boolean isBack = true;

    /**
     * 按下监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {//按下返回键

            if (isBack) {
                ToastUtil.showToast("再点一次退出");
                isBack = false;
                handler.sendEmptyMessageDelayed(1, 2000);
            } else {
                //退出app
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case 1:
                isBack = true;
                break;
        }

        return false;
    }
}
