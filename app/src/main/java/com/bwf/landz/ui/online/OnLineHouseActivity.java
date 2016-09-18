package com.bwf.landz.ui.online;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.landz.MyApplication;
import com.bwf.landz.R;
import com.bwf.landz.entity.OnLineTypeResultBean;
import com.bwf.landz.entity.OnlineTypeBean;
import com.bwf.landz.entity.ParamListBean;
import com.bwf.landz.view.MyPopwindow;

import java.util.List;

/**
 * 在售豪宅
 */
public class OnLineHouseActivity extends BaseActivity {

    private OnlineTypeBean onlineTypeBean;
    private TextView tv_location, tv_price, tv_room, tv_type, tv_more;

    @Override
    public int getContentViewId() {
        return R.layout.activity_on_line_house;
    }

    @Override
    public void beforeInitView() {
        onlineTypeBean = MyApplication.getApplication().getOnlineTypeBean();
    }

    @Override
    public void initView() {

        tv_location = findViewByIdNoCast(R.id.tv_location);
        tv_price = findViewByIdNoCast(R.id.tv_price);
        tv_room = findViewByIdNoCast(R.id.tv_room);
        tv_type = findViewByIdNoCast(R.id.tv_type);
        tv_more = findViewByIdNoCast(R.id.tv_more);

    }

    @Override
    public void initData() {
        setOnClick(tv_location, tv_price, tv_room, tv_type, tv_more);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_location://区域

                List<ParamListBean> paramList = null;

                for (OnLineTypeResultBean onLineTypeResultBean : onlineTypeBean.result) {
                    if ("1001".equals(onLineTypeResultBean.paramType)) {
                        paramList = onLineTypeResultBean.paramList;
                    }
                }

                MyPopwindow myPopwindow = new MyPopwindow(OnLineHouseActivity.this);
                myPopwindow.setParamListBean(paramList);
                myPopwindow.showPopWindow(tv_location);
                break;
        }
    }


}
