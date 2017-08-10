package com.raise.tmdb.ui.presenter;

import android.content.Context;

import com.raise.tmdb.App;
import com.raise.tmdb.constant.Const;
import com.raise.tmdb.data.MovieRepository;
import com.raise.tmdb.data.MovieSimpleRepository;
import com.raise.tmdb.data.model.Movie;
import com.raise.tmdb.data.model.MovieSimple;
import com.raise.tmdb.http.RetrofitProvider;
import com.raise.tmdb.http.TmdbApi;
import com.raise.tmdb.ui.RankContract;
import com.raise.tmdb.util.ApiUtils;
import com.raise.tmdb.util.FileUtils;
import com.raise.tmdb.util.ToastUtils;
import com.raise.trace.Trace;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by raise.yang on 17/08/09.
 */

public class RankPresenter implements RankContract.Presenter {
    private String TAG = "RankPresenter";
    private RankContract.View m_view;
    private Context m_context;

    @Override
    public void start(RankContract.View view) {
        m_view = view;
        m_context = App.get();
        m_view.set_presenter(this);
    }

    @Override
    public void fetch_rank() {
        Trace.d(TAG, "fetch_rank() ");
        m_view.show_progress_dialog(0);
        String gz_path = Const.get_movie_file_gz();

        if (new File(gz_path).exists()) {
            m_view.dismiss_progress_dialog();
            notify_rank_view();
            return;
        }

        RetrofitProvider.getInstance()
                .create_file(TmdbApi.class)
                .daily_file(ApiUtils.get_newest_date())
                .subscribeOn(Schedulers.io())
                .concatMap(responseBody -> {
//                    Trace.d(TAG, "apply() --status = " + responseBody.contentLength());
                    Trace.d(TAG, "fetch_rank() concatMap currentThread = " + Thread.currentThread().getName());
                    return FileUtils.rx_write_file(responseBody.byteStream(), gz_path, responseBody.contentLength());
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(integer -> {
                    m_view.show_progress_dialog(integer);
                })
                .observeOn(Schedulers.io())
                .doOnComplete(() -> {
                    Trace.d(TAG, "fetch_rank() doOnComplete currentThread = " + Thread.currentThread().getName());
                    boolean decompress = FileUtils.decompress(gz_path, Const.get_movie_file());
                    Trace.d(TAG, "fetch_rank() success = " + decompress);
                    if (decompress) {
                        Observable.empty()
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnComplete(() -> m_view.dismiss_progress_dialog())
                                .subscribe();
                        notify_rank_view();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> {
                    Trace.e(TAG, "fetch_rank() e = " + throwable);
                    ToastUtils.showToast("网络错误！！！");
                    m_view.dismiss_progress_dialog();
                })
                .subscribe();
    }

    private void notify_rank_view() {
        Trace.d(TAG, "notify_rank_view() ");
        String gz_path = Const.get_movie_file_gz();
//        List<Movie> movieList =
        MovieSimpleRepository.getInstance()
                .getMovieSimples()
                .toObservable()
                .observeOn(Schedulers.io())
                .flatMap(new Function<MovieSimple, ObservableSource<Movie>>() {
                    @Override
                    public ObservableSource<Movie> apply(MovieSimple movieSimple) throws Exception {
                        Trace.d(TAG, "apply() MovieRepository.getInstance().getMovie(movieSimple.getId())");
                        Trace.d(TAG, "apply() " + Thread.currentThread().getName());
                        return MovieRepository.getInstance().getMovie(movieSimple.getId());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> {
                    Trace.e(TAG, "notify_rank_view() 请求电影列表失败 e = " + throwable);
                })
                .doOnNext(m -> {
                    List list = new ArrayList();
                    list.add(m);
                    m_view.refresh_rank(list);
                }).subscribe();
//
//        MovieRepository.getInstance()
//                .get_movie(gz_path, 10)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnError(throwable -> {
//                    Trace.e(TAG, "notify_rank_view() 请求电影列表失败 e = " + throwable);
//                })
//                .doOnNext(m -> {
//                    List list = new ArrayList();
//                    list.add(m);
//                    m_view.refresh_rank(list);
//                }).subscribe();
//                .toList()
//                .blockingGet();
//        m_view.refresh_rank(movieList);
    }


}
