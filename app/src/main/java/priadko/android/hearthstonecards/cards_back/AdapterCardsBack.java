package priadko.android.hearthstonecards.cards_back;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.priadko.baselibrary.base_recycler_view_adapter.AdapterRecViewBase;
import com.android.priadko.baselibrary.base_recycler_view_adapter.BaseHolderPicasso;
import com.android.priadko.baselibrary.base_recycler_view_adapter.BaseItemListener;

import priadko.android.hearthstonecards.R;
import priadko.android.hearthstonecards.cards_back.interactor.retrofit.CardsBack;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

class AdapterCardsBack extends AdapterRecViewBase<CardsBack, AdapterCardsBack.HolderCardsBack, AdapterCardsBack.ItemListener> {

    AdapterCardsBack(@NonNull ItemListener itemListener, boolean emptyFromConstructor) {
        super(itemListener, emptyFromConstructor);
    }

    @Override
    protected HolderCardsBack onGetHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new HolderCardsBack(inflater.inflate(R.layout.item_card_back, parent, false));
    }

    @Override
    protected void onBindHolder(HolderCardsBack holder, int position) {
        CardsBack cardsBack = items.get(position);
        holder.loadImage(cardsBack.getImg());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  HOLDER
    ///////////////////////////////////////////////////////////////////////////////////////////////
    class HolderCardsBack extends BaseHolderPicasso {

        HolderCardsBack(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListener.itemClicked(getAdapterPosition(), items.get(getAdapterPosition()));
                }
            });
        }

        void loadImage(String url){
            loadImage(url,
                    ((ImageView) itemView.findViewById(R.id.imageView_image)),
                    R.drawable.ic_photo_album_black_24dp);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  ITEM LISTENER
    ///////////////////////////////////////////////////////////////////////////////////////////////
    interface ItemListener extends BaseItemListener<CardsBack> {

    }
}
