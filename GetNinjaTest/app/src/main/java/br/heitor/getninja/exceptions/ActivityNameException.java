package br.heitor.getninja.exceptions;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.heitor.getninja.R;

public class ActivityNameException extends Exception {
    private static final long serialVersionUID = 1000L;

    public ActivityNameException(Context ctx, Class<? extends AppCompatActivity> activityClass) {
        super(String.format(ctx.getString(R.string.exception_activity_name), activityClass.getSimpleName()));
    }
}
