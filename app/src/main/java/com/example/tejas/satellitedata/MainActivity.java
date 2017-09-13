package com.example.tejas.satellitedata;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static String link;
    int id;
    display_data fragment=null;
    public static String offline;
    private static String title="Category 1";

    public static Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle(title);
        fragmentManager = getFragmentManager();
        fragment = new display_data();
        fragmentTransaction = fragmentManager.beginTransaction();
        link = "https://satellitedata.000webhostapp.com/Summary_obc.php";
        offline="file:///android_asset/summary.html";
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        id = item.getItemId();
        fragmentManager = getFragmentManager();
        fragment = new display_data();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch(id){
            case R.id.nav_1:
                    link = "http://www.google.co.in"; //Each of the 6 links represents is to load a different webpage. Sample webpage provided is the Google homepage
                        toolbar.setTitle("Category 1");
                    offline="http://www.google.co.in";

                    break;
            case R.id.nav_2:
                    link = "http://www.google.co.in";
                    offline="http://www.google.co.in";
                toolbar.setTitle("Category 2");

                break;
            case R.id.nav_3:
                    link = "http://www.google.co.in";
                offline="http://www.google.co.in";
                toolbar.setTitle("Category 3");
                break;

            case R.id.nav_4:
                    link = "http://www.google.co.in";
                offline="http://www.google.co.in";
                toolbar.setTitle("Category 4");
                break;

            case R.id.nav_5:
                    link = "http://www.google.co.in";
                offline="http://www.google.co.in";
                toolbar.setTitle("Category 5");
                break;

            case R.id.nav_6:
                    link = "http://www.google.co.in";
                offline="http://www.google.co.in";
                toolbar.setTitle("Category 6");
                break;
                }
                if(fragment!=null){
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
