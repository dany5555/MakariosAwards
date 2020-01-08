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

        ImageView primaryImageView, secondaryImageView;
        TextView title, dateAndAuthor;
        TextView paragraph1, paragraph2, paragraph3, paragraph4, paragraph5, paragraph6, paragraph7, paragraph8;

        primaryImageView = findViewById(R.id.primaryImageView);
        secondaryImageView = findViewById(R.id.secondaryImageView);

        title = findViewById(R.id.articleTitle);
        dateAndAuthor = findViewById(R.id.dateAndAuthor);

        paragraph1 = findViewById(R.id.paragraph1);
        paragraph2 = findViewById(R.id.paragraph2);
        paragraph3 = findViewById(R.id.paragraph3);
        paragraph4 = findViewById(R.id.paragraph4);
        paragraph5 = findViewById(R.id.paragraph5);
        paragraph6 = findViewById(R.id.paragraph6);
        paragraph7 = findViewById(R.id.paragraph7);
        paragraph8 = findViewById(R.id.paragraph8);


        if (getIntent().getStringExtra("paragraph1").equals("no text")) {
            paragraph1.setVisibility(View.GONE);
        } else {
            paragraph1.setVisibility(View.VISIBLE);
            paragraph1.setText(getIntent().getStringExtra("paragraph1"));
        }

        if (getIntent().getStringExtra("paragraph2").equals("no text")) {
            paragraph2.setVisibility(View.GONE);
        } else {
            paragraph2.setVisibility(View.VISIBLE);
            paragraph2.setText(getIntent().getStringExtra("paragraph2"));
        }

        if (getIntent().getStringExtra("paragraph3").equals("no text")) {
            paragraph3.setVisibility(View.GONE);
        } else {
            paragraph3.setVisibility(View.VISIBLE);
            paragraph3.setText(getIntent().getStringExtra("paragraph3"));
        }

        if (getIntent().getStringExtra("paragraph4").equals("no text")) {
            paragraph4.setVisibility(View.GONE);
        } else {
            paragraph4.setVisibility(View.VISIBLE);
            paragraph4.setText(getIntent().getStringExtra("paragraph4"));
        }

        if (getIntent().getStringExtra("paragraph5").equals("no text")) {
            paragraph5.setVisibility(View.GONE);
        } else {
            paragraph5.setVisibility(View.VISIBLE);
            paragraph5.setText(getIntent().getStringExtra("paragraph5"));
        }

        if (getIntent().getStringExtra("paragraph6").equals("no text")) {
            paragraph6.setVisibility(View.GONE);
        } else {
            paragraph6.setVisibility(View.VISIBLE);
            paragraph6.setText(getIntent().getStringExtra("paragraph6"));
        }

        if (getIntent().getStringExtra("paragraph7").equals("no text")) {
            paragraph7.setVisibility(View.GONE);
        } else {
            paragraph7.setVisibility(View.VISIBLE);
            paragraph7.setText(getIntent().getStringExtra("paragraph7"));
        }

        if (getIntent().getStringExtra("paragraph8").equals("no text")) {
            paragraph8.setVisibility(View.GONE);
        } else {
            paragraph8.setVisibility(View.VISIBLE);
            paragraph8.setText(getIntent().getStringExtra("paragraph8"));
        }

        title.setText(getIntent().getStringExtra("title"));
        dateAndAuthor.setText(getIntent().getStringExtra("dateAndAuthor"));



        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("primaryImageUrl")).into(primaryImageView);
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("secondaryImageUrl")).into(secondaryImageView);





    }
}
