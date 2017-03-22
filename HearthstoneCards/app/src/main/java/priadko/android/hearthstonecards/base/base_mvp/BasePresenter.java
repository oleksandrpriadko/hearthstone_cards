package priadko.android.hearthstonecards.base.base_mvp;

import java.lang.ref.WeakReference;

/**
 * TimVision-Android-W12
 * Oleksandr Priadko
 */

public abstract class BasePresenter<T extends BaseView> implements BasePresenterInterface<T>{
    private WeakReference<T> view;

    protected T getView() {
        return this.view.get();
    }

    protected void bindView(T view) {
        setView(view);
    }

    protected void setView(T view) {
        this.view = new WeakReference<>(view);
    }

    protected void unBindView() {
        clearView();
    }

    private void clearView() {
        if (this.view != null && this.view.get() != null) {
            this.view.clear();
            this.view = null;
        }
    }

    protected boolean isViewAdded() {
        return view != null && view.get() != null;
    }
}
