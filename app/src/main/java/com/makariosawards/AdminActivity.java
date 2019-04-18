package com.makariosawards;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {

    TabLayout categoriesTabLayout;
    ViewPager viewPager;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference bestDirectivaMember = database.getReference("Categories").child("Best Directiva Member");
    DatabaseReference bestGringo = database.getReference("Categories").child("Best Gringo");
    DatabaseReference bestSmile = database.getReference("Categories").child("Best Smile");
    DatabaseReference firstInLineForPotluck = database.getReference("Categories").child("First in Line for Potluck");
    DatabaseReference kindest = database.getReference("Categories").child("Kindest");
    DatabaseReference laziestPersonInDrama = database.getReference("Categories").child("Laziest Person in Drama");
    DatabaseReference laziestPersonInMusic = database.getReference("Categories").child("Laziest Person in Music");
    DatabaseReference laziestPersonInPoetry = database.getReference("Categories").child("Laziest Person in Poetry");
    DatabaseReference loudestLaugh = database.getReference("Categories").child("Loudest Laugh");
    DatabaseReference mostDramatic = database.getReference("Categories").child("Most Dramatic");
    DatabaseReference mostPassionate = database.getReference("Categories").child("Most Passionate");
    DatabaseReference mostPunctual = database.getReference("Categories").child("Most Punctual");
    DatabaseReference quietest = database.getReference("Categories").child("Quietest");
    DatabaseReference sleepiest = database.getReference("Categories").child("Sleepiest");

    int currentPage = 0;
    int currentTabDisplayed;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        categoriesTabLayout = findViewById(R.id.tabLayout);

        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Best Directiva Member"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Best Gringo"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Best Smile"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("First in Line for Potluck"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Kindest"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Laziest Person in Drama"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Laziest Person in Music"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Laziest Person in Poetry"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Loudest Laugh"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Most Dramatic"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Most Passionate"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Most Punctual"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Quietest"));
        categoriesTabLayout.addTab(categoriesTabLayout.newTab().setText("Sleepiest"));

        categoriesTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        categoriesTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        viewPager = findViewById(R.id.viewpager);


    }
}
