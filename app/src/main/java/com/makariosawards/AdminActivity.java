package com.makariosawards;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This Activity serves as our main activity for the admin side of the app.
 * Here, the admin is able to see the realtime total votes for each category.
 */
public class AdminActivity extends AppCompatActivity {

    // Declare my classes.
    TabLayout categoriesTabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    // Set the tab layout initial page to 0.
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Cast my tab layout to the UI element.
        categoriesTabLayout = findViewById(R.id.tabLayout);

        // Adding all of the tabs along with their names to the tab layout.
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

        // Setting some tab layout properties.
        categoriesTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        categoriesTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        // Cast my view pager to the UI element.
        viewPager = findViewById(R.id.viewpager);

        // Setting the pager adapter for the view pager that will be displaying the information.
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
