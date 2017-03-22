package priadko.android.hearthstonecards.cards.all.interactor;

import android.support.annotation.NonNull;

import priadko.android.hearthstonecards.cards.all.IProcessListenerFragAll;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

interface InteractorFragAll {

    void getAllCards(@NonNull IProcessListenerFragAll listener);
}
