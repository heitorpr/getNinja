package br.heitor.getninja.views.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import br.heitor.getninja.Events.CollectionEvent;
import br.heitor.getninja.R;
import br.heitor.getninja.collections.LeadCollection;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LeadListFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.blank_view)
    RelativeLayout blankView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private LeadCollection collection;

    public LeadListFragment() {
    }

    public static LeadListFragment newInstance() {
        LeadListFragment fragment = new LeadListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        initVariables();

        return view;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        createCollection();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(CollectionEvent event) {
        if (!event.getType().equals(collection.getClass())) return;
        refreshLayout.setRefreshing(false);
    }

    private void createCollection() {
        if (collection != null) return;
        collection = new LeadCollection();
        collection.fetch();
    }
}
