package com.peace.love.photoviewer.http;

import com.peace.love.photoviewer.model.PhotoData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ProjectName:PhotoViewer
 * Date:2018/3/2 16:27
 *
 * @author CBF
 */

public interface Api {

    /**
     * @param keyword
     * @return
     */
    @GET("/rest/search_public.json")
    Observable<PhotoData> getPhotoData(@Query("keyword") String keyword);
}
