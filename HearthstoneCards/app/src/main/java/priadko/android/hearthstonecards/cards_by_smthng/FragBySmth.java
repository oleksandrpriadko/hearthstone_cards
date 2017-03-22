package priadko.android.hearthstonecards.cards_by_smthng;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import priadko.android.hearthstonecards.R;
import priadko.android.hearthstonecards.base.base_fragment.FragBaseAbstract;
import priadko.android.hearthstonecards.base.base_fragment.FragToolbarDrawerMain;
import priadko.android.hearthstonecards.base.constants.BUNDLE;
import priadko.android.hearthstonecards.util.ManagerSharedPref;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class FragBySmth extends FragToolbarDrawerMain {

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_by_smth;
    }

    @Override
    protected void prepareViews(View rootView) {
        ManagerSharedPref.INFO dataType;
        if (getArguments() != null){
            dataType = ((ManagerSharedPref.INFO) getArguments()
                    .getSerializable(BUNDLE.SERIALIZABLE.SMTH_ITEM_DATA_TYPE));
        } else {
            dataType = ManagerSharedPref.INFO.CLASSES;
        }
        List<String> itemNames = new ArrayList<>(
                ManagerSharedPref.with(this).getInfo(dataType));
        ViewPager viewPager = ((ViewPager) rootView.findViewById(R.id.pager_by_smth));
        AdapterCardsPager adapterCardsPager = new AdapterCardsPager(
                getFragmentManager(), dataType, itemNames);
        viewPager.setAdapter(adapterCardsPager);
        TabLayout tabLayout = ((TabLayout) rootView.findViewById(R.id.tabs_by_smth));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getDrawerId() {
        return R.id.drawer_layout;
    }

    @Override
    protected int getDrawerOpenRes() {
        return R.string.navigation_drawer_open;
    }

    @Override
    protected int getDrawerClosedRes() {
        return R.string.navigation_drawer_close;
    }

    @Override
    protected boolean ownOptionsMenu() {
        return false;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Nullable
    @Override
    protected String getToolbarTitle() {
        return getFragmentTag();
    }

    @Nullable
    @Override
    protected String getToolbarSubtitle() {
        return null;
    }

    @Override
    protected boolean hasToolbarBackButton() {
        return false;
    }

    @Nullable
    @Override
    protected View.OnClickListener getToolbarBackButtonCallback() {
        return null;
    }
}
