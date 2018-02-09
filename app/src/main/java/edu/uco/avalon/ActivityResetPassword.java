package edu.uco.avalon;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Button reset = (Button) findViewById(R.id.btnreset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ActivityResetPassword.this)
                        .setTitle("Submit")
                        .setMessage("Thanks for contacting us, We will get back to you ASAP ")
                        .setPositiveButton("Go Back to Main", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(ActivityResetPassword.this, ActivityMain.class));

                            }
                        })
                        .create().show();
            }
        });

        contactUs();
    }

    private void contactUs() {
        TextView contact = (TextView) findViewById(R.id.help);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityResetPassword.this, ActivityContactUs.class));
            }
        });
    }

}
