package priadko.android.hearthstonecards.cards.all.presenter;

import com.android.priadko.baselibrary.base_mvp.BasePresenter;

import java.util.List;

import priadko.android.hearthstonecards.cards.all.IProcessListenerFragAll;
import priadko.android.hearthstonecards.cards.all.ViewFragAll;
import priadko.android.hearthstonecards.cards.all.interactor.InteractorFragAllImpl;
import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class PresenterFragAllImpl
        extends BasePresenter<ViewFragAll>
        implements PresenterFragAll {
    private InteractorFragAllImpl interactor;

    public PresenterFragAllImpl(String baseUrl) {
        if (interactor == null){
            interactor = new InteractorFragAllImpl(baseUrl);
        }
    }

    @Override
    public void bind(ViewFragAll view) {
        bindView(view);
    }

    @Override
    public void getCards() {
        interactor.getAllCards(listener);
    }

    @Override
    public void unBind() {
        unBindView();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //  LISTENER
    //////////////////////////////////////////////////////////////////////////////////////////////
    private IProcessListenerFragAll listener = new IProcessListenerFragAll() {
        @Override
        public void loadingStarted() {
            if (isViewAdded()){
                getView().showLoading();
            }
        }

        @Override
        public void loadingError() {
            if (isViewAdded()){
                getView().showLoadingError();
            }
        }

        @Override
        public void cardsLoaded(List<Card> cards) {
            if (isViewAdded()){
                getView().cardsLoaded(cards);
            }
        }

        @Override
        public void loadingDone() {
            if (isViewAdded()){
                getView().hideLoading();
            }
        }
    };
}
