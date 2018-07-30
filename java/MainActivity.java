package com.example.sankhedekar.test;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity implements NetworkType.NetworkTypeListener{

    // These are the global variables
    EditText editUsername, editPassword;
    TextView result;
    Button buttonSubmit, buttonReset;
    String cred = "Test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsername = findViewById(R.id.txtUsername);
        editPassword = findViewById(R.id.txtPassword);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonReset = findViewById(R.id.buttonReset);
        result = findViewById(R.id.Result);

        // Submit Button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editUsername.getText().toString();
                String password = editPassword.getText().toString();
                if(name.isEmpty() || password.isEmpty()) {
                    Context context = getApplicationContext();
                    CharSequence text = "Both Name and Password required";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    if(name.equals(cred) && password.equals(cred)) {
                        Context context = getApplicationContext();
                        CharSequence text = "Login Successful";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        send_Message(v);
                        editUsername.setText("");
                        editPassword.setText("");
                    }
                    else{
                        Context context = getApplicationContext();
                        CharSequence text = "Please enter correct credentials";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        });

        // Reset Button
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUsername.setText("");
                editPassword.setText("");
                result.setText("");
                editUsername.requestFocus();
            }
        });
    }

    public void send_Message(View view) {
        Intent intent = new Intent(this, TabLayoutActivity.class);
        String message = editUsername.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void mySnackBar(int connectedType) {
        String message;
        if (connectedType == 1) {
            message = "Wifi enabled.";
        }
        else if (connectedType == 2) {
            message = "Mobile data enabled.";
        }
        else {
            message = "No internet connection.";
        }

        Snackbar snackbar = Snackbar.make(findViewById(R.id.cl), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        BroadcastReceiver broadcastReceiver = new NetworkType();
        registerReceiver(broadcastReceiver, intentFilter);

        AppNetworkListener.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(int connectedType) {
        mySnackBar(connectedType);
    }
}
