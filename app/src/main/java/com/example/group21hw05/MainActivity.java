package com.example.group21hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetSimpleAsync.Idata {
    ProgressDialog builder;
    ArrayAdapter<Source> adapter;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");
        builder = new ProgressDialog(MainActivity.this);
        builder.setMessage("Loading Sources...");
        builder.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        builder.setCancelable(false);
        builder.show();

        try {
            new GetSimpleAsync(MainActivity.this).execute("https://newsapi.org/v2/sources?apiKey=8f557fd881474e2e97e0568c454f5de3");
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void handledata(final ArrayList<Source> sources) {

        ListView listView = (ListView) findViewById(R.id.lv_news);
        SourceAdapter sourceAdapter = new SourceAdapter(this, R.layout.source_items, sources);
        listView.setAdapter(sourceAdapter);
        builder.dismiss();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent intent = new Intent(MainActivity.this, NewsViewActivity.class);
                    intent.putExtra("sourceid", sources.get(position).id);
                    intent.putExtra("sourceName", sources.get(position).name);
                    startActivity(intent);
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

}
