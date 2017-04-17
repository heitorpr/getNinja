package br.heitor.getninja.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ActivityUtils {
    public static void moveFragment(Fragment fragment, FragmentManager fm, int frameID, String name, boolean addToBackStack) {
        FragmentTransaction transactionFragment = fm.beginTransaction();

        transactionFragment.replace(frameID, fragment, name);

        if (addToBackStack) transactionFragment.addToBackStack(name);

        transactionFragment.commit();
    }
}
