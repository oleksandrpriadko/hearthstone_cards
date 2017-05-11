package priadko.android.hearthstonecards.cards.all;

import com.android.priadko.baselibrary.base_mvp.BaseView;

import java.util.List;

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
