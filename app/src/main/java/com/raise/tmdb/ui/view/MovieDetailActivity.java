package com.raise.tmdb.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.raise.tmdb.R;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        TextView movie_name = (TextView) findViewById(R.id.textView_movie_name);
        int movie_id = getIntent().getIntExtra("movie_id", -1);
        movie_name.setText(movie_id);
    }
}
