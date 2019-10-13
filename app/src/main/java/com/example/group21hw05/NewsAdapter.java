package com.example.group21hw05;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        try {
            News news = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_items, parent, false);
            }
            TextView textViewtitle = (TextView) convertView.findViewById(R.id.tv_title);
            TextView textViewauthor = (TextView) convertView.findViewById(R.id.tv_author);
            TextView textViewdate = (TextView) convertView.findViewById(R.id.tv_date);
            final ImageView imageViewnews = (ImageView) convertView.findViewById(R.id.iv_news);
            if (news.author.equals("null")) {
                textViewauthor.setVisibility(View.INVISIBLE);
            } else {
                textViewauthor.setText(news.author);
            }
            textViewtitle.setText(news.title);
            Log.d("url", news.urlToImage);
            Picasso.get().load(news.urlToImage).placeholder(R.drawable.loading).into(imageViewnews);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return convertView;
    }
}
