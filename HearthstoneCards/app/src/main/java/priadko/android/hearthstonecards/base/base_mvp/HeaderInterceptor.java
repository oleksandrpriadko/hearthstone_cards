package priadko.android.hearthstonecards.base.base_mvp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * CookBrowser
 * Oleksandr Priadko
 */
class HeaderInterceptor implements Interceptor {
    private static final String MASHAPE_TITLE = "X-Mashape-Key";
    private static final String MASHAPE_VALUE = "ZqZDokvSBlmshEMoSxzqhog0kOcOp1tnJgvjsnGOhZhcrGnywl";
    private static final String TYPE_TITLE = "Accept";
    private static final String TYPE_VALUE = "application/json";

    @Override
    public Response intercept(Chain chain)
            throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader(MASHAPE_TITLE, MASHAPE_VALUE)
                .addHeader(TYPE_TITLE, TYPE_VALUE)
                .build();

        return chain.proceed(request);
    }
}
