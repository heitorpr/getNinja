package br.heitor.getninja.utils;

import android.content.Context;
import android.support.v4.app.Fragment;

import br.heitor.getninja.exceptions.FragmentNameException;
import br.heitor.getninja.views.fragments.LeadListFragment;
import br.heitor.getninja.views.fragments.OfferListFragment;

public class FragmentNameHelper {
    public static String getName(Context ctx, Class<? extends Fragment> fragment) {
        try {
            return getFragmentName(ctx, fragment);
        } catch (FragmentNameException e) {
            ErrorHandler.logError(e);
        }

        return fragment.getClass().getSimpleName();
    }

    private static String getFragmentName(Context ctx, Class<? extends Fragment> fragment) throws FragmentNameException {
        if (fragment.equals(OfferListFragment.class)) {
            return OfferListFragment.class.getSimpleName();
        }

        if (fragment.equals(LeadListFragment.class)) {
            return LeadListFragment.class.getSimpleName();
        }

        throw new FragmentNameException(ctx, fragment);
    }
}
