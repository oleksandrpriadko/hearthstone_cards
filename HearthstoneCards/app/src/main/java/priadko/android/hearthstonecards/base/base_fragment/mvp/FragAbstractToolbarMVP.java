package priadko.android.hearthstonecards.base.base_fragment.mvp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import priadko.android.hearthstonecards.base.base_mvp.BasePresenter;
import priadko.android.hearthstonecards.base.base_mvp.BaseView;

public abstract class FragAbstractToolbarMVP<P extends BasePresenter<V>, V extends BaseView> extends FragBaseAbstractMVP<P, V> {

    private Toolbar toolbar;

    /**
     * @return true - if Fragment have options menu, false - otherwise
     */
    protected abstract boolean ownOptionsMenu();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(ownOptionsMenu());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar(view);
    }

    /**
     * @return id of toolbar layout
     */
    @IdRes
    protected abstract int getToolbarId();

    /**
     * @return return your title for Fragment here.
     * By default title is empty
     */
    @Nullable
    protected abstract String getToolbarTitle();

    /**
     * @return return your subtitle for Fragment here.
     * By default subtitle is empty
     */
    @Nullable
    protected abstract String getToolbarSubtitle();

    /**
     * @return true - if toolbar have back button, false - otherwise
     */
    protected abstract boolean hasToolbarBackButton();

    /**
     * @return View.OnClickListener if toolbar have back button,
     * null - otherwise
     */
    @Nullable
    protected abstract View.OnClickListener getToolbarBackButtonCallback();

    protected Toolbar initToolbar(View rootView) {
        toolbar = ((Toolbar) rootView.findViewById(getToolbarId()));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        if (getToolbarTitle() != null) {
            //noinspection ConstantConditions
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getToolbarTitle());

        }
        if (getToolbarSubtitle() != null) {
            //noinspection ConstantConditions
            ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(getToolbarSubtitle());
        }
        if (hasToolbarBackButton()) {
            //noinspection ConstantConditions
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //noinspection ConstantConditions
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
            View.OnClickListener toolbarBackButtonCallback = getToolbarBackButtonCallback();
            if (toolbarBackButtonCallback != null)
                toolbar.setNavigationOnClickListener(toolbarBackButtonCallback);
        }
        return toolbar;
    }

    /**
     * Set toolbar title in Runtime
     *
     * @param title title fot toolbar
     */
    protected void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

}
