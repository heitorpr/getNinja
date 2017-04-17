package br.heitor.getninja.views.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.heitor.getninja.R;
import br.heitor.getninja.views.fragments.LeadListFragment;
import br.heitor.getninja.views.fragments.OfferListFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private Context ctx;

    public MainPagerAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        this.ctx = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return OfferListFragment.newInstance();
        if (position == 1) return LeadListFragment.newInstance();
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return ctx.getString(R.string.offers);
        if (position == 1) return ctx.getString(R.string.accepted);
        return null;
    }


}
