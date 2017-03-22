package priadko.android.hearthstonecards.cards.all;

import java.util.List;

import priadko.android.hearthstonecards.base.base_mvp.BaseView;
import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface ViewFragAll extends BaseView {

    void showLoading();

    void hideLoading();

    void cardsLoaded(List<Card> cards);

    void showLoadingError();
}
