package priadko.android.hearthstonecards.cards;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import priadko.android.hearthstonecards.cards.all.FragAll;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

class AdapterCardsPager extends FragmentStatePagerAdapter {
    private static final int COUNT = 1;

    private static final String ALL = "All";
    private static final String SEARCH = "Search";

    AdapterCardsPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragAll();
            case 1:
                return new FragDummyPager();
            default:
                return new FragAll();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return ALL;
            case 1:
                return SEARCH;
            default:
                return ALL;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }


}
