package priadko.android.hearthstonecards.cards_back.presenter;

import com.android.priadko.baselibrary.base_mvp.BasePresenter;

import java.util.List;

import priadko.android.hearthstonecards.cards_back.IProcessListenerCardsBack;
import priadko.android.hearthstonecards.cards_back.ViewCardsBack;
import priadko.android.hearthstonecards.cards_back.interactor.InteractorCardsBackImpl;
import priadko.android.hearthstonecards.cards_back.interactor.retrofit.CardsBack;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class PresenterCardsBackImpl
        extends BasePresenter<ViewCardsBack>
        implements PresenterCardsBack {

    private InteractorCardsBackImpl interactor;

    public PresenterCardsBackImpl(String baseUrl) {
        if (interactor == null){
            interactor = new InteractorCardsBackImpl(baseUrl);
        }
    }

    @Override
    public void getCardsBack() {
        interactor.getCardsBack(listener);
    }

    @Override
    public void bind(ViewCardsBack view) {
        bindView(view);
    }

    @Override
    public void unBind() {
        unBindView();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  LISTENER
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private IProcessListenerCardsBack listener = new IProcessListenerCardsBack() {
        @Override
        public void loadingStarted() {
            if (isViewAdded()){
                getView().showLoading();
            }
        }

        @Override
        public void cardsBackLoaded(List<CardsBack> cardsBacks) {
            if (isViewAdded()){
                getView().cardsLoaded(cardsBacks);
            }
        }

        @Override
        public void loadingDone() {
            if (isViewAdded()){
                getView().hideLoading();
            }
        }

        @Override
        public void loadingError() {
            if (isViewAdded()){
                getView().showLoadingError();
            }
        }
    };
}
