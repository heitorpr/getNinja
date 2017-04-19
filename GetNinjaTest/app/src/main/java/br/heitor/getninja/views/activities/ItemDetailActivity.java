package br.heitor.getninja.views.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import br.heitor.getninja.R;
import br.heitor.getninja.utils.ActivityUtils;
import br.heitor.getninja.utils.FragmentNameHelper;
import br.heitor.getninja.views.fragments.ItemDetailFragment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        openFragment();
    }

    private void openFragment() {
        FragmentManager fm = getSupportFragmentManager();
        String name = FragmentNameHelper.getName(ctx, ItemDetailFragment.class);

        Fragment fragment = fm.findFragmentByTag(name);
        if (fragment == null) fragment = ItemDetailFragment.newInstance();

        ActivityUtils.moveFragment(fragment, fm, R.id.container, name, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
