package priadko.android.hearthstonecards.cards_back;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import priadko.android.hearthstonecards.R;
import priadko.android.hearthstonecards.base.base_fragment.mvp.FragToolbarDrawerMainMVP;
import priadko.android.hearthstonecards.base.dialog_errors.ERROR_TYPES;
import priadko.android.hearthstonecards.cards_back.interactor.retrofit.CardsBack;
import priadko.android.hearthstonecards.cards_back.presenter.PresenterCardsBackImpl;
import priadko.android.hearthstonecards.util.recycler_view.ItemDecorVertGridSpac;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class FragCardsBack extends FragToolbarDrawerMainMVP<PresenterCardsBackImpl, ViewCardsBack> implements ViewCardsBack {

    private static final int SPAN = 3;

    private FrameLayout layoutLoading;
    private FrameLayout layoutEmpty;
    private AdapterCardsBack adapterCardsBack;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  INIT
    ///////////////////////////////////////////////////////////////////////////////////////////////

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
        return R.layout.fragment_cards_back;
    }

    @NonNull
    @Override
    protected PresenterCardsBackImpl getPresenter() {
        return presenter = new PresenterCardsBackImpl(getString(R.string.base_url));
    }

    @NonNull
    @Override
    protected ViewCardsBack getMVPView() {
        return this;
    }

    @Override
    protected void prepareViews(View rootView) {
        initRecViewAll(rootView);
        initLayoutLoading(rootView);
        initLayoutEmpty(rootView);
        presenter.bind(this);
        presenter.getCardsBack();
    }

    private void initLayoutLoading(View rootView) {
        layoutLoading = ((FrameLayout) rootView.findViewById(R.id.layout_loading));
    }

    private void initLayoutEmpty(View rootView) {
        layoutEmpty = ((FrameLayout) rootView.findViewById(R.id.layout_empty));
    }

    private void initRecViewAll(View rootView) {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), SPAN);
        adapterCardsBack = new AdapterCardsBack(itemListener, false);
        ItemDecorVertGridSpac decoration = new ItemDecorVertGridSpac(SPAN, 10, 30, 10, 30, true);
        RecyclerView recViewCardsBack = ((RecyclerView) rootView.findViewById(R.id.recView_cards_back));
        recViewCardsBack.addItemDecoration(decoration);
        recViewCardsBack.setLayoutManager(layoutManager);
        recViewCardsBack.setAdapter(adapterCardsBack);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  ACTIONS
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private void showLoadingLayout(boolean show) {
        layoutLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showEmptyLayout(boolean show) {
        layoutEmpty.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showLoading() {
        showLoadingLayout(true);
    }

    @Override
    public void hideLoading() {
        showLoadingLayout(false);
    }

    @Override
    public void cardsLoaded(List<CardsBack> cards) {
        adapterCardsBack.setData(cards);
    }

    @Override
    public void showLoadingError() {
        showErrorOk(ERROR_TYPES.WRONG_IN_SERVER);
    }

    private AdapterCardsBack.ItemListener itemListener = new AdapterCardsBack.ItemListener() {
        @Override
        public void isEmpty(boolean empty) {
            showEmptyLayout(empty);
        }

        @Override
        public void itemClicked(int position, CardsBack item) {
            Toast.makeText(getContext(), "pos " + position, Toast.LENGTH_SHORT).show();
        }

    };
}
