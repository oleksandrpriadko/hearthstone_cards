package priadko.android.hearthstonecards.cards_back;

import com.android.priadko.baselibrary.base_mvp.BaseView;

import java.util.List;

import priadko.android.hearthstonecards.cards_back.interactor.retrofit.CardsBack;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface ViewCardsBack extends BaseView {

    void showLoading();

    void hideLoading();

    void cardsLoaded(List<CardsBack> cards);

    void showLoadingError();

}
