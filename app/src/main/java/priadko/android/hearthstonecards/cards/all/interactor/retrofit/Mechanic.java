
package priadko.android.hearthstonecards.cards.all.interactor.retrofit;


import io.realm.RealmObject;

public class Mechanic extends RealmObject{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
