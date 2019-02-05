package lyndemberg.github.io.androidlogin.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import lyndemberg.github.io.androidlogin.R;

public class LoginReceiver extends BroadcastReceiver {
    public static final String ACTION_LOGIN_EXECUTED = "pdm-androidlogin-executed";

    private Activity ctx;

    public LoginReceiver(Activity ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Button btn = ctx.findViewById(R.id.buttonAcessar);
        btn.setText("Acessar");
        btn.setEnabled(true);
        int status = intent.getIntExtra("status", 401);
        if(status == 200)
            Toast.makeText(ctx,"Sucesso no login",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(ctx,"Fracasso no login",Toast.LENGTH_LONG).show();
    }

}
