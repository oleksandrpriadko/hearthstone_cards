package priadko.android.hearthstonecards.base.base_recycler_view_adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * HearthstoneCards
 * Oleksandr Priadko
 */

public abstract class AdapterRecViewBase<
                D,
                H extends RecyclerView.ViewHolder,
                I extends BaseItemListener<D>>
        extends RecyclerView.Adapter<H>{

    protected I itemListener;
    protected List<D> items;
    private LayoutInflater inflater;

    public AdapterRecViewBase(@NonNull I itemListener, boolean emptyFromConstructor) {
        this.itemListener = itemListener;
        if (emptyFromConstructor){
            this.itemListener.isEmpty(isEmpty());
        }
    }

    public void setData(List<D> data){
        this.items = data;
        this.itemListener.isEmpty(isEmpty());
        this.notifyDataSetChanged();
    }

    public List<D> getData(){
        return this.items;
    }

    private boolean isEmpty(){
        return this.items == null || this.items.isEmpty();
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null){
            inflater = LayoutInflater.from(parent.getContext());
        }
        return onGetHolder(inflater, parent, viewType);
    }

    protected abstract H onGetHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(H holder, int position) {
        this.onBindHolder(holder, position);
    }

    protected abstract void onBindHolder(H holder, int position);

    @Override
    public int getItemCount() {
        return isEmpty() ? 0 : items.size();
    }
}
