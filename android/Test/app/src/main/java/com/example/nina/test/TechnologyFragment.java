package com.example.nina.test;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechnologyFragment extends Fragment {

    private String NEWSAPI_URL =
            "https://newsapi.org/v2/top-headlines?country=rs&category=technology&apiKey=3ee7e20cceb94efb84ae7fec52e65ac8";

    private NewsAdapter mAdapter;

    public TechnologyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);

        TechnologyFragment.NewsAsyncTask task = new TechnologyFragment.NewsAsyncTask();
        task.execute(NEWSAPI_URL);

        final ListView newsListView = (ListView)rootView.findViewById(R.id.list);
        mAdapter = new NewsAdapter(getActivity(), new ArrayList<News>());
        newsListView.setAdapter(mAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news = mAdapter.getItem(i);
                Uri newsUri = Uri.parse(news.getUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW,newsUri);
                startActivity(webIntent);
            }
        });

        return rootView;

    }
    private class NewsAsyncTask extends AsyncTask<String,Void,List<News>> {

        private ProgressDialog progressDialog;

        @Override
        protected List<News> doInBackground(String... strings) {
            List<News> result = QueryUtils.fetchNewsData(NEWSAPI_URL);
            return result;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(List<News> data) {
            mAdapter.clear();
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            if (data != null && !data.isEmpty()){
                mAdapter.addAll(data);
            }
        }



    }

}