package th.co.cpn.poon.fragment;

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

import org.w3c.dom.Text;

import th.co.cpn.poon.R;
import th.co.cpn.poon.utility.GetAllData;
import th.co.cpn.poon.utility.MyAlert;
import th.co.cpn.poon.utility.MyConstance;

/**
 * Created by kiakkarachai on 07/03/2018.
 */

public class MainFragment extends Fragment{

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
                  myAlert.myDialog("Have Space","Please Fill All User and Passowrd");


              } else {

//                  No Space
                  try {

                      MyConstance myConstance = new MyConstance();
                      GetAllData getAllData = new GetAllData(getActivity());
                      getAllData.execute(myConstance.getUrlReadAllUser());

                      String jsonString = getAllData.get();
                      Log.wtf("8MarchV1", "JSON ==>" + jsonString);

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
        View view = inflater.inflate(R.layout.fragment_main, container , false);
        return view;
    }


} // Main Class
