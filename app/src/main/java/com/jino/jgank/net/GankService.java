package com.jino.jgank.net;


import com.jino.jgank.entity.GankResponEntity;
import com.jino.jgank.entity.GankSearchEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Gank相关API
 * Created by JINO on 2018/1/19.
 */

public interface GankService {
    String GANK_BASE_URL = "http://gank.io/api";

    String CATEGROY_ALL = "all";
    String CATEGROY_FULI = "福利";
    String CATEGROY_ANDROID = "Android";
    String CATEGROY_IOS = "iOS";
    String CATEGROY_VIDEO = "休息视频";
    String CATEGROY_EXT = "拓展资源";
    String CATEGROY_WEB = "前端";
    String CATEGROY_RANDOM = "瞎推荐";

    /**
     * category: all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     *
     * @param cate 分类
     * @param num  个数
     * @param page 页数
     * @return Observable<GankResponEntity>
     */
    @GET(GANK_BASE_URL + "/data/{cate}/{num}/{page}")
    Observable<GankResponEntity> getCategroyData(@Path("cate") String cate,
                                                 @Path("num") int num, @Path("page") int page);

    /**
     * 搜索API
     * @param keyword 关键字
     * @param num 个数
     * @param page 页数
     * @return Observable<GankResponEntity>
     */
    @GET(GANK_BASE_URL+"/search/query/{keyword}/category/all/count/{num}/page/{page}")
    Observable<GankSearchEntity> search(@Path("keyword")String keyword, @Path("num")int num
            , @Path("page")int page);
}
