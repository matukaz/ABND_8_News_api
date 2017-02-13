package com.teddydev.abnd_8_news_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.teddydev.abnd_8_news_app.Model.News;

import java.util.List;


public class NewsListAdapter extends ArrayAdapter<News> {
    public NewsListAdapter(Context context, List<News> arrayList) {
        super(context, 0, arrayList);
    }

    static class ViewHolder {
        private TextView title;
        private TextView section;
    }

    @NonNull
    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        ViewHolder holder;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) listItemView.findViewById(R.id.title);
            holder.section = (TextView) listItemView.findViewById(R.id.section);
            listItemView.setTag(holder);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }

        News news = getItem(position);
        holder.title.setText(news.getTitle());
        holder.section.setText(news.getSection());

        return listItemView;
    }
}
