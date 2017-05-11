package priadko.android.hearthstonecards.main_activity.interactor;

import android.support.annotation.NonNull;

import com.android.priadko.baselibrary.base_mvp.RetrofitInteractor;
import com.android.priadko.baselibrary.base_retrofit.HeaderInterceptor;
import com.android.priadko.baselibrary.base_retrofit.HeaderInterceptorMashape;

import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import priadko.android.hearthstonecards.main_activity.IProcessListenerMain;
import priadko.android.hearthstonecards.main_activity.interactor.retrofit_model.ApiInfo;
import priadko.android.hearthstonecards.main_activity.interactor.retrofit_model.ResponseInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class InteractorMainImpl
        extends RetrofitInteractor<ResponseInfo>
        implements InteractorMain {

    public InteractorMainImpl(String baseUrl) {
        super(baseUrl);
    }

    @NonNull
    @Override
    protected CACHE_TYPE getCacheType() {
        return CACHE_TYPE.NONE;
    }

    @Override
    protected int getCacheSizeMB() {
        return 0;
    }

    @Override
    protected int getExpTime() {
        return 0;
    }

    @NonNull
    @Override
    protected TimeUnit getExpTimeUnit() {
        return TimeUnit.MICROSECONDS;
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
    public void getInfo(final IProcessListenerMain listener) {
        listener.loadingStarted();
        getApi(ApiInfo.class).getInfo().enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                if (response.isSuccessful()) {
                    saveToCache(response.body());
                    listener.infoLoaded(response.body());
                } else {
                    listener.loadingError();
                }
                listener.loadingDone();
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                listener.loadingError();
                listener.loadingDone();
            }
        });
    }
}
