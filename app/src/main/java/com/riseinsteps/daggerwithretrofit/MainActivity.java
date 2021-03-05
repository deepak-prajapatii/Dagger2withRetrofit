package com.riseinsteps.daggerwithretrofit;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.riseinsteps.daggerwithretrofit.adapter.Adapter;
import com.riseinsteps.daggerwithretrofit.model.Model;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private TextView page, perPage, total, total_page, url, text;
    private RecyclerView recyclerView;

    @Inject
    Retrofit retrofit;
    private TodoApi todoApi;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);
        perPage = findViewById(R.id.per_page);
        total = findViewById(R.id.total);
        total_page = findViewById(R.id.total_pages);
        url = findViewById(R.id.url);
        text = findViewById(R.id.text);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new Adapter();

        setData();
    }

    private void setData() {
        ((BaseApplication) getApplication()).getNetworkComponent().inject(this);
        todoApi = retrofit.create(TodoApi.class);

        Call<Model> call = todoApi.getModel();

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                page.setText(String.valueOf(response.body().getPage()));
                perPage.setText(String.valueOf(response.body().getPer_page()));
                total.setText(String.valueOf(response.body().getTotal()));
                total_page.setText(String.valueOf(response.body().getTotal_pages()));
                url.setText(response.body().getSupport().getUrl());
                text.setText(response.body().getSupport().getText());

                adapter.setDataList(response.body().getData());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}