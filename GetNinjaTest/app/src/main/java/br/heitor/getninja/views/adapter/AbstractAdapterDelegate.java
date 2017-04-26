package br.heitor.getninja.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;

public abstract class AbstractAdapterDelegate<T> implements AdapterDelegate<T> {
    protected CustomAdapter.ViewType viewType;
    protected LayoutInflater inflater;
    protected Context ctx;

    public AbstractAdapterDelegate(Context ctx, CustomAdapter.ViewType viewType) {
        this.ctx = ctx;
        this.viewType = viewType;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public CustomAdapter.ViewType getViewType() {
        return viewType;
    }
}
