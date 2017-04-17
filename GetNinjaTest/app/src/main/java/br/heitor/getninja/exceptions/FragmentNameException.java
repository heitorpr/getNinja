package br.heitor.getninja.exceptions;

import android.content.Context;
import android.support.v4.app.Fragment;

import br.heitor.getninja.R;

public class FragmentNameException extends Exception {
    private static final long serialVersionUID = 1001L;

    public FragmentNameException(Context ctx, Class<? extends Fragment> fragmentClass) {
        super(String.format(ctx.getString(R.string.exception_fragment_name), fragmentClass.getSimpleName()));
    }
}
