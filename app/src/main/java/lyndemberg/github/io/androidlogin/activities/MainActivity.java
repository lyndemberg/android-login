package lyndemberg.github.io.androidlogin.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import lyndemberg.github.io.androidlogin.R;
import lyndemberg.github.io.androidlogin.receivers.LoginReceiver;
import lyndemberg.github.io.androidlogin.services.LoginService;
import lyndemberg.github.io.androidlogin.valueobject.CredentialsValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginReceiver loginReceiver = new LoginReceiver(this);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(loginReceiver,new IntentFilter(LoginReceiver.ACTION_LOGIN_EXECUTED));
    }

    public void clickAcessar(View view) {
        Button button = (Button) view;
        button.setText("Aguarde");
        button.setEnabled(false);

        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputPassword = findViewById(R.id.inputPassword);

        CredentialsValue credentials = new CredentialsValue(inputEmail.getText().toString(), inputPassword.getText().toString());
        Intent intent = new Intent(this, LoginService.class);
        intent.putExtra("credentials",credentials);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
