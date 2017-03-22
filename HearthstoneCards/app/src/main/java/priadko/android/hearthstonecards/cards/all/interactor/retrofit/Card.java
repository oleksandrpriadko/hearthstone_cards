
package priadko.android.hearthstonecards.cards.all.interactor.retrofit;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Card extends RealmObject{

    private String cardId;
    private String name;
    private String cardSet;
    private String type;
    private String faction;
    private String rarity;
    private int cost;
    private int attack;
    private int health;
    private String text;
    private String flavor;
    private String artist;
    private boolean collectible;
    private String playerClass;
    private String howToGetGold;
    private String img = "http://wow.zamimg.com/images/hearthstone/cards/enus/original/XXX_100.png";
    private String imgGold;
    private String locale;
    private RealmList<Mechanic> mechanics = null;
    private String race;
    private String howToGet;
    private boolean elite;

    public String getCardId() {
        return cardId;
    }

    public String getName() {
        return name;
    }

    public String getCardSet() {
        return cardSet;
    }

    public String getType() {
        return type;
    }

    public String getFaction() {
        return faction;
    }

    public String getRarity() {
        return rarity;
    }

    public int getCost() {
        return cost;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public String getText() {
        return text;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getArtist() {
        return artist;
    }

    public boolean isCollectible() {
        return collectible;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public String getHowToGetGold() {
        return howToGetGold;
    }

    public String getImg() {
        return img;
    }

    public String getImgGold() {
        return imgGold;
    }

    public String getLocale() {
        return locale;
    }

    public List<Mechanic> getMechanics() {
        return mechanics;
    }

    public String getRace() {
        return race;
    }

    public String getHowToGet() {
        return howToGet;
    }

    public boolean isElite() {
        return elite;
    }
}
