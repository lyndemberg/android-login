package lyndemberg.github.io.androidlogin.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lyndemberg.github.io.androidlogin.R;
import lyndemberg.github.io.androidlogin.receivers.MainReceiver;
import lyndemberg.github.io.androidlogin.services.LoginService;
import lyndemberg.github.io.androidlogin.valueobject.CredentialsValue;

public class MainActivity extends AppCompatActivity {
    private MainReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        receiver = new MainReceiver(){
            @Override
            public void resolveView(Intent intent) {
                if(intent.getStringExtra("event").equals(MainReceiver.EVENT_LOGIN_EXECUTED)){
                    Button btn = findViewById(R.id.buttonAcessar);
                    btn.setText("Acessar");
                    btn.setEnabled(true);

                    int status = intent.getIntExtra("status", 401);
                    if(status == 200)
                        Toast.makeText(MainActivity.this,"Sucesso no login",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"Fracasso no login",Toast.LENGTH_LONG).show();
                }
            }
        };

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(receiver,new IntentFilter(MainReceiver.ACTION_MAIN));
        super.onResume();
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onStop();
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

}
