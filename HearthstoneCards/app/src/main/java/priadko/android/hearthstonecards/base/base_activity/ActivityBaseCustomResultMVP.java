package priadko.android.hearthstonecards.base.base_activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import priadko.android.hearthstonecards.base.base_mvp.BasePresenter;
import priadko.android.hearthstonecards.base.base_mvp.BaseView;
import priadko.android.hearthstonecards.base.base_mvp.PresenterManager;


/**
 * TimVision-Android-W12
 * Oleksandr Priadko
 */
public abstract class ActivityBaseCustomResultMVP<P extends BasePresenter<V>, V extends BaseView> extends ActivityBaseCustomResult {

    protected P presenter;

    @NonNull
    protected abstract P getPresenter();

    @NonNull
    protected abstract V getView();

    protected String getActivityTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = PresenterManager.getInstance().getFromCache(getActivityTag());
        if (presenter == null) {
            presenter = getPresenter();
            PresenterManager.getInstance().saveToCache(presenter, getActivityTag());
            Log.i("Presenter", "created " + getActivityTag());
        } else {
            Log.i("Presenter", "fromCache " + getActivityTag());
        }
        presenter.bind(getView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bind(getView());
    }

    @Override
    protected void onPause() {
        presenter.unBind();
        super.onPause();
    }

    @Override
    protected void onStop() {
        presenter.unBind();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.unBind();
        super.onDestroy();
    }
}
