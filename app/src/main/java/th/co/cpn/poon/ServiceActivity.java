package th.co.cpn.poon;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

public class ServiceActivity extends AppCompatActivity {

//    Explicit

    private String tag = "12MarchV1";
    private String[] loginStrings;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

//        Get Value From Intent
        getValueFromIntent();

        //Create Toolbar

        createToolbar();


    }  // Main Method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarDrawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();

    }

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarService);
            setSupportActionBar(toolbar);

//            setup Title
        getSupportActionBar().setTitle("My Service");
        getSupportActionBar().setSubtitle(loginStrings[1]);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Create Hamberger Icon
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(ServiceActivity.this,drawerLayout, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);



    }

    private void getValueFromIntent() {
        loginStrings = getIntent().getStringArrayExtra("Login");
        Log.wtf(tag, "NameLogin ==>" + loginStrings[1]);
    }


}   //Main Class
