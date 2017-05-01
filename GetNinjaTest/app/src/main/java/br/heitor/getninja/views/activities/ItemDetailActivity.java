package br.heitor.getninja.views.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import br.heitor.getninja.R;
import br.heitor.getninja.utils.ActivityUtils;
import br.heitor.getninja.utils.FragmentNameHelper;
import br.heitor.getninja.views.adapter.CustomAdapter;
import br.heitor.getninja.views.fragments.LeadDetailFragment;
import br.heitor.getninja.views.fragments.OfferDetailFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailActivity extends BaseAppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.linear_accept_buttons)
    LinearLayout linearAcceptButtons;
    @BindView(R.id.linear_call_buttons)
    LinearLayout linearCallButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        initVariables();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initVariables() {
        super.initVariables();

        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        CustomAdapter.ViewType type = (CustomAdapter.ViewType) bundle.get("type");
        String link = bundle.getString("link");

        if (type == CustomAdapter.ViewType.OFFER) {
            linearCallButtons.setVisibility(View.GONE);
            linearAcceptButtons.setVisibility(View.VISIBLE);
        }

        openFragment(type, link);
    }

    private void openFragment(CustomAdapter.ViewType type, String link) {
        FragmentManager fm = getSupportFragmentManager();

        String name = getFragmentName(type);
        Fragment fragment = fm.findFragmentByTag(name);
        if (fragment == null) fragment = getFragment(type);

        fragment.getArguments().putString("link", link);

        ActivityUtils.moveFragment(fragment, fm, R.id.container, name, true);
    }

    private Fragment getFragment(CustomAdapter.ViewType type) {
        if (type == CustomAdapter.ViewType.OFFER)
            return OfferDetailFragment.newInstance();

        return LeadDetailFragment.newInstance();
    }

    private String getFragmentName(CustomAdapter.ViewType type) {
        if (type == CustomAdapter.ViewType.OFFER)
            return FragmentNameHelper.getName(ctx, OfferDetailFragment.class);

        return FragmentNameHelper.getName(ctx, LeadDetailFragment.class);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
