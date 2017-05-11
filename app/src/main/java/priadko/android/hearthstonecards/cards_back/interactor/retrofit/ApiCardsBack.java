package priadko.android.hearthstonecards.cards_back.interactor.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface ApiCardsBack {

    @GET("/cardbacks")
    Call<List<CardsBack>> getCardsBack();
}
