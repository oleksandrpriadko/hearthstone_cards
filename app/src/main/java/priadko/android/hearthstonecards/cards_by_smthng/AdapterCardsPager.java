package priadko.android.hearthstonecards.cards_by_smthng;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import priadko.android.hearthstonecards.cards_by_smthng.pager_item.FragBySmthPager;
import priadko.android.hearthstonecards.constants.BUNDLE;
import priadko.android.hearthstonecards.util.ManagerSharedPref;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

class AdapterCardsPager extends FragmentStatePagerAdapter {

    private List<String> itemNames;
    private ManagerSharedPref.INFO infoType;

    AdapterCardsPager(FragmentManager fm, ManagerSharedPref.INFO infoType, List<String> itemNames) {
        super(fm);
        this.itemNames = itemNames;
        this.infoType = infoType;
    }

    @Override
    public Fragment getItem(int position) {
        FragBySmthPager fragBySmthPager = new FragBySmthPager();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE.SERIALIZABLE.SMTH_ITEM_DATA_TYPE, infoType);
        bundle.putString(BUNDLE.STRING.SMTH_ITEM_NAME, itemNames.get(position));
        fragBySmthPager.setArguments(bundle);
        return fragBySmthPager;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return itemNames.get(position);
    }

    @Override
    public int getCount() {
        return itemNames.size();
    }


}
