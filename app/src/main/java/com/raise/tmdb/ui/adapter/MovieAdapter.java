package com.raise.tmdb.ui.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raise.tmdb.App;
import com.raise.tmdb.R;
import com.raise.tmdb.data.model.Movie;
import com.raise.tmdb.util.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raise.yang on 17/08/09.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    //数据集合
    private List<Movie> m_movies = new ArrayList<>();

    private OnItemClickListener m_clickListener;

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        m_clickListener = clickListener;
    }

    public void set_datas(List<Movie> movies) {
        if (movies != null)
            m_movies = movies;
        notifyDataSetChanged();
    }

    public void add_datas(List<Movie> movies) {
        m_movies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie item = m_movies.get(position);
        holder.name_tv.setText(item.getTitle());
        Picasso picasso = Picasso.with(App.get());
        picasso.setLoggingEnabled(true);
        picasso.load(ApiUtils.get_image_url(item.getPoster_path()))
                .placeholder(R.mipmap.placeholder_poster)
                .into(holder.poster_img);
        if (m_clickListener != null) {
            holder.itemView.setOnClickListener(__ -> m_clickListener.onItemClick(item));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                App.get()).inflate(R.layout.item_movie, parent,
                false));
        return holder;
    }

    @Override
    public int getItemCount() {
        return m_movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name_tv;
        ImageView poster_img;

        public ViewHolder(View itemView) {
            super(itemView);
            name_tv = (TextView) itemView.findViewById(R.id.textview_name);
            poster_img = (ImageView) itemView.findViewById(R.id.movie_poster);
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            if (parent.getChildLayoutPosition(view) != 0)
                outRect.top = space;
        }
    }
}
