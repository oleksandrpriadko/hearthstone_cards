package priadko.android.hearthstonecards.base.base_recycler_view_adapter;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import priadko.android.hearthstonecards.R;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public class BaseHolderPicasso extends RecyclerView.ViewHolder{
    public BaseHolderPicasso(View itemView) {
        super(itemView);
    }

    protected void loadImage(String url, ImageView imageView, @DrawableRes int placeHolderId) {
        getPicasso()
                .load(url)
                .placeholder(placeHolderId)
                .error(R.drawable.ic_info_outline_black_24dp)
                .into(imageView);
    }

    private Picasso getPicasso() {
        Picasso picasso = Picasso.with(itemView.getContext());
        picasso.setIndicatorsEnabled(false);
        picasso.setLoggingEnabled(false);
        return picasso;
    }
}
