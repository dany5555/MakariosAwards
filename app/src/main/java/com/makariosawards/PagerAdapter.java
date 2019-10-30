package com.makariosawards;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BestDirectivaMemberFragment tab1 = new BestDirectivaMemberFragment();
                return tab1;
            case 1:
                BestGringoFragment tab2 = new BestGringoFragment();
                return tab2;
            case 2:
                BestSmileFragment tab3 = new BestSmileFragment();
                return tab3;
            case 3:
                FirstInLineForPotluckFragment tab4 = new FirstInLineForPotluckFragment();
                return tab4;
            case 4:
                KindestFragment tab5 = new KindestFragment();
                return tab5;
            case 5:
                LaziestPersonInDramaFragment tab6 = new LaziestPersonInDramaFragment();
                return tab6;
            case 6:
                LaziestPersonInMusicFragment tab7 = new LaziestPersonInMusicFragment();
                return tab7;
            case 7:
                LaziestPersonInPoetryFragment tab8 = new LaziestPersonInPoetryFragment();
                return tab8;
            case 8:
                LoudestLaughFragment tab9 = new LoudestLaughFragment();
                return tab9;
            case 9:
                MostDramaticFragment tab10 = new MostDramaticFragment();
                return tab10;
            case 10:
                MostPassionateFragment tab11 = new MostPassionateFragment();
                return tab11;
            case 11:
                MostPunctualFragment tab12 = new MostPunctualFragment();
                return tab12;
            case 12:
                QuietestFragment tab13 = new QuietestFragment();
                return tab13;
            case 13:
                SleepiestFragment tab14 = new SleepiestFragment();
                return tab14;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}
