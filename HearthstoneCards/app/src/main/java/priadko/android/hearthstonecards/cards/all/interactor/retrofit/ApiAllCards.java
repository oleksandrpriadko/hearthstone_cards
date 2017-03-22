package priadko.android.hearthstonecards.cards.all.interactor.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public interface ApiAllCards {
    @GET("cards")
    Call<ResponseAllCards> getAllCards();
}
