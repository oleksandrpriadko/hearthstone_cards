package priadko.android.hearthstonecards.base.base_mvp;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class PresenterManager {
    private static final int MAX_SIZE = 10;
    private static final long EXPIRATION_VALUE = 3;
    private static PresenterManager instance;

    private final Cache<String, BasePresenter<? extends BaseView>> presenters;

    private PresenterManager(long maxSize, long expirationValue, TimeUnit expirationUnit) {
        presenters = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(expirationValue, expirationUnit)
                .build();
    }

    public static PresenterManager getInstance() {
        if (instance == null) {
            instance = new PresenterManager(MAX_SIZE, EXPIRATION_VALUE, TimeUnit.MINUTES);
        }
        return instance;
    }

    public void saveToCache(BasePresenter<? extends BaseView> presenter, String key) {
        presenters.put(key, presenter);
    }

    public <P extends BasePresenter<? extends BaseView>> P getFromCache(String key) {

//        presenters.invalidate(key);
        //noinspection unchecked
        return (P) presenters.getIfPresent(key);
    }
}