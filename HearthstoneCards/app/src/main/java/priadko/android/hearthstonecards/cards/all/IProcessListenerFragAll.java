package priadko.android.hearthstonecards.cards.all;

import java.util.List;

import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface IProcessListenerFragAll {

    void loadingStarted();

    void loadingError();

    void cardsLoaded(List<Card> cards);

    void loadingDone();
}
