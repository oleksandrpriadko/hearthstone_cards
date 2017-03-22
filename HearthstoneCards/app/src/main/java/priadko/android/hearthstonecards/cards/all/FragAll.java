package priadko.android.hearthstonecards.cards.all;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import priadko.android.hearthstonecards.R;
import priadko.android.hearthstonecards.base.base_fragment.mvp.FragBaseAbstractMVP;
import priadko.android.hearthstonecards.base.dialog_errors.ERROR_TYPES;
import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;
import priadko.android.hearthstonecards.cards.all.presenter.PresenterFragAllImpl;
import priadko.android.hearthstonecards.util.recycler_view.ItemDecorVertGridSpac;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class FragAll extends FragBaseAbstractMVP<PresenterFragAllImpl, ViewFragAll> implements ViewFragAll {
    private static final int SPAN = 3;

    private FrameLayout layoutLoading;
    private FrameLayout layoutEmpty;
    private AdapterAll adapterAll;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  INIT
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_all;
    }

    @NonNull
    @Override
    protected PresenterFragAllImpl getPresenter() {
        return presenter = new PresenterFragAllImpl(getString(R.string.base_url));
    }

    @NonNull
    @Override
    protected ViewFragAll getMVPView() {
        return this;
    }

    @Override
    protected void prepareViews(View rootView) {
        initRecViewAll(rootView);
        initLayoutLoading(rootView);
        initLayoutEmpty(rootView);
        presenter.bind(this);
        presenter.getCards();
    }

    private void initLayoutLoading(View rootView) {
        layoutLoading = ((FrameLayout) rootView.findViewById(R.id.layout_loading));
    }

    private void initLayoutEmpty(View rootView) {
        layoutEmpty = ((FrameLayout) rootView.findViewById(R.id.layout_empty));
    }

    private void initRecViewAll(View rootView) {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), SPAN);
        adapterAll = new AdapterAll(itemListener, false);
        ItemDecorVertGridSpac decoration = new ItemDecorVertGridSpac(SPAN, 10, 30, 10, 30, true);
        RecyclerView recViewAll = ((RecyclerView) rootView.findViewById(R.id.recView_all_cards));
        recViewAll.addItemDecoration(decoration);
        recViewAll.setLayoutManager(layoutManager);
        recViewAll.setAdapter(adapterAll);
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
    public void cardsLoaded(List<Card> cards) {
        adapterAll.setData(cards);
    }

    @Override
    public void showLoadingError() {
        showErrorOk(ERROR_TYPES.WRONG_IN_SERVER);
    }

    private AdapterAll.ItemListener itemListener = new AdapterAll.ItemListener() {
        @Override
        public void isEmpty(boolean empty) {
            showEmptyLayout(empty);
        }

        @Override
        public void itemClicked(int position, Card card) {
            Toast.makeText(getContext(), "pos " + position, Toast.LENGTH_SHORT).show();
        }
    };
}
