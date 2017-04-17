package br.heitor.getninja.views.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected Context ctx;
    protected Unbinder unbinder;

    protected void initVariables() {
        ctx = getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) unbinder.unbind();
    }
}