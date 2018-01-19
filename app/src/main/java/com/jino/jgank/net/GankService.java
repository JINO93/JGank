package com.jino.jgank.net;

import android.database.Observable;

import com.jino.jgank.entity.GankResponEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by JINO on 2018/1/19.
 */

public interface GankService {
    public static final String GANK_BASE_URL = "http://http://gank.io/api";

    /**
     * category: all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     * @param cate 分类
     * @param num  个数
     * @param page  页数
     * @return
     */
    @GET(GANK_BASE_URL + "/data/{cate}/{num}/{page}")
    Observable<GankResponEntity> getCategroyData(@Path("cate") String cate,
                                                 @Path("num") int num, @Path("page") int page);
}
