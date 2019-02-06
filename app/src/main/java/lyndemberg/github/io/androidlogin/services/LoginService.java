package lyndemberg.github.io.androidlogin.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;

import lyndemberg.github.io.androidlogin.R;
import lyndemberg.github.io.androidlogin.receivers.MainReceiver;
import lyndemberg.github.io.androidlogin.valueobject.CredentialsValue;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginService extends IntentService {
    private static final String BGLOG = "pdm-login-service";
    private OkHttpClient clientHttp = new OkHttpClient();

    public LoginService() {
        super("Thread-LoginService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        CredentialsValue credentials = (CredentialsValue) intent.getSerializableExtra("credentials");
        FormBody formBody = new FormBody.Builder().add("email", credentials.getEmail())
                    .add("password", credentials.getPassword()).build();
        Request request = new Request.Builder()
                .url(getString(R.string.resource_login))
                .post(formBody)
                .build();

        Intent intentExecuted = new Intent(MainReceiver.ACTION_MAIN);
        intentExecuted.putExtra("event", MainReceiver.EVENT_LOGIN_EXECUTED);
        //value default to code
        try (Response response = clientHttp.newCall(request).execute()) {
            intentExecuted.putExtra("status",response.code());
            LocalBroadcastManager.getInstance(this).sendBroadcast(intentExecuted);
        } catch (IOException e) {
            intentExecuted.putExtra("status",401);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intentExecuted);
        }
    }


}
