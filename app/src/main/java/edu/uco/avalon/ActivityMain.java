package edu.uco.avalon;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import edu.uco.avalon.Users.Administrator;
import edu.uco.avalon.Users.EquipmentManager;
import edu.uco.avalon.Users.ProjectOwner;
import edu.uco.avalon.Users.UserDirectory;

public class ActivityMain extends AppCompatActivity {
    int trys =0;
    Spinner spinner;
    ArrayAdapter<CharSequence> adp;
    UserDirectory userDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Administrator admin = new Administrator("a", "a");
        ProjectOwner po = new ProjectOwner("b","b");
        EquipmentManager eqm = new EquipmentManager("c","c");
        userDirectory = new UserDirectory();
        userDirectory.addUser(admin);
        userDirectory.addUser(po);
        userDirectory.addUser(eqm);

        Login();
        ForgetPassword();
        contactUs();
    }

    private void contactUs() {
        TextView contact = (TextView) findViewById(R.id.help);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMain.this, ActivityContactUs.class));
            }
        });
    }

    private void ForgetPassword() {

        TextView forget = (TextView) findViewById(R.id.txtForget);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMain.this, ActivityResetPassword.class));
            }
        });
    }

    private void Login() {
        Button login = (Button) findViewById(R.id.btnlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inUser = ((TextView) findViewById(R.id.edit_username)).getText().toString().toLowerCase();
                String inPassword = ((TextView) findViewById(R.id.edit_password)).getText().toString();
                if(trys > 2){
                    new AlertDialog.Builder(ActivityMain.this)
                            .setTitle("Account Lock")
                            .setMessage("Your Account has been lock due to many tries please contact the admin to unlock if for you")
                            .setPositiveButton("Cantact Admin", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(ActivityMain.this, ActivityContactUs.class));

                                }
                            })
                            .setNegativeButton("cancel", null).create().show();
                }
                else if (userDirectory.getUserCredentials(inUser, inPassword) != 0)
                    if(userDirectory.getUserCredentials(inUser, inPassword) == 1) {
                        startActivity(new Intent(ActivityMain.this, AdminListActivity.class));
                    }
                    else if(userDirectory.getUserCredentials(inUser, inPassword) == 2) {
                        startActivity(new Intent(ActivityMain.this, ProjectSelection.class));
                    }
                    else if(userDirectory.getUserCredentials(inUser, inPassword) == 3) {
                        startActivity(new Intent(ActivityMain.this, EquipmentOptionsActivity.class));
                    }
                    else{
                    trys++;
                    new AlertDialog.Builder(ActivityMain.this)
                            .setTitle("wrong Password")
                            .setMessage("You entered an invalid passowrd")
                            .setPositiveButton("Reset Password", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(ActivityMain.this, ActivityResetPassword.class));

                                }
                            })
                            .setNegativeButton("Try Again", null).create().show();
                    }
                else{
                    new AlertDialog.Builder(ActivityMain.this)
                            .setTitle("wrong User name")
                            .setMessage("You entered an invalid userName")
                            .setPositiveButton("Cantact Admin", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(ActivityMain.this, ActivityContactUs.class));

                                }
                            })
                            .setNegativeButton("Try Again", null).create().show();
                }

            }
        });
    }

    //TODO - Just to bypass the login screen for now
    public void gotoProjectSelection(View view){
        Intent intent = new Intent(this, ProjectSelection.class);
        startActivity(intent);
    }

}
