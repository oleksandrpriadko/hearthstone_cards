package priadko.android.hearthstonecards.base.base_mvp;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

interface BasePresenterInterface<T extends BaseView> {
    void bind(T view);

    void unBind();
}
