package com.teddydev.abnd_8_news_app;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.teddydev.abnd_8_news_app.Model.News;
import com.teddydev.abnd_8_news_app.Model.Util;
import com.teddydev.abnd_8_news_app.Util.NewsLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private String BASE_URL = "http://content.guardianapis.com/search";
    private String topic = "android";

    private ListView listView;
    private TextView msgTextView;
    private ProgressBar progressbar;
    private NewsListAdapter newsListAdapter;
    private List<News> newsList = new ArrayList<>();

    private ListView.OnItemClickListener newsOnItemClickListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            News newsOnClicked = newsListAdapter.getItem(position);
            if (newsOnClicked != null) {
                Util.openLinkInBrowser(MainActivity.this, newsOnClicked.getWebUrl());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(0, null, MainActivity.this).forceLoad();
        newsListAdapter = new NewsListAdapter(this, newsList);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(newsListAdapter);
        listView.setOnItemClickListener(newsOnItemClickListener);
    }

    private String buildUrl(String topic) {
        Uri baseUri = Uri.parse(BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", topic);
        uriBuilder.appendQueryParameter("api-key", "test");
        return uriBuilder.toString();
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
//        progressbar.setVisibility(View.VISIBLE);
//        msgTextView.setVisibility(View.GONE);
        return new NewsLoader(this, buildUrl(topic));
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        this.newsList.clear();
        if (news != null && !news.isEmpty()) {
            this.newsList.addAll(news);
            newsListAdapter.notifyDataSetChanged();
            listView.setVisibility(View.VISIBLE);
        } else {
            // msgTextView.setText(getString(R.string.fetching_error));
        }
      //  progressbar.setVisibility(View.GONE);
        listView.setEmptyView(msgTextView);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsListAdapter.clear();
    }
}
