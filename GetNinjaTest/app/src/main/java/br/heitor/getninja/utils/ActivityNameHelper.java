package br.heitor.getninja.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.heitor.getninja.exceptions.ActivityNameException;
import br.heitor.getninja.views.activities.MainActivity;

public class ActivityNameHelper {
    public static String getName(Context ctx, AppCompatActivity activity) {
        try {
            return getActivityName(ctx, activity);
        } catch (ActivityNameException e) {
            ErrorHandler.logError(e);
        }

        return activity.getClass().getSimpleName();
    }

    private static String getActivityName(Context ctx, AppCompatActivity activity) throws ActivityNameException {
        if (activity instanceof MainActivity) {
            return "Home";
        }

        throw new ActivityNameException(ctx, activity.getClass());
    }
}
