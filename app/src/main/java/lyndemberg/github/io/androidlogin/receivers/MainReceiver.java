package lyndemberg.github.io.androidlogin.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import lyndemberg.github.io.androidlogin.R;

public abstract class MainReceiver extends BroadcastReceiver {
    public static final String ACTION_MAIN = "MainReceiver";

    //events
    public static final String EVENT_LOGIN_EXECUTED = "pdm-loginexecuted";

    public abstract void resolveView(Intent intent);

    @Override
    public void onReceive(Context context, Intent intent) {
        resolveView(intent);
    }

}
