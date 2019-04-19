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
    PagerAdapter pagerAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

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
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), categoriesTabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(categoriesTabLayout));
        categoriesTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                currentPage = viewPager.getCurrentItem();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
