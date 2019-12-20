package com.viking.test_project.Retrofit;

import com.viking.test_project.Model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/api/app/article_types/5729fc387fdea7e267fa9761")
    Call<List<News>> getNews();

}
