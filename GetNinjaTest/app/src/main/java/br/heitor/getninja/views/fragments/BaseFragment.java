package br.heitor.getninja.views.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

public abstract class BaseFragment extends Fragment {
    protected Context ctx;
    protected Unbinder unbinder;

    protected void initVariables() {
        ctx = getActivity();
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) unbinder.unbind();
    }
}