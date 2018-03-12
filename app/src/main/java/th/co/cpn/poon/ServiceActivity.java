package th.co.cpn.poon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ServiceActivity extends AppCompatActivity {

//    Explicit

    private String tag = "12MarchV1";
    private String[] loginStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

//        Get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("Login");
        Log.wtf(tag, "NameLogin ==>" + loginStrings[1]);

    }  // Main Method




}   //Main Class
