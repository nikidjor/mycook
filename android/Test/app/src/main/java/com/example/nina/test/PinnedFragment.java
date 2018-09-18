package com.example.nina.test;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nina.test.db.AppDatabase;
import com.example.nina.test.db.PinnedNews;
import com.example.nina.test.db.PinnedNewsDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class PinnedFragment extends Fragment {
    private PinnedNewsAdapter mAdapter;
    private AppDatabase database;

    public PinnedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);

        database = ((MainActivity) getActivity()).getDatabase();

        PinnedNewsAsyncTask task = new PinnedNewsAsyncTask();
        task.execute();

        final ListView newsListView = (ListView) rootView.findViewById(R.id.list);
        mAdapter = new PinnedNewsAdapter(getActivity(), new ArrayList<PinnedNews>());
        newsListView.setAdapter(mAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PinnedNews news = mAdapter.getItem(i);
                Uri newsUri = Uri.parse(news.getNewsUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(webIntent);
            }
        });

        newsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PinnedNews news = mAdapter.getItem(position);
                PinnedNewsDAO dao = database.pinnedNewsDao();
                dao.removeAllPinnedNews();
                FragmentTransaction ftr = getFragmentManager().beginTransaction();
                ftr.detach(PinnedFragment.this).attach(PinnedFragment.this).commit();

                return true;
            }
        });

        return rootView;

    }

    private class PinnedNewsAsyncTask extends AsyncTask<String, Void, List<PinnedNews>> {

        private ProgressDialog progressDialog;

        @Override
        protected List<PinnedNews> doInBackground(String... strings) {
            return database.pinnedNewsDao().getAllPinnedNews();
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
        protected void onPostExecute(List<PinnedNews> data) {
            mAdapter.clear();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }


    }

}

