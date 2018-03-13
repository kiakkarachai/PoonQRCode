package th.co.cpn.poon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import th.co.cpn.poon.R;
import th.co.cpn.poon.ServiceActivity;
import th.co.cpn.poon.utility.GetAllData;
import th.co.cpn.poon.utility.MyAlert;
import th.co.cpn.poon.utility.MyConstance;

/**
 * Created by kiakkarachai on 07/03/2018.
 */

public class MainFragment extends Fragment {

    //กด Alt + Insert เลือก onActivityCreated
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Register Controller  เพื่อสามารถให้กดได้
        registerController();

//        login Controler
        loginControler();

    }   // Main Method

    private void loginControler() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              Get Value From Edit Text
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {

//                  Have space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Have Space", "Please Fill All User and Passowrd");


                } else {

//                  No Space
                    try {

                        MyConstance myConstance = new MyConstance();
                        GetAllData getAllData = new GetAllData(getActivity());
                        getAllData.execute(myConstance.getUrlReadAllUser());

                        String jsonString = getAllData.get();
                        Log.wtf("8MarchV1", "JSON ==>" + jsonString);

                        JSONArray jsonArray = new JSONArray(jsonString);
                        String[] columnUserStrings = myConstance.getColumnUserTableStrings();
                        String[] loginStrings = new String[columnUserStrings.length];

                        boolean userStatus = true;

                        for (int i = 0; i < jsonArray.length(); i += 1) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            if (userString.equals(jsonObject.getString(columnUserStrings[2]))) {
                                //User true

                                userStatus = false;
                                for (int i1 = 0; i1 < columnUserStrings.length; i1 += 1) {
                                    loginStrings[i1] = jsonObject.getString(columnUserStrings[i1]);



                                    Log.wtf("8MarchV1", "loginString[" + i1 + "] ==>" + loginStrings[i1]);

                                } // For

                            } // If

                        }   // For

                        if (userStatus) {
//                            User False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("User False", "No this user in my Database");
                        } else if (passwordString.equals(loginStrings[3])) {
//                            Password true
                            Toast.makeText(getActivity(), "Welcome" + loginStrings[1], Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), ServiceActivity.class);
                            intent.putExtra("Login", loginStrings);
                            intent.putExtra("Status", true);
                            startActivity(intent);
                            getActivity().finish();

                        } else {
//                            password false
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("Password False", "Please Try Again Password False");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }  // if


            }
        });

    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            // Alt + Enter
            @Override
            public void onClick(View view) {
//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();


            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }


} // Main Class
