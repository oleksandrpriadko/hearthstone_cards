package priadko.android.hearthstonecards.base.base_mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * TimVision-Android-W12
 * Oleksandr Priadko
 */

public abstract class BaseInteractor<D> {
    private static final int DEFAULT_CACHE_SIZE_MB = 10;
    private static final int DEFAULT_CACHE_EXPIRATION = 60;
    private static final TimeUnit DEFAULT_CACHE_EXPIRATION_UNIT = TimeUnit.SECONDS;

    private String baseUrl;
    private Retrofit retrofit;
    private Cache<String, D> dataCache;

    public BaseInteractor(String baseUrl) {
        this.baseUrl = baseUrl;
        CACHE_TYPE cacheType = getCacheType();
        int cacheSize = DEFAULT_CACHE_SIZE_MB;
        int expTime = DEFAULT_CACHE_EXPIRATION;
        TimeUnit timeUnitExp = DEFAULT_CACHE_EXPIRATION_UNIT;
        if (cacheType != CACHE_TYPE.NONE){
            switch (cacheType){
                case HAS_CACHE:
                    cacheSize = getCacheSizeMB();
                    expTime = getExpTime();
                    timeUnitExp = getExpTimeUnit();
                    break;
                case DEFAULT_CACHE:
                    cacheSize = DEFAULT_CACHE_SIZE_MB;
                    expTime = DEFAULT_CACHE_EXPIRATION;
                    timeUnitExp = DEFAULT_CACHE_EXPIRATION_UNIT;
                    break;
            }
            this.dataCache = CacheBuilder.newBuilder()
                    .maximumSize(cacheSize)
                    .expireAfterWrite(expTime, timeUnitExp)
                    .build();
        }
        this.initRetrofit();
    }

    @NonNull
    protected abstract CACHE_TYPE getCacheType();

    private String getCacheKey() {
        return this.getClass().getSimpleName();
    }

    protected abstract int getCacheSizeMB();

    protected abstract int getExpTime();

    @NonNull
    protected abstract TimeUnit getExpTimeUnit();

    @Nullable
    protected D getCachedData() {
        return this.dataCache != null ? this.dataCache.getIfPresent(getCacheKey()) : null;
    }

    protected void saveToCache(D data) {
        if (this.dataCache == null){
            return;
        }
        this.dataCache.put(getCacheKey(), data);
    }

    protected boolean isDataValid() {
        D data = getCachedData();
        return data != null;
    }

    private void initRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new HeaderInterceptor())
                .build();
        String testUrl = getBaseUrl();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(testUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private String getBaseUrl() {
        return this.baseUrl;
    }

    private Retrofit getRetrofit() {
        if (this.retrofit == null) {
            this.initRetrofit();
        }
        return this.retrofit;
    }

    protected <A> A getApi(final Class<A> apiClass) {
        return this.getRetrofit().create(apiClass);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  CACHE TYPES
    ///////////////////////////////////////////////////////////////////////////////////////////////
    protected enum CACHE_TYPE {
        NONE,
        HAS_CACHE,
        DEFAULT_CACHE
    }
}
