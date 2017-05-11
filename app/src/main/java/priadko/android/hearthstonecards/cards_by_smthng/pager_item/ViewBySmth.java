package priadko.android.hearthstonecards.cards_by_smthng.pager_item;

import com.android.priadko.baselibrary.base_mvp.BaseView;

import java.util.List;

import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface ViewBySmth extends BaseView {

    void showLoading();

    void cardsLoaded(List<Card> cards);

    void hideLoading();

    void showLoadingError();

}
