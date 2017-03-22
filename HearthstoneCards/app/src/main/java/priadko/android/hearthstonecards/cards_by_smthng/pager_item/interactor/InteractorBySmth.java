package priadko.android.hearthstonecards.cards_by_smthng.pager_item.interactor;

import android.os.Bundle;

import priadko.android.hearthstonecards.cards_by_smthng.pager_item.IProcessListenerBySmth;
import priadko.android.hearthstonecards.util.ManagerSharedPref;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface InteractorBySmth {

    void processBundle(Bundle bundle, IProcessListenerBySmth listener);

    void loadCards(ManagerSharedPref.INFO dataType, String smthName, IProcessListenerBySmth listener);

}
