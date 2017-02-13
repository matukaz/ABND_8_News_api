package com.teddydev.abnd_8_news_app.Util;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.teddydev.abnd_8_news_app.Model.NetworkUtil;
import com.teddydev.abnd_8_news_app.Model.News;

import java.util.List;

/**
 * Created by Matu on 12.02.2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    String url;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<News> loadInBackground() {
        if (url == null) {
            return null;
        }
        return NetworkUtil.fetchNewsData(url);
    }
}
