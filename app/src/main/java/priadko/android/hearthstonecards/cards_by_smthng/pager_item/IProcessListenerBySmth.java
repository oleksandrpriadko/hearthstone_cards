package priadko.android.hearthstonecards.cards_by_smthng.pager_item;

import java.util.List;

import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;
import priadko.android.hearthstonecards.util.ManagerSharedPref;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface IProcessListenerBySmth {

    void typeGot(ManagerSharedPref.INFO data_type, String name);

    void loadingStarted();

    void cardsLoaded(List<Card> cards);

    void loadingError();

    void loadingDone();
}
