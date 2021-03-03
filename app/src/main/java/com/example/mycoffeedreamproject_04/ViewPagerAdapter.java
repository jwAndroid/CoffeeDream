package com.example.mycoffeedreamproject_04;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mycoffeedreamproject_04.Fragment.FragEarly;
import com.example.mycoffeedreamproject_04.Fragment.FragEnd;
import com.example.mycoffeedreamproject_04.Fragment.FragFriday;
import com.example.mycoffeedreamproject_04.Fragment.FragMonday;
import com.example.mycoffeedreamproject_04.Fragment.FragThursday;
import com.example.mycoffeedreamproject_04.Fragment.FragTuesday;
import com.example.mycoffeedreamproject_04.Fragment.FragWednesday;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    /**/
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    /*간단히 아이템(Fragment) position 를 가지고서 newInstance() > 해당 프래그먼트 객체 반환*/
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

            default: return null;
        }

    }

    /*아이템 개수 반환 */
    @Override
    public int getCount() {
        return 7;
    }

    /*타이틀 반환 */
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
