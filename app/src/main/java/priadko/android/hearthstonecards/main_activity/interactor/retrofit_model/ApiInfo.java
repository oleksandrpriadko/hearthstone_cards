package priadko.android.hearthstonecards.main_activity.interactor.retrofit_model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface ApiInfo {

    @GET("/info")
    Call<ResponseInfo> getInfo();
}
