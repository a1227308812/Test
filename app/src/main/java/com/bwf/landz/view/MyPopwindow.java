package com.bwf.landz.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bwf.framwork.utils.DisplayUtil;
import com.bwf.landz.R;
import com.bwf.landz.entity.ParamListBean;
import com.bwf.landz.ui.online.adapter.LocationAdapter;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/15 0015.
 * Description:
 */
public class MyPopwindow extends PopupWindow {

    private LocationAdapter adapter, adapter2;

    private ListView lv_location, lv_location2;

    public MyPopwindow(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        View view = View.inflate(context, R.layout.pop_location, null);

        this.setContentView(view);
        this.setHeight(DisplayUtil.getDensity_Height(context) / 2);
        this.setBackgroundDrawable(new BitmapDrawable());//设置

        lv_location = (ListView) view.findViewById(R.id.lv_location);
        lv_location2 = (ListView) view.findViewById(R.id.lv_location2);
        adapter = new LocationAdapter(context);
        adapter2 = new LocationAdapter(context);
        lv_location.setAdapter(adapter);
        lv_location2.setAdapter(adapter2);
    }

    /**
     * 放ParamListBean数据
     *
     * @param beanList
     */
    public void setParamListBean(final List<ParamListBean> beanList) {

        if (beanList == null || beanList.isEmpty())
            return;

        adapter.setItems(beanList);
        adapter.notifyDataSetChanged();

        lv_location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //更新一级列表选中状态
                for (int i = 0; i < beanList.size(); i++) {
                    if (i == position)
                        beanList.get(i).isSelect = true;
                    else
                        beanList.get(i).isSelect = false;
                }
                adapter.notifyDataSetChanged();

                //二级列表
                ParamListBean bean = beanList.get(position);
                List<ParamListBean> childList = bean.childList;
                adapter2.setItems(childList);
                adapter2.notifyDataSetChanged();
            }
        });
    }

    /**
     * 显示popwindow
     *
     * @param view
     */
    public void showPopWindow(View view) {
        if (!isShowing()) {
            this.showAsDropDown(view);//显示在view的下方
            // this.showAtLocation(view, Gravity.TOP, 0, 0);//可以显示在指定view的指定位置
        }
    }

}
