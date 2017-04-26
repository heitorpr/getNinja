package br.heitor.getninja.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import br.heitor.getninja.Events.CollectionEvent;
import br.heitor.getninja.collections.Collection;
import br.heitor.getninja.views.widgets.BlankStateView;
import de.greenrobot.event.EventBus;

@SuppressWarnings("unchecked")
public class CustomAdapter<T> extends RecyclerView.Adapter {
    protected Collection collection;
    private AdapterDelegateManager delegates;
    private BlankStateView blankStateView;
    private RecyclerView recyclerView;

    @Deprecated
    public CustomAdapter(Collection collection, AdapterDelegate... delegates) {
        this.delegates = new AdapterDelegateManager();
        this.collection = collection;

        for (AdapterDelegate delegate : delegates) this.delegates.add(delegate);
    }

    public CustomAdapter(Collection collection, RecyclerView recyclerView,
                         BlankStateView blankStateView, AdapterDelegate... delegates) {

        this.delegates = new AdapterDelegateManager();
        this.collection = collection;
        this.recyclerView = recyclerView;
        this.blankStateView = blankStateView;

        EventBus.getDefault().register(this);
        for (AdapterDelegate delegate : delegates) this.delegates.add(delegate);
    }

    @Override
    public int getItemViewType(int position) {
        return delegates.getItemViewType(collection, position);
    }

    @Override
    public final int getItemCount() {
        return collection != null ? collection.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegates.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegates.onBindViewHolder(collection, position, holder);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    public void refresh(RecyclerView list) {
        if (list == null || list.isComputingLayout()) return;
        notifyDataSetChanged();

        if (blankStateView == null || recyclerView == null) return;
        blankStateView.updateBlankState(recyclerView);
    }

    public T getItem(int position) {
        return collection.size() <= position ? null : (T) collection.get(position);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(CollectionEvent event) {
        boolean canCheckBlankState = event.getType().equals(collection.getClass())
                && recyclerView != null && !recyclerView.isComputingLayout();

        updateBlankState(canCheckBlankState);
    }

    private void updateBlankState(boolean canCheckBlankState) {
        if (!canCheckBlankState || blankStateView == null) return;
        notifyDataSetChanged();
        blankStateView.updateBlankState(recyclerView);
    }

    public void destroy() {
        if (EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this);

        delegates = null;
        blankStateView = null;
        recyclerView = null;
    }

    public void setViews(RecyclerView recyclerView, BlankStateView blankStateView) {
        this.recyclerView = recyclerView;
        this.blankStateView = blankStateView;
    }

    public enum ViewType {
        OFFER, LEAD
    }
}
