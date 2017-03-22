package priadko.android.hearthstonecards.util.recycler_view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemDecorVertGridSpac extends RecyclerView.ItemDecoration {
    private final int spanCount;
    private int left;
    private int top;
    private int right;
    private int bottom;
    private boolean includeEdge;

    public ItemDecorVertGridSpac(int spanCount, int left, int top, int right, int bottom, boolean includeEdge) {
        this.spanCount = spanCount;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = left - column * left / spanCount;
            outRect.right = (column + 1) * right / spanCount;

            if (position < spanCount) {
                outRect.top = top;
            }
            outRect.bottom = bottom;
        } else {
            outRect.left = column * left / spanCount;
            outRect.right = right - (column + 1) * right / spanCount;
            if (position >= spanCount) {
                outRect.top = top;
            }
        }
    }
}
