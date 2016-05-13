package com.fanhl.swipedemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private android.support.v7.widget.RecyclerView       recyclerView;
    private android.support.v4.widget.SwipeRefreshLayout swipeRefreshLayout;
    private ItemAdapter                                  adapter;
    private android.widget.TextView                      emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        initData();
        refreshData();
    }

    private void assignViews() {
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.emptyView = (TextView) findViewById(R.id.empty_view);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                refreshData();
            }
        });

        adapter = new ItemAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {

    }

    private void refreshData() {
        if (!swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(true);
        new AsyncTask<Void, Void, Void>() {

            @Override protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "do nothing");
                return null;
            }

            @Override protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                swipeRefreshLayout.setRefreshing(false);

                recyclerView.setVisibility(View.GONE);//FIXME The bug happened when this line enable.
                emptyView.setVisibility(View.VISIBLE);
            }
        }.execute();
    }
}
