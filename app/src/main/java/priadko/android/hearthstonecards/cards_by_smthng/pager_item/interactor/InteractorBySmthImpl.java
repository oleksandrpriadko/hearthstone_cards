package priadko.android.hearthstonecards.cards_by_smthng.pager_item.interactor;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.android.priadko.baselibrary.base_mvp.RetrofitInteractor;
import com.android.priadko.baselibrary.base_retrofit.HeaderInterceptor;
import com.android.priadko.baselibrary.base_retrofit.HeaderInterceptorMashape;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;
import priadko.android.hearthstonecards.cards_by_smthng.pager_item.IProcessListenerBySmth;
import priadko.android.hearthstonecards.constants.BUNDLE;
import priadko.android.hearthstonecards.util.ManagerSharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class InteractorBySmthImpl
        extends RetrofitInteractor<List<Card>>
        implements InteractorBySmth {

    public InteractorBySmthImpl(String baseUrl) {
        super(baseUrl);
    }

    @NonNull
    @Override
    protected CACHE_TYPE getCacheType() {
        return CACHE_TYPE.HAS_CACHE;
    }

    @Override
    protected int getCacheSizeMB() {
        return 3;
    }

    @Override
    protected int getExpTime() {
        return 60;
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
    public void processBundle(Bundle bundle, IProcessListenerBySmth listener) {
        if (bundle == null) {
            listener.typeGot(ManagerSharedPref.INFO.CLASSES, "Dummy");
        } else {
            ManagerSharedPref.INFO type =
                    ((ManagerSharedPref.INFO) bundle.getSerializable(BUNDLE.SERIALIZABLE.SMTH_ITEM_DATA_TYPE));
            String name = bundle.getString(BUNDLE.STRING.SMTH_ITEM_NAME);
            listener.typeGot(type, name);
        }
    }

    @Override
    public void loadCards(ManagerSharedPref.INFO dataType, String smthName, final IProcessListenerBySmth listener) {
        listener.loadingStarted();
        if (isDataValid()) {
            listener.cardsLoaded(getCachedData());
            listener.loadingDone();
            return;
        }
        ApiBySmth api = getApi(ApiBySmth.class);
        Call<List<Card>> call;
        switch (dataType) {
            case CLASSES:
                call = api.byClass(smthName);
                break;
            case FACTIONS:
                call = api.byFaction(smthName);
                break;
            case QUALITIES:
                call = api.byQuality(smthName);
                break;
            case RACES:
                call = api.byRace(smthName);
                break;
            case SETS:
                call = api.bySet(smthName);
                break;
            case TYPES:
                call = api.byType(smthName);
                break;
            default:
                call = api.byClass(smthName);
        }
        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (response.isSuccessful()) {
                    saveToCache(response.body());
                    listener.cardsLoaded(response.body());
                } else {
                    listener.loadingError();
                }
                listener.loadingDone();
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                listener.loadingError();
                listener.loadingDone();
            }
        });
    }
}
