package com.example.mycoffeedreamproject_04;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    // 프래그먼트 교체를 보여주는 처리를 구현한 곳
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragMonday.newInstance();
            case 1:
                return FragTuesday.newInstance();
            case 2:
                return FragWednesday.newInstance();
            case 3:
                return FragThursday.newInstance();
            case 4:
                return FragFriday.newInstance();
            case 5:
                return FragEarly.newInstance();
            case 6:
                return FragEnd.newInstance();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 7;
    }

//Story Menu Franchise Academy Location MainMenu

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MainMenu";
            case 1:
                return "Story";
            case 2:
                return "Menu";
            case 3:
                return "Franchise";
            case 4:
                return "Academy";
            case 5:
                return "Location";
            case 6:
                return "MyInfo";
            default:
                return null;
        }
    }
}
