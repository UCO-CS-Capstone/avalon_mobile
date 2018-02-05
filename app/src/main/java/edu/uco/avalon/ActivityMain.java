package edu.uco.avalon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //TODO - Just to bypass the login screen for now
    public void gotoProjectSelection(View view){
        Intent intent = new Intent(this, ProjectSelection.class);
        startActivity(intent);
    }
}
