package priadko.android.hearthstonecards.cards_by_smthng.pager_item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import priadko.android.hearthstonecards.R;
import priadko.android.hearthstonecards.base.base_recycler_view_adapter.AdapterRecViewBase;
import priadko.android.hearthstonecards.base.base_recycler_view_adapter.BaseHolderPicasso;
import priadko.android.hearthstonecards.base.base_recycler_view_adapter.BaseItemListener;
import priadko.android.hearthstonecards.cards.all.interactor.retrofit.Card;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

class AdapterBySmth extends AdapterRecViewBase<Card, AdapterBySmth.HolderBySmth, AdapterBySmth.ItemListener> {

    public AdapterBySmth(@NonNull ItemListener itemListener, boolean emptyFromConstructor) {
        super(itemListener, emptyFromConstructor);
    }

    @Override
    protected HolderBySmth onGetHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new HolderBySmth(inflater.inflate(R.layout.item_card, parent, false));
    }

    @Override
    protected void onBindHolder(HolderBySmth holder, int position) {
        Card card = getData().get(position);
        holder.loadImage(card.getImg());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  HOLDER
    ///////////////////////////////////////////////////////////////////////////////////////////////
    class HolderBySmth extends BaseHolderPicasso {

        HolderBySmth(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListener.itemClicked(getAdapterPosition(),
                            getData().get(getAdapterPosition()));
                }
            });
        }

        void loadImage(String url) {
            loadImage(url,
                    ((ImageView) itemView.findViewById(R.id.imageView_image)),
                    R.drawable.ic_class_black_24dp);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  ITEM LISTENER
    ///////////////////////////////////////////////////////////////////////////////////////////////
    interface ItemListener extends BaseItemListener<Card> {

    }
}
