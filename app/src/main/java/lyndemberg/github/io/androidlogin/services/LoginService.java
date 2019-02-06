package lyndemberg.github.io.androidlogin.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;

import lyndemberg.github.io.androidlogin.R;
import lyndemberg.github.io.androidlogin.receivers.LoginReceiver;
import lyndemberg.github.io.androidlogin.valueobject.CredentialsValue;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoginService extends IntentService {
    private static final String BGLOG = "pdm-login-service";
    private static final MediaType FORM_URLENCODED = MediaType.get("application/x-www-form-urlencoded");
    private OkHttpClient clientHttp = new OkHttpClient();

    public LoginService() {
        super("Thread-LoginService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        CredentialsValue credentials = (CredentialsValue) intent.getSerializableExtra("credentials");
        Log.d(BGLOG,credentials.toString());
        FormBody formBody = new FormBody.Builder().add("email", credentials.getEmail())
                    .add("password", credentials.getPassword()).build();
        Request request = new Request.Builder()
                .url(getString(R.string.resource_login))
                .post(formBody)
                .build();

        Intent intentExecuted = new Intent(LoginReceiver.ACTION_LOGIN_EXECUTED);
        //value default to code
        try (Response response = clientHttp.newCall(request).execute()) {
            String result = response.body().string();
            intentExecuted.putExtra("result", result);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intentExecuted);
        } catch (IOException e) {
            LocalBroadcastManager.getInstance(this).sendBroadcast(intentExecuted);
        }
    }


}
