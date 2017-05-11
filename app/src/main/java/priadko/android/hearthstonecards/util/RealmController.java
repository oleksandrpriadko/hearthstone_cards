package priadko.android.hearthstonecards.util;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;

public class RealmController {

    //////////////////////////////////////////////////////////////////////////////////////////////
    //  INIT
    //////////////////////////////////////////////////////////////////////////////////////////////
    public static void init(Context context) {
        Realm.init(context);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .build());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //  TICKETS REALM
    //////////////////////////////////////////////////////////////////////////////////////////////
    public static void saveCards(final List<Card> cards){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(cards);
            }
        });
    }

    public static RealmResults<Card> getCards() {
        return Realm.getDefaultInstance().where(Card.class).findAll();
    }

    public static Card getCard(String cardId) {
        return Realm.getDefaultInstance().where(Card.class)
                .equalTo("cardId", cardId)
                .findFirst();
    }
}
