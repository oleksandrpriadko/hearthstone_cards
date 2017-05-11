package priadko.android.hearthstonecards.cards_back.interactor.retrofit;

public class CardsBack {

    private String cardBackId;
    private String name;
    private String description;
    private String source;
    private String sourceDescription;
    private boolean enabled;
    private String img;
    private String imgAnimated;
    private String sortCategory;
    private String sortOrder;
    private String locale;
    private String howToGet;

    public String getCardBackId() {
        return cardBackId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSource() {
        return source;
    }

    public String getSourceDescription() {
        return sourceDescription;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getImg() {
        return img;
    }

    public String getImgAnimated() {
        return imgAnimated;
    }

    public String getSortCategory() {
        return sortCategory;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public String getLocale() {
        return locale;
    }

    public String getHowToGet() {
        return howToGet;
    }
}