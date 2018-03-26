package edu.uco.avalon;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by cdcal on 2/7/2018.
 */

public class AddUserFragment extends Fragment {
    Button save;
    EditText Fname;
    EditText Lname;
    EditText Email;
    EditText Pass;
    EditText Flag;

    Button delette;

    public AddUserFragment(){


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        Fname = (EditText) view.findViewById(R.id.Fname);
        Lname = (EditText) view.findViewById(R.id.Lname);
        Email = (EditText) view.findViewById(R.id.Email);
        Pass = (EditText) view.findViewById(R.id.pass);
        Flag = (EditText) view.findViewById(R.id.flag);



            save = (Button) view.findViewById(R.id.UserSave);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!((Fname.getText().toString()).isEmpty() || (Lname.getText().toString()).isEmpty()
                            || Email.getText().toString().isEmpty()|| Pass.getText().toString().isEmpty()
                            || Flag.getText().toString().isEmpty())) {

                        addUser();

                    } else

                    {
                        Toast.makeText(getContext(), "PLease Enter Input for All filed please.", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        if(this instanceof EditUserFragment){ //if in edit-mode, add the delete button stuff.
           // delette = view.findViewById(R.id.deletteMaintenanceButton);
            //delette.setVisibility(View.VISIBLE);
            /*delette.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteData(); //update to db
                }
            });
            */
        }
        return view;

    }
    public void addUser(){
        UserMgmt.userlist.add(new UserMgmt(Fname.getText().toString(), Lname.getText().toString(),
                Email.getText().toString(), Flag.getText().toString(), Pass.getText().toString()));

        getFragmentManager().popBackStack();

    }

    protected  void deleteData(){}



}
