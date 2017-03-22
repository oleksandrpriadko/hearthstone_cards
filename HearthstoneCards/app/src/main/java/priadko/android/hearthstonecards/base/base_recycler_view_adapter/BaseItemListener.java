package priadko.android.hearthstonecards.base.base_recycler_view_adapter;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface BaseItemListener<T> {
    void isEmpty(boolean isEmpty);

    void itemClicked(int position, T item);
}
