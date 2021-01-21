package com.jayjay.splasher;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    CustomView customView;
    LinearLayout headercontent;

    public Toolbar toolbar;

    String NameHolder;
    TextView Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //
        Intent intent = getIntent();

        // setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        headercontent = (LinearLayout) header.findViewById(R.id.headercontent);
//        headercontent.animate().y(0);
        headercontent.setAlpha(0);
        customView = (CustomView) header.findViewById(R.id.customview);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        NameHolder = intent.getStringExtra(MainActivity.userName);

        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigation.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.textViw);
        navUsername.setText(NameHolder);

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//          Passing slideOffset value to rotateRect() method for rotation of rectangles in custom view
                customView.rotateRect(slideOffset);
                headercontent.animate().alpha(slideOffset);
                headercontent.animate().yBy(slideOffset);

            }

            @Override
            public void onDrawerOpened(View drawerView) {

                headercontent.animate().y(150);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                headercontent.setAlpha(0);
                headercontent.animate().y(0);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        toggle.syncState();


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
        int id = item.getItemId();

        if (id == R.id.logout) {
            finish();
            Toast.makeText(DashboardActivity.this,"Log Out Successful", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}