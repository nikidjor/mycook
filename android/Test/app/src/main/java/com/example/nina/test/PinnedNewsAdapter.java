package com.example.nina.test;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nina.test.db.PinnedNews;

import java.util.List;

public class PinnedNewsAdapter extends ArrayAdapter<PinnedNews> {

    public PinnedNewsAdapter(Activity context, List<PinnedNews> news) {

        super(context, 0, news);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        PinnedNews currentNews = getItem(position);

        //todo fixme with real news content
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        dateTextView.setText(currentNews.getId());


        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_text_view);
        titleTextView.setText(currentNews.getNewsUrl());

        TextView houseTextView = (TextView) listItemView.findViewById(R.id.house_text_view);
        houseTextView.setText("house");

        return listItemView;
    }

}
