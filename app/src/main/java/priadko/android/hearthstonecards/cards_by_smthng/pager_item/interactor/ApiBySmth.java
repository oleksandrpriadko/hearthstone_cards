package priadko.android.hearthstonecards.cards_by_smthng.pager_item.interactor;

import java.util.List;

import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

interface ApiBySmth {

    @GET("/cards/sets/{name}")
    Call<List<Card>> bySet(
            @Path("name") String name
    );

    @GET("/cards/classes/{name}")
    Call<List<Card>> byClass(
            @Path("name") String name
    );

    @GET("/cards/factions/{name}")
    Call<List<Card>> byFaction(
            @Path("name") String name
    );

    @GET("/cards/qualities/{name}")
    Call<List<Card>> byQuality(
            @Path("name") String name
    );

    @GET("/cards/races/{name}")
    Call<List<Card>> byRace(
            @Path("name") String name
    );

    @GET("/cards/types/{name}")
    Call<List<Card>> byType(
            @Path("name") String name
    );
}
