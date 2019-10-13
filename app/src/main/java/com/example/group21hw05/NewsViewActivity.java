package com.example.group21hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsViewActivity extends AppCompatActivity implements GetNewsAsync.InewsData {
    ProgressDialog builder;
    ArrayAdapter<Source> adapter;
    String sourceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);
        builder = new ProgressDialog(NewsViewActivity.this);
        builder.setMessage("Loading News");
        builder.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        builder.setCancelable(false);
        builder.show();
        try {
            String id = getIntent().getExtras().getString("sourceid");
            sourceName = getIntent().getExtras().getString("sourceName");
            setTitle(sourceName);
            String url = "https://newsapi.org/v2/top-headlines?sources=" + id + "&apiKey=8f557fd881474e2e97e0568c454f5de3";
            new GetNewsAsync(NewsViewActivity.this).execute(url);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void getNewsData(final ArrayList<News> newsArrayList) {
        ListView listView = (ListView) findViewById(R.id.lv_2);
        NewsAdapter newsAdapter = new NewsAdapter(this, R.layout.news_items, newsArrayList);
        listView.setAdapter(newsAdapter);
        builder.dismiss();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("url",newsArrayList.get(position).urlToImage);
                String urltoweb = newsArrayList.get(position).url;
                Intent i = new Intent(NewsViewActivity.this, WebViewActivity.class);
                i.putExtra("newsurl", urltoweb);
                i.putExtra("webName", sourceName);
                Log.d("author",newsArrayList.get(position).author);
                startActivity(i);

            }
        });
    }
}
