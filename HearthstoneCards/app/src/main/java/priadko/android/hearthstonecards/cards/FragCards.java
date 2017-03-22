package priadko.android.hearthstonecards.cards;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import priadko.android.hearthstonecards.R;
import priadko.android.hearthstonecards.base.base_fragment.FragToolbarDrawerMain;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class FragCards extends FragToolbarDrawerMain {
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

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_cards;
    }

    @Override
    protected void prepareViews(View rootView) {
        ViewPager viewPager = ((ViewPager) rootView.findViewById(R.id.pager));
        AdapterCardsPager adapterCardsPager = new AdapterCardsPager(getFragmentManager());
        viewPager.setAdapter(adapterCardsPager);
        TabLayout tabLayout = ((TabLayout) rootView.findViewById(R.id.tabs));
        tabLayout.setupWithViewPager(viewPager);
    }
}
