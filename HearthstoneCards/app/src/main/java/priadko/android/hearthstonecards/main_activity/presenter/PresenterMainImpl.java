package priadko.android.hearthstonecards.main_activity.presenter;

import priadko.android.hearthstonecards.base.base_mvp.BasePresenter;
import priadko.android.hearthstonecards.main_activity.IProcessListenerMain;
import priadko.android.hearthstonecards.main_activity.ViewMainActivity;
import priadko.android.hearthstonecards.main_activity.interactor.InteractorMainImpl;
import priadko.android.hearthstonecards.main_activity.interactor.retrofit_model.ResponseInfo;
import priadko.android.hearthstonecards.util.ManagerSharedPref;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class PresenterMainImpl
        extends BasePresenter<ViewMainActivity>
        implements PresenterMain {

    private InteractorMainImpl interactor;

    public PresenterMainImpl(String baseUrl) {
        if (interactor == null) {
            interactor = new InteractorMainImpl(baseUrl);
        }
    }

    @Override
    public void bind(ViewMainActivity view) {
        bindView(view);
    }

    @Override
    public void unBind() {
        unBindView();
    }

    @Override
    public void getInfo(ManagerSharedPref managerSharedPref) {
        interactor.getInfo(listener);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  LISTENER
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private IProcessListenerMain listener = new IProcessListenerMain() {
        @Override
        public void loadingStarted() {
            if (isViewAdded()) {
                getView().showLoading();
            }
        }

        @Override
        public void infoLoaded(ResponseInfo info) {
            if (isViewAdded()) {
                getView().infoReceived(info);
                getView().showFragAllCards();
            }
        }

        @Override
        public void loadingDone() {
            if (isViewAdded()) {
                getView().hideLoading();
            }
        }

        @Override
        public void loadingError() {
            if (isViewAdded()) {
                getView().showLoadingError();
            }
        }
    };
}
