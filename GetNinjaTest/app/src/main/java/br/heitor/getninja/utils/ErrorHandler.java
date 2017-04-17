package br.heitor.getninja.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ErrorHandler {
    public static void logError(Exception ex) {
        Log.e(ex.getClass().getSimpleName(), ex.getMessage());
    }

    private static void showMessage(Context ctx, String text) {
        Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
    }
}
