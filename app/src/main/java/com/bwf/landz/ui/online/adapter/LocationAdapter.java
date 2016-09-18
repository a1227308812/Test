package com.bwf.landz.ui.online.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.landz.R;
import com.bwf.landz.entity.ParamListBean;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/15 0015.
 * Description:区域，价格等相关adapter
 */
public class LocationAdapter extends BaseAdapter {

    private List<ParamListBean> items;

    private Context context;

    public LocationAdapter(Context context) {
        this.context = context;
    }

    public LocationAdapter(Context context, List<ParamListBean> items) {
        this.items = items;
        this.context = context;
    }

    public void setItems(List<ParamListBean> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items == null ? null : items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHodler viewHodler = null;
        if (convertView == null) {
            viewHodler = new ViewHodler();
            convertView = View.inflate(context, R.layout.item_location, null);
            viewHodler.tv_item_location = (TextView) convertView.findViewById(R.id.tv_item_location);
            viewHodler.ll_location = (LinearLayout) convertView.findViewById(R.id.ll_location);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }

        ParamListBean bean = items.get(position);
        viewHodler.tv_item_location.setText(bean.name);

        if (bean.isSelect){
            viewHodler.ll_location.setBackgroundColor(Color.GRAY);
        }else {
            viewHodler.ll_location.setBackgroundColor(Color.WHITE);
        }

        return convertView;
    }

    private class ViewHodler {
        LinearLayout ll_location;
        TextView tv_item_location;
    }

}
