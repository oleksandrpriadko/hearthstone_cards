package priadko.android.hearthstonecards.cards.all.interactor;

import android.support.annotation.NonNull;

import com.android.priadko.baselibrary.base_mvp.RetrofitInteractor;
import com.android.priadko.baselibrary.base_retrofit.HeaderInterceptor;
import com.android.priadko.baselibrary.base_retrofit.HeaderInterceptorMashape;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import priadko.android.hearthstonecards.cards.all.IProcessListenerFragAll;
import priadko.android.hearthstonecards.cards.all.interactor.retrofit.ApiAllCards;
import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;
import priadko.android.hearthstonecards.cards.all.interactor.retrofit.ResponseAllCards;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class InteractorFragAllImpl
        extends RetrofitInteractor<List<Card>>
        implements InteractorFragAll {

    private static final int CACHE_SIZE_MB = 10;
    private static final int CACHE_EXPIRATION = 240;

    public InteractorFragAllImpl(String baseUrl) {
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
    public void getAllCards(@NonNull final IProcessListenerFragAll listener) {
        listener.loadingStarted();
        List<Card> cards = getCachedData();
        if (isDataValid()) {
            listener.cardsLoaded(cards);
            listener.loadingDone();
            return;
        }
        getApi(ApiAllCards.class).getAllCards().enqueue(new Callback<ResponseAllCards>() {
            @Override
            public void onResponse(Call<ResponseAllCards> call, Response<ResponseAllCards> response) {
                if (response.isSuccessful()) {
                    List<Card> cards = response.body().getAllCards();
                    saveToCache(cards);
                    listener.cardsLoaded(cards);
                } else {
                    listener.loadingError();
                }
                listener.loadingDone();
            }

            @Override
            public void onFailure(Call<ResponseAllCards> call, Throwable t) {
                listener.loadingError();
            }
        });
    }
}
