package br.heitor.getninja.views.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import br.heitor.getninja.R;
import br.heitor.getninja.views.adapters.MainPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        MainPagerAdapter mSectionsPagerAdapter = new MainPagerAdapter(ctx, getSupportFragmentManager());
        container.setAdapter(mSectionsPagerAdapter);
        tabs.setupWithViewPager(container);

        for (int i = 0; i < tabs.getTabCount(); i++) {
            TabLayout.Tab tab = tabs.getTabAt(i);
            if (tab == null) continue;

            tab.setCustomView(R.layout.layout_custom_tab);

            if (i == 0) {
                ((TextView) tab.getCustomView()).setText(tab.getText());
                ((TextView) tab.getCustomView()).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(ctx, R.drawable.ic_business_center_white_24dp), null, null, null);
                continue;
            }

            if (i == 1) {
                tab.setIcon(R.drawable.ic_check_white_24dp);
                continue;
            }
        }
    }
}
