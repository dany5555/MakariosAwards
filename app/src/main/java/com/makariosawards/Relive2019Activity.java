package com.makariosawards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Int2;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class Relive2019Activity extends AppCompatActivity {

    Toolbar toolbar;
    Button reliveButton, nominees2019Button;
    ImageView background;

    String url = "https://lh3.googleusercontent.com/bEJzExKEjvGdG5G7w1Q0w8nkc9vIBWxs3XeaFWQ_POqSWkCpqSW-8umOnrn8QD5XVAFFx4dsRGrq0hNiOI_RdSCmeDgEB4PsivKgw-EKJ_R6lZcNYo49aA8SDZf3NHwv7jD0JKbqBQ=w2108-h1245-no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relive2019);

        toolbar = findViewById(R.id.toolbar);
        reliveButton = findViewById(R.id.relivebutton);
        nominees2019Button = findViewById(R.id.nominees2019Button);
        background = findViewById(R.id.imageView12);

        Glide.with(this).load(url).transition(withCrossFade()).into(background);


        setSupportActionBar(toolbar);

        // Display icon in the toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_makarios_awards_logo_with_insignia_horizontal_taller);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        reliveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Take me to activity.
                Intent intent = new Intent(getApplicationContext(), Relive2019AwardsActivity.class);
                startActivity(intent);
            }
        });

        nominees2019Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Take me to activity.
                Intent intent = new Intent(getApplicationContext(), Relive2019NomineesActivity.class);
                startActivity(intent);
            }
        });





    }
}
