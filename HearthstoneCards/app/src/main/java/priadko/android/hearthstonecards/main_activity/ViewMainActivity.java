package priadko.android.hearthstonecards.main_activity;

import priadko.android.hearthstonecards.base.base_mvp.BaseView;
import priadko.android.hearthstonecards.main_activity.interactor.retrofit_model.ResponseInfo;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface ViewMainActivity extends BaseView {

    void showLoading();

    void infoReceived(ResponseInfo info);

    void showFragAllCards();

    void hideLoading();

    void showLoadingError();

}
