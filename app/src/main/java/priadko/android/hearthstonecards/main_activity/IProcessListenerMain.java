package priadko.android.hearthstonecards.main_activity;

import priadko.android.hearthstonecards.main_activity.interactor.retrofit_model.ResponseInfo;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface IProcessListenerMain {

    void loadingStarted();

    void infoLoaded(ResponseInfo info);

    void loadingDone();

    void loadingError();
}
