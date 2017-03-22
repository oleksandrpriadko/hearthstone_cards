package priadko.android.hearthstonecards;

import android.support.annotation.Nullable;
import android.view.View;

import priadko.android.hearthstonecards.base.base_fragment.FragToolbarDrawerMain;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class FragDummy extends FragToolbarDrawerMain {
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
        return "Dummy";
    }

    @Nullable
    @Override
    protected String getToolbarSubtitle() {
        return "Subtitle Dummy";
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
        return R.layout.fragment_dummy;
    }

    @Override
    protected void prepareViews(View rootView) {

    }
}
