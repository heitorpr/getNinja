package br.heitor.getninja.views.adapter;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

class AdapterDelegateManager<T> {
    @SuppressWarnings("unchecked")
    private SparseArrayCompat<AdapterDelegate<T>> delegates = new SparseArrayCompat();

    public AdapterDelegateManager<T> add(@NonNull AdapterDelegate<T> delegate) {
        delegates.put(delegate.getViewType().ordinal(), delegate);
        return this;
    }

    public int getItemViewType(@NonNull T items, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            AdapterDelegate<T> delegate = delegates.valueAt(i);

            if (!delegate.isForViewType(items, position)) continue;
            return delegates.keyAt(i);
        }

        return 0;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegates.get(viewType).onCreateViewHolder(parent);
    }

    public void onBindViewHolder(@NonNull T items, int position, @NonNull RecyclerView.ViewHolder viewHolder) {
        delegates.get(viewHolder.getItemViewType()).onBindViewHolder(items, position, viewHolder);
    }
}