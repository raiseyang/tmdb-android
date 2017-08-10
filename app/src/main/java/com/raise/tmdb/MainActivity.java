package com.raise.tmdb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.raise.tmdb.ui.view.RankFragment;
import com.raise.tmdb.util.FileUtils;
import com.raise.trace.Trace;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private String TAG = "MainActivity";

    RankFragment m_rankFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setSubtitle("这里是子标题");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        m_rankFragment = RankFragment.newInstance("1", "1");
        ft.add(R.id.fragment_container, m_rankFragment);
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Trace.d(TAG, "onMenuItemClick() ");
                m_rankFragment.fetch_rank();
                break;
            case R.id.action_search:

                String path = getFilesDir() + "/daily_date.gz";
                String dest_path = getFilesDir() + "/daily_date.txt";
                boolean success = FileUtils.decompress(path, dest_path);
                Trace.d(TAG, "onMenuItemClick() success = %s", success);

                break;
        }
        return true;
    }
}
