package com.example.sankhedekar.test;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;


public class TabLayoutActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, NetworkType.NetworkTypeListener{
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.pager);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Bank Accounts"));
        tabLayout.addTab(tabLayout.newTab().setText("Credit Cards"));
        tabLayout.addTab(tabLayout.newTab().setText("Total"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void mySnackBar(int connectedType) {
        String message;
        if (connectedType == 1) {
            message = "Wifi enabled.";
        }
        else if (connectedType == 2) {
            message = "Mobile data enabled.";
        }
        else {
            message = "No internet connection.";
        }

        Snackbar snackbar = Snackbar.make(findViewById(R.id.cl), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        BroadcastReceiver broadcastReceiver = new NetworkType();
        registerReceiver(broadcastReceiver, intentFilter);

        AppNetworkListener.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(int connectedType) {
        mySnackBar(connectedType);
    }
}
