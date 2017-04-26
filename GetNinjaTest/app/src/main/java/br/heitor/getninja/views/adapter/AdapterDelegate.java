package br.heitor.getninja.views.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface AdapterDelegate<T> {
    CustomAdapter.ViewType getViewType();

    /**
     * Called to determine whether this AdapterDelegate is the responsible for the given data
     * element.
     *
     * @param items    The data source of the Adapter
     * @param position The position in the data source
     * @return true, if this item is responsible,  otherwise false
     */
    boolean isForViewType(@NonNull T items, int position);

    /**
     * Creates the  {@link RecyclerView.ViewHolder} for the given data source item
     *
     * @param parent The ViewGroup parent of the given data source
     * @return The new instantiated {@link RecyclerView.ViewHolder}
     */
    @NonNull
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    /**
     * Called to bind the {@link RecyclerView.ViewHolder} to the item of the data source set
     *
     * @param items      The data source
     * @param position   The position in the data source
     * @param viewHolder The {@link RecyclerView.ViewHolder} to bind
     */
    void onBindViewHolder(@NonNull T items, int position, @NonNull RecyclerView.ViewHolder viewHolder);
}