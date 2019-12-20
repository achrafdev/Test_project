package com.viking.test_project.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.viking.test_project.Adapters.NewsAdapter;
import com.viking.test_project.Model.News;
import com.viking.test_project.R;
import com.viking.test_project.Retrofit.ApiInterface;
import com.viking.test_project.Retrofit.ApiManager;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity{

    private ArrayList<News> newssList = new ArrayList<>();
    NewsAdapter newsAdapter;
    RecyclerView recyclerViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerViewNews = findViewById(R.id.news_recylerview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_centered);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar.setTitle("NEWS");
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getNews();
    }

    private void getNews() {
        ApiInterface service = ApiManager.createService(ApiInterface.class);
        Call<List<News>> newsCall = service.getNews();

        newsCall.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                for(News f: response.body()){
                    newssList.add(f);
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(ListActivity.this);
                recyclerViewNews.setLayoutManager(layoutManager);
                newsAdapter = new NewsAdapter(newssList,ListActivity.this);
                newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(News news) {
                        Intent i = new Intent(ListActivity.this, DetailsActivity.class);
                        i.putExtra("news", news);
                        startActivity(i);
                    }
                });
                recyclerViewNews.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable throwable) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
