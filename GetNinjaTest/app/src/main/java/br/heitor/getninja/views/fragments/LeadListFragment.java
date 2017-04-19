package br.heitor.getninja.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.heitor.getninja.R;
import br.heitor.getninja.views.activities.ItemDetailActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LeadListFragment extends BaseFragment {
    @BindView(R.id.txt_teste)
    TextView txtTeste;

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


        txtTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, ItemDetailActivity.class);
                ctx.startActivity(intent);
            }
        });

        return view;
    }
}
