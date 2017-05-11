package priadko.android.hearthstonecards.cards_by_smthng.pager_item;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.priadko.baselibrary.base_fragment.mvp.FragBaseAbstractMVP;
import com.android.priadko.baselibrary.dialog_errors.ERROR_TYPES;

import java.util.List;

import priadko.android.hearthstonecards.R;
import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;
import priadko.android.hearthstonecards.cards_by_smthng.pager_item.presenter.PresenterBySmthImpl;
import priadko.android.hearthstonecards.constants.BUNDLE;
import priadko.android.hearthstonecards.util.recycler_view.ItemDecorVertGridSpac;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class FragBySmthPager
        extends FragBaseAbstractMVP<PresenterBySmthImpl, ViewBySmth>
        implements ViewBySmth {

    private static final int SPAN = 3;

    private FrameLayout layoutLoading;
    private FrameLayout layoutEmpty;
    private AdapterBySmth adapterBySmth;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  INIT
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected int getRootViewId() {
        return R.layout.fragment_by_smth_pager;
    }

    @NonNull
    @Override
    protected PresenterBySmthImpl getPresenter() {
        return new PresenterBySmthImpl(getString(R.string.base_url));
    }

    @NonNull
    @Override
    protected ViewBySmth getMVPView() {
        return this;
    }

    @Override
    public String getFragmentTag() {
        return super.getFragmentTag() + getArguments().getString(BUNDLE.STRING.SMTH_ITEM_NAME);
    }

    @Override
    protected void prepareViews(View rootView) {
        initLayoutLoading(rootView);
        initLayoutEmpty(rootView);
        initRecView(rootView);
        presenter.bundleReceived(getArguments());
        presenter.loadCards();
    }

    private void initLayoutLoading(View rootView) {
        layoutLoading = ((FrameLayout) rootView.findViewById(R.id.layout_loading));
    }

    private void initLayoutEmpty(View rootView) {
        layoutEmpty = ((FrameLayout) rootView.findViewById(R.id.layout_empty));
    }

    private void initRecView(View rootView) {
        adapterBySmth = new AdapterBySmth(itemListener, false);
        ItemDecorVertGridSpac decoration = new ItemDecorVertGridSpac(SPAN, 10, 30, 10, 30, true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN);
        RecyclerView recViewSmth = ((RecyclerView) rootView.findViewById(R.id.recView_by_smth));
        recViewSmth.addItemDecoration(decoration);
        recViewSmth.setAdapter(adapterBySmth);
        recViewSmth.setLayoutManager(layoutManager);
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
    public void cardsLoaded(List<Card> cards) {
        adapterBySmth.setData(cards);
    }

    @Override
    public void hideLoading() {
        showLoadingLayout(false);
    }

    @Override
    public void showLoadingError() {
        showErrorOk(ERROR_TYPES.WRONG_IN_SERVER);
        adapterBySmth.setData(null);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  ITEM LISTENER
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private AdapterBySmth.ItemListener itemListener = new AdapterBySmth.ItemListener() {
        @Override
        public void isEmpty(boolean isEmpty) {
            showEmptyLayout(isEmpty);
        }

        @Override
        public void itemClicked(int position, Card item) {
            Toast.makeText(getContext(), "pos " + position, Toast.LENGTH_SHORT).show();
        }
    };
}
