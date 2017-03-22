package priadko.android.hearthstonecards.base.base_fragment.mvp;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import priadko.android.hearthstonecards.base.base_mvp.BasePresenter;
import priadko.android.hearthstonecards.base.base_mvp.BaseView;

public abstract class FragToolbarDrawerMainMVP<P extends BasePresenter<V>, V extends BaseView> extends FragAbstractToolbarMVP<P, V> {

    @Override
    protected Toolbar initToolbar(View rootView) {
        Toolbar toolbar = super.initToolbar(rootView);
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(getDrawerId());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, getDrawerOpenRes(), getDrawerClosedRes());
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        return toolbar;
    }

    /**
     * @return drawer id here and it will be used for set up toolbar
     */
    @IdRes
    protected abstract int getDrawerId();

    /**
     * @return string resource when drawer is open
     */
    @StringRes
    protected abstract int getDrawerOpenRes();

    /**
     * @return string resource when drawer is closed
     */
    @StringRes
    protected abstract int getDrawerClosedRes();
}
