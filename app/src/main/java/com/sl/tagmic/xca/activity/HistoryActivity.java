package com.sl.tagmic.xca.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.sl.tagmic.xca.R;

public class HistoryActivity extends AppCompatActivity {
    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }
}
