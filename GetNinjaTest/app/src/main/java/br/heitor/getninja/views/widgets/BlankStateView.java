package br.heitor.getninja.views.widgets;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import br.heitor.getninja.collections.Collection;

public class BlankStateView {
    protected Context ctx;
    private RelativeLayout blankView;
    private Collection collection;

    public BlankStateView(@NonNull RelativeLayout blankView, @NonNull Collection collection,
                          @LayoutRes int resBlankView, @NonNull Context ctx) {

        this.blankView = blankView;
        this.ctx = ctx;
        this.collection = collection;

        inflateBlankView(blankView, resBlankView, ctx);
    }

    public void updateBlankState(RecyclerView recyclerView) {
        if (blankView == null || recyclerView == null) return;
        int itemCount = collection == null ? 0 : collection.size();

        boolean willShowBlankState = itemCount == 0;

        if (willShowBlankState) {
            recyclerView.setVisibility(View.GONE);
            showBlankState();
            return;
        }

        blankView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void showBlankState() {
        if (blankView == null) return;
        blankView.setVisibility(View.VISIBLE);
    }

    private void inflateBlankView(RelativeLayout blankView, int resBlankView, Context ctx) {
        if (blankView == null) return;

        blankView.removeAllViews();
        LayoutInflater.from(ctx).inflate(resBlankView, blankView);
    }

    public void destroy() {
        blankView = null;
        ctx = null;
        collection = null;
    }
}
