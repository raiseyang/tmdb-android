package com.raise.tmdb.ui.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raise.tmdb.R;
import com.raise.tmdb.data.model.Movie;
import com.raise.tmdb.ui.RankContract;
import com.raise.tmdb.ui.adapter.MovieAdapter;
import com.raise.tmdb.ui.presenter.RankPresenter;
import com.raise.tmdb.util.ScreenUtils;
import com.raise.trace.Trace;

import java.util.List;

public class RankFragment extends Fragment implements RankContract.View {

    private final String TAG = "RankFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    //直接定义成具体的实现类，方便跟踪代码
    private RankPresenter m_presenter;
    private Context m_context;

    private ProgressDialog m_progress;
    private RecyclerView m_recyclerView;

    private MovieAdapter movieAdapter;

    private OnFragmentInteractionListener mListener;

    public RankFragment() {
    }

    public static RankFragment newInstance(String param1, String param2) {
        RankFragment fragment = new RankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        m_context = context;
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //mvp双向绑定
        new RankPresenter().start(this);

        m_recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        LinearLayoutManager llm = new LinearLayoutManager(m_context);
        m_recyclerView.setLayoutManager(llm);

        movieAdapter = new MovieAdapter();
        m_recyclerView.setAdapter(movieAdapter);
        m_recyclerView.addItemDecoration(new MovieAdapter.SpaceItemDecoration(ScreenUtils.dpToPxInt(m_context, 5)));
        movieAdapter.setOnItemClickListener(movie -> goto_movie_detail(movie));

    }

    private void goto_movie_detail(Movie movie) {
        Intent intent = new Intent(m_context, MovieDetailActivity.class);
        intent.putExtra("movie_id", movie.getId());
        startActivity(intent);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void set_presenter(RankContract.Presenter presenter) {
        m_presenter = (RankPresenter) presenter;
    }

    @Override
    public void show_progress_dialog(int progress) {
        if (m_progress == null) {
            m_progress = new ProgressDialog(m_context);
            m_progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            m_progress.setMessage("正在请求列表");
        }
        m_progress.setProgress(progress);
        m_progress.show();
    }

    @Override
    public void dismiss_progress_dialog() {
        Trace.d(TAG, "dismiss_progress_dialog()  currentThread = " + Thread.currentThread().getName());
        if (m_progress != null && m_progress.isShowing()) {
            m_progress.dismiss();
        }
    }

    @Override
    public void refresh_rank(List<Movie> datas) {
        Trace.d(TAG, "refresh_rank() ");
        movieAdapter.add_datas(datas);
//        movieAdapter.set_datas(datas);
    }

    public void fetch_rank() {
        m_presenter.fetch_rank();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
