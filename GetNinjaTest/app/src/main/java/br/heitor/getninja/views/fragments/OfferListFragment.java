package br.heitor.getninja.views.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import br.heitor.getninja.Events.CollectionEvent;
import br.heitor.getninja.R;
import br.heitor.getninja.collections.OfferCollection;
import br.heitor.getninja.models.Offer;
import br.heitor.getninja.views.adapter.CustomAdapter;
import br.heitor.getninja.views.adapter_delegate.OfferAdapterDelegate;
import br.heitor.getninja.views.widgets.BlankStateView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OfferListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.blank_view)
    RelativeLayout blankView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private OfferCollection collection;
    private BlankStateView blankStateView;
    private CustomAdapter<Offer> adapter;

    public OfferListFragment() {
    }

    public static OfferListFragment newInstance() {
        OfferListFragment fragment = new OfferListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);

        initVariables();
        setEvents();
        return view;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        createCollection();
        createAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setEvents() {
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        collection.fetch();
    }

    private void createCollection() {
        if (collection != null) return;
        collection = new OfferCollection();
        collection.fetch();
    }

    private void createAdapter() {
        blankStateView = new BlankStateView(blankView, collection, R.layout.blank_state_default, ctx);

        if (adapter != null) {
            adapter.setViews(recyclerView, blankStateView);
            return;
        }

        adapter = new CustomAdapter<>(collection, recyclerView, blankStateView,
                new OfferAdapterDelegate(ctx, CustomAdapter.ViewType.OFFER));
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(CollectionEvent event) {
        if (!event.getType().equals(collection.getClass())) return;
        refreshLayout.setRefreshing(false);
    }
}
