package priadko.android.hearthstonecards.cards_back;

import java.util.List;

import priadko.android.hearthstonecards.cards_back.interactor.retrofit.CardsBack;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface IProcessListenerCardsBack {

    void loadingStarted();

    void cardsBackLoaded(List<CardsBack> cardsBacks);

    void loadingDone();

    void loadingError();

}
