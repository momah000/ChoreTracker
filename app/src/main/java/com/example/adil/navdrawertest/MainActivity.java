package com.example.adil.navdrawertest;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                     //
        setContentView(R.layout.activity_main);                 // SETTING THE MAIN MENU PAGE UP
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //
        setSupportActionBar(toolbar);                           //

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);                                  // SETTING UP TABS
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);  // TO OPEN AND CLOSE DRAWER
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.   // THIS ADDS PLUS BUTTON
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.             // ACTION WHEN PLUS BUTTON IS PRESSED
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(MainActivity.this,CreateChore.class);
            startActivityForResult(myIntent,1);

        }

        return super.onOptionsItemSelected(item);
    }*/
   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) // gets data from new profile creation
    {

        Log.e("CHECK", "We are in onactivity");

            Fragment fragment = (Fragment) getFragmentManager().findFragmentById(R.id.container);
          //  Intent myIntent = new Intent(MainActivity.this,ChoreList.class);
           // MainActivity.this.startActivity(myIntent);
          //  fragment.
           fragment.onActivityResult(requestCode,resultCode,data);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {            // ACTION WHEN SOMETHING IN NAVIGATION DRAWER IS PRESSED
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent myIntent = new Intent(MainActivity.this,ProfilesActivity.class);
            MainActivity.this.startActivity(myIntent);

        } else if (id == R.id.nav_gallery) {
            //Create intent to open nav gallery
            //Start activity with intent we created
            //return true because we dealt with the event listener properly
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent myIntent = new Intent(MainActivity.this,CreateProfile.class);
            MainActivity.this.startActivity(myIntent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else  if (id == R.id.action_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////

    private void setupViewPager(ViewPager viewPager) {                               // YOU CAN ADD MORE PAGES FROM HERE
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ChoreList(), "Chores");
        adapter.addFragment(new Calendar(), "Calendar");
        viewPager.setAdapter(adapter);

    }

}
