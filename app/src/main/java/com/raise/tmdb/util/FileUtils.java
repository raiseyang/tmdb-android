package com.raise.tmdb.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by raise.yang on 17/08/08.
 */

public class FileUtils {

    public static void write_file(InputStream is, String file_path) throws IOException {

        OutputStream os = null;
        try {
            byte[] buffer = new byte[4096];

            os = new FileOutputStream(file_path);
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.flush();
        } finally {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean decompress(@NonNull String gz_file, @NonNull String dest_file_path) {
        GZIPInputStream gzipInputStream = null;
        BufferedOutputStream os = null;
        try {
            byte[] buf = new byte[1024 * 2];
            FileInputStream fis = new FileInputStream(gz_file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            gzipInputStream = new GZIPInputStream(bis);

            os = new BufferedOutputStream(
                    new FileOutputStream(new File(dest_file_path)));

            byte[] buffer = new byte[4096];
            int len = 0;
            while ((len = gzipInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                os.close();
                gzipInputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static Flowable<String> rx_read_file(@NonNull String file_path, int max_line) {
        return Flowable.create((FlowableEmitter<String> e) -> {
            FileReader fr = new FileReader(file_path);
            BufferedReader bufferedReader = new BufferedReader(fr);
            int max = max_line;
            while (!e.isCancelled()
                    && max-- > 0) {
                e.onNext(bufferedReader.readLine());
            }
            e.onComplete();
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                ;
    }

    public static Observable<Integer> rx_write_file(InputStream is, String file_path, long file_size) {
        return Observable.create(e -> {
            long total_size = file_size;
            OutputStream os = null;
            try {
                byte[] buffer = new byte[4096];

                os = new FileOutputStream(file_path);
                long write_size = 0;
                int len = 0;
                int notify_pro = 0;
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                    write_size += len;
                    int cur_pro = (int) (write_size * 100 / total_size);
                    //只有进度有变化才通知
                    if (cur_pro != notify_pro) {
                        e.onNext(cur_pro);
                        notify_pro = cur_pro;
                    }
                }
                os.flush();
                e.onComplete();
            } catch (Exception ex) {
                e.onError(ex);
            } finally {
                try {
                    os.close();
                    is.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
