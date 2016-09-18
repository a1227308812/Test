package com.bwf.framwork.http;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lizhangfeng on 2016/7/13 0013.
 * Description:
 */
public class HttpHelper {

    public static void login(Activity activity, HttpRequestAsyncTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "landz");
        map.put("password", "123456");
        Request request = new Request("http://baidu.com", 10000, Request.Method.POST, map);
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestAsyncTask.execute(request);
    }

}
