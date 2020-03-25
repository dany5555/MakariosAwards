package com.makariosawards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_layout);

        ImageView firstImageView, secondImageView;
        TextView title, dateAndAuthor;
        TextView firstParagraph, secondParagraph;

        firstImageView = findViewById(R.id.firstImageView);
        secondImageView = findViewById(R.id.secondImageView);

        title = findViewById(R.id.articleTitle);
        dateAndAuthor = findViewById(R.id.dateAndAuthor);

        firstParagraph = findViewById(R.id.firstParagraph);
        secondParagraph = findViewById(R.id.secondParagraph);

        firstParagraph.setText(getIntent().getStringExtra("firstParagraph"));
        secondParagraph.setText(getIntent().getStringExtra("secondParagraph"));

        title.setText(getIntent().getStringExtra("title"));
        dateAndAuthor.setText(getIntent().getStringExtra("dateAndAuthor"));



        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("firstImageUrl")).into(firstImageView);
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("secondImageUrl")).into(secondImageView);





    }
}
