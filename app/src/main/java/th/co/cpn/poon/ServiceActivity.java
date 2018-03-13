package th.co.cpn.poon;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import th.co.cpn.poon.fragment.DisplayQRfragment;
import th.co.cpn.poon.fragment.InfoFragment;
import th.co.cpn.poon.fragment.QRscanFragment;
import th.co.cpn.poon.fragment.ShowAllFragment;

public class ServiceActivity extends AppCompatActivity {

//    Explicit

    private String tag = "12MarchV1";
    private String[] loginStrings;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private boolean aBoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

//        Get Value From Intent
        getValueFromIntent();

        //Create Toolbar

        createToolbar();


//        Add Fragment to Activity
        addFragment(savedInstanceState);

//        SHow Readall
        SHowReadall();

//        QR scan
        QRScan();

//        Exit Controller
        exitController();


    }  // Main Method




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_service, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {

    }

    private void exitController() {
        TextView textView = findViewById(R.id.txtExit);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }

    private void QRScan() {
        TextView textView = findViewById(R.id.txtQRscan);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentServiceFragment, QRscanFragment.qRscanInstance(loginStrings))
                        .commit();
                drawerLayout.closeDrawers();
            }
        });

    }

    private void SHowReadall() {
        TextView textView = findViewById(R.id.txtShowAll);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.contentServiceFragment, new ShowAllFragment()).commit();
                drawerLayout.closeDrawers();

            }
        });
    }

    private void addFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            if (aBoolean) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.contentServiceFragment, new ShowAllFragment()).commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.contentServiceFragment,
                                DisplayQRfragment.displayQRInstance(getIntent().getStringExtra("QRcode"), loginStrings))
                        .commit();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemInfo) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentServiceFragment, new InfoFragment()).commit();
            return true;
        }


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
        aBoolean = getIntent().getBooleanExtra("Status", true);
        Log.wtf(tag, "NameLogin ==>" + loginStrings[1]);
    }


}   //Main Class
