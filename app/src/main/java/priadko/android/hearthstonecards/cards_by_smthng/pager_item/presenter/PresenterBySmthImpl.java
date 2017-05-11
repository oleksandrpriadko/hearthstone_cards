package priadko.android.hearthstonecards.cards_by_smthng.pager_item.presenter;

import android.os.Bundle;

import com.android.priadko.baselibrary.base_mvp.BasePresenter;

import java.util.List;

import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;
import priadko.android.hearthstonecards.cards_by_smthng.pager_item.IProcessListenerBySmth;
import priadko.android.hearthstonecards.cards_by_smthng.pager_item.ViewBySmth;
import priadko.android.hearthstonecards.cards_by_smthng.pager_item.interactor.InteractorBySmth;
import priadko.android.hearthstonecards.cards_by_smthng.pager_item.interactor.InteractorBySmthImpl;
import priadko.android.hearthstonecards.util.ManagerSharedPref;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class PresenterBySmthImpl
        extends BasePresenter<ViewBySmth>
        implements PresenterBySmth {

    private InteractorBySmth interactor;
    private ManagerSharedPref.INFO dataType;
    private String name;

    public PresenterBySmthImpl(String baseUrl) {
        if (interactor == null) {
            interactor = new InteractorBySmthImpl(baseUrl);
        }
    }

    @Override
    public void bind(ViewBySmth view) {
        bindView(view);
    }

    @Override
    public void unBind() {
        unBindView();
    }

    @Override
    public void bundleReceived(Bundle bundle) {
        interactor.processBundle(bundle, listener);
    }

    @Override
    public void loadCards() {
        interactor.loadCards(dataType, name, listener);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  LISTENER
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private IProcessListenerBySmth listener = new IProcessListenerBySmth() {
        @Override
        public void typeGot(ManagerSharedPref.INFO dataType, String name) {
            PresenterBySmthImpl.this.dataType = dataType;
            PresenterBySmthImpl.this.name = name;
        }

        @Override
        public void loadingStarted() {
            if (isViewAdded()) {
                getView().showLoading();
            }
        }

        @Override
        public void cardsLoaded(List<Card> cards) {
            if (isViewAdded()) {
                getView().cardsLoaded(cards);
            }
        }

        @Override
        public void loadingError() {
            if (isViewAdded()) {
                getView().showLoadingError();
            }
        }

        @Override
        public void loadingDone() {
            if (isViewAdded()) {
                getView().hideLoading();
            }
        }
    };
}
