
package priadko.android.hearthstonecards.cards.all.interactor.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResponseAllCards {

    @SerializedName("Basic")
    private List<Card> basic;
    @SerializedName("Classic")
    private List<Card> classic;
    @SerializedName("Promo")
    private List<Card> promo;
    @SerializedName("Reward")
    private List<Card> reward;
    @SerializedName("Naxxramas")
    private List<Card> naxxramas;
    @SerializedName("GoblinsVsGnomes")
    private List<Card> goblinsVsGnomes;
    @SerializedName("BlackrockMountain")
    private List<Card> blackrockMountain;
    @SerializedName("TheGrandTournament")
    private List<Card> theGrandTournament;
    @SerializedName("TheLeagueOfExplorers")
    private List<Card> theLeagueOfExplorers;
    @SerializedName("WhispersOfTheOldGods")
    private List<Card> whispersOfTheOldGods;
    @SerializedName("Karazhan")
    private List<Card> karazhan;
    @SerializedName("MeanStreetsOfGadgetzan")
    private List<Card> meanStreetsOfGadgetzan;
    @SerializedName("TavernBrawl")
    private List<Card> tavernBrawl;
    @SerializedName("HeroSkins")
    private List<Card> heroSkins;

    public List<Card> getBasic() {
        return basic;
    }

    public List<Card> getClassic() {
        return classic;
    }

    public List<Card> getPromo() {
        return promo;
    }

    public List<Card> getReward() {
        return reward;
    }

    public List<Card> getNaxxramas() {
        return naxxramas;
    }

    public List<Card> getGoblinsVsGnomes() {
        return goblinsVsGnomes;
    }

    public List<Card> getBlackrockMountain() {
        return blackrockMountain;
    }

    public List<Card> getTheGrandTournament() {
        return theGrandTournament;
    }

    public List<Card> getTheLeagueOfExplorers() {
        return theLeagueOfExplorers;
    }

    public List<Card> getWhispersOfTheOldGods() {
        return whispersOfTheOldGods;
    }

    public List<Card> getKarazhan() {
        return karazhan;
    }

    public List<Card> getMeanStreetsOfGadgetzan() {
        return meanStreetsOfGadgetzan;
    }

    public List<Card> getTavernBrawl() {
        return tavernBrawl;
    }

    public List<Card> getHeroSkins() {
        return heroSkins;
    }

    public List<Card> getAllCards(){
        List<Card> list = new ArrayList<>(getAllCardsCount());
        addCards(list, basic);
        addCards(list, classic);
        addCards(list, promo);
        addCards(list, reward);
        addCards(list, naxxramas);
        addCards(list, goblinsVsGnomes);
        addCards(list, blackrockMountain);
        addCards(list, theGrandTournament);
        addCards(list, theLeagueOfExplorers);
        addCards(list, whispersOfTheOldGods);
        addCards(list, karazhan);
        addCards(list, meanStreetsOfGadgetzan);
        addCards(list, tavernBrawl);
        addCards(list, heroSkins);
        return list;
    }

    private int getAllCardsCount(){
        return getListSize(basic)
                + getListSize(classic)
                + getListSize(promo)
                + getListSize(reward)
                + getListSize(naxxramas)
                + getListSize(goblinsVsGnomes)
                + getListSize(blackrockMountain)
                + getListSize(theGrandTournament)
                + getListSize(theLeagueOfExplorers)
                + getListSize(whispersOfTheOldGods)
                + getListSize(karazhan)
                + getListSize(meanStreetsOfGadgetzan)
                + getListSize(tavernBrawl)
                + getListSize(heroSkins);
    }

    private int getListSize(Collection<Card> collection){
        return collection != null && !collection.isEmpty() ? collection.size() : 0;
    }

    private void addCards(Collection<Card> to, Collection<Card> from){
        if (getListSize(from) > 0){
            to.addAll(from);
        }
    }
}
