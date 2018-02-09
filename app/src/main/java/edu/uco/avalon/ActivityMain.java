package edu.uco.avalon;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.uco.avalon.Users.Administrator;
import edu.uco.avalon.Users.EquipmentManager;
import edu.uco.avalon.Users.ProjectOwner;
import edu.uco.avalon.Users.UserDirectory;

public class ActivityMain extends AppCompatActivity {

    EditText editUser;
    EditText editPass;
    Button btnLogin;
    TextView forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser = (EditText)findViewById(R.id.edit_username);
        editPass = (EditText)findViewById(R.id.edit_password);
        forgotPass = (TextView)findViewById(R.id.text_forgot_password);

        Administrator administrator = new Administrator("aaa@boom.net", "123");
        ProjectOwner projectOwner = new ProjectOwner("bbb@boom.net", "456");
        EquipmentManager equipmentManager = new EquipmentManager("ccc@boom.net", "789");
        final UserDirectory userDirectory = new UserDirectory();

        userDirectory.addUser(administrator);
        userDirectory.addUser(projectOwner);
        userDirectory.addUser(equipmentManager);

        btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.user_layout);
                FragmentManager fm = getSupportFragmentManager();
                if(userDirectory.getUserCredentials(editUser.getText().toString(), editPass.getText().toString())==1) {
                    AdminFragment admin = new AdminFragment();
                    setTitle("Admin");
                    fm.beginTransaction().replace(R.id.user_container, admin).commit();
                }
                if(userDirectory.getUserCredentials(editUser.getText().toString(), editPass.getText().toString())==2) {
                    ProjOwnFrag projOwn = new ProjOwnFrag();
                    setTitle("PO");
                    fm.beginTransaction().replace(R.id.user_container, projOwn).commit();
                }
                if(userDirectory.getUserCredentials(editUser.getText().toString(), editPass.getText().toString())==3) {
                    EquipMngrFrag equipMngr = new EquipMngrFrag();
                    setTitle("EQM");
                    fm.beginTransaction().replace(R.id.user_container, equipMngr).commit();
                }
            }
        });

    }

}
