package priadko.android.hearthstonecards.cards_back.interactor;

import android.support.annotation.NonNull;

import com.android.priadko.baselibrary.base_mvp.RetrofitInteractor;
import com.android.priadko.baselibrary.base_retrofit.HeaderInterceptor;
import com.android.priadko.baselibrary.base_retrofit.HeaderInterceptorMashape;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import priadko.android.hearthstonecards.cards_back.IProcessListenerCardsBack;
import priadko.android.hearthstonecards.cards_back.interactor.retrofit.ApiCardsBack;
import priadko.android.hearthstonecards.cards_back.interactor.retrofit.CardsBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class InteractorCardsBackImpl
        extends RetrofitInteractor<List<CardsBack>>
        implements InteractorCardsBack {

    private static final int CACHE_SIZE_MB = 10;
    private static final int CACHE_EXPIRATION = 240;

    public InteractorCardsBackImpl(String baseUrl) {
        super(baseUrl);
    }

    @NonNull
    @Override
    protected CACHE_TYPE getCacheType() {
        return CACHE_TYPE.HAS_CACHE;
    }

    @Override
    protected int getCacheSizeMB() {
        return CACHE_SIZE_MB;
    }

    @Override
    protected int getExpTime() {
        return CACHE_EXPIRATION;
    }

    @NonNull
    @Override
    protected TimeUnit getExpTimeUnit() {
        return TimeUnit.SECONDS;
    }

    @Override
    protected HeaderInterceptor[] getInterceptors() {
        HeaderInterceptorMashape interceptorMashape = new HeaderInterceptorMashape();
        return new HeaderInterceptor[] {interceptorMashape};
    }

    @NonNull
    @Override
    protected HttpLoggingInterceptor.Level getLoggingLevel() {
        return HttpLoggingInterceptor.Level.BODY;
    }

    @Override
    public void getCardsBack(final IProcessListenerCardsBack listener) {
        listener.loadingStarted();
        List<CardsBack> cardBacks = getCachedData();
        if (isDataValid()){
            listener.cardsBackLoaded(cardBacks);
            listener.loadingDone();
            return;
        }
        getApi(ApiCardsBack.class).getCardsBack().enqueue(new Callback<List<CardsBack>>() {
            @Override
            public void onResponse(Call<List<CardsBack>> call, Response<List<CardsBack>> response) {
                if (response.isSuccessful()) {
                    listener.cardsBackLoaded(response.body());
                    saveToCache(response.body());
                } else {
                    listener.loadingError();
                }
                listener.loadingDone();
            }

            @Override
            public void onFailure(Call<List<CardsBack>> call, Throwable t) {
                listener.loadingError();
                listener.loadingDone();
            }
        });
    }
}
