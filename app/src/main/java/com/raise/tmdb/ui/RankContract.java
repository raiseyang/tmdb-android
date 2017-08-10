package com.raise.tmdb.ui;

import com.raise.tmdb.data.model.Movie;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by raise.yang on 17/08/09.
 */

public interface RankContract {

    interface View {

        void set_presenter(Presenter presenter);

        void show_progress_dialog(int progress);

        void dismiss_progress_dialog();

        void refresh_rank(List<Movie> datas);

    }

    interface Presenter {
        void start(@NonNull RankContract.View view);

        void fetch_rank();
    }

}
