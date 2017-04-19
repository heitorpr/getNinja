package br.heitor.getninja.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.heitor.getninja.R;
import butterknife.ButterKnife;

public class ItemDetailFragment extends BaseFragment {

    public ItemDetailFragment() {
    }

    public static ItemDetailFragment newInstance() {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        initVariables();

        return view;
    }
}
