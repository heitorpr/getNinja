package br.heitor.getninja.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.heitor.getninja.utils.ActivityNameHelper;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
    protected Context ctx;
    protected Unbinder unbinder;
    protected String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        TAG = ActivityNameHelper.getName(ctx, this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

    protected void initVariables() {
    }
}
