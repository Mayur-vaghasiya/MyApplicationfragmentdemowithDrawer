package com.example.peacock.myapplicationfragmentdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peacock.myapplicationfragmentdemo.R;
import com.example.peacock.myapplicationfragmentdemo.adapter.NavigationAdapter;
import com.example.peacock.myapplicationfragmentdemo.fragment.DashBoard;
import com.example.peacock.myapplicationfragmentdemo.fragment.fragmentone;
import com.example.peacock.myapplicationfragmentdemo.fragment.fragmentsecond;
import com.example.peacock.myapplicationfragmentdemo.fragment.fragmentthired;
import com.example.peacock.myapplicationfragmentdemo.interfaces.ChangeCurrentFragment;
import com.example.peacock.myapplicationfragmentdemo.interfaces.DrawerLocker;
import com.example.peacock.myapplicationfragmentdemo.model.DashBoarditem;

import java.util.ArrayList;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements ChangeCurrentFragment,
        FragmentManager.OnBackStackChangedListener, View.OnClickListener, DrawerLocker {

    //Navigation Drawer
    private RecyclerView Recycler_right;
    private LinearLayoutManager lm;
    private DrawerLayout mDrawerlayout;


    private int size;
    private ArrayList<DashBoarditem> itemlist;
    private Activity activity = null;
    private Toolbar toolbar = null;
    private AppCompatTextView actv_header_name;

    private ChangeCurrentFragment ChangeCurrentFragment;
    private String fragment_name, Tag;
    private ImageView toolbar_ivNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = MainActivity.this;
        itemlist = new ArrayList<DashBoarditem>();
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        Recycler_right = (RecyclerView) findViewById(R.id.recycler_right);
        lm = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);

        Recycler_right.setLayoutManager(lm);

        ChangeCurrentFragment = (ChangeCurrentFragment) activity;//make object


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actv_header_name = (AppCompatTextView) toolbar.findViewById(R.id.actv_header_name);

        //manageFragments(new DashBoard(), getString(R.string.dashboard));

        manageFragments(new fragmentone(), getString(R.string.fargmentones));

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerlayout.setDrawerListener(toggle);
        toggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                initDrawer();
            }
        });


        // To Set List in Navigation Drawer..
        itemlist = new ArrayList<>();
        itemlist.add(new DashBoarditem("1", "Search", "c"));
        itemlist.add(new DashBoarditem("2", "ExExEx", "K"));
        itemlist.add(new DashBoarditem("3", "Fcolorstone", "L"));

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Recycler_right.setAdapter(new NavigationAdapter(activity, itemlist, new NavigationAdapter.OnItemsClickListener() {
                    @Override
                    public void onClick(String itemid) {

                        overridePendingTransition(R.anim.left_in, R.anim.right_out);
                        setselectedmenu(itemid);
                    }
                }));
            }
        }, 500);
    }


    @Override
    public void onFancyColorSelected(String title, String selectedColors) {

    }


    public Toolbar getToolbar() {

        return toolbar;

    }

    public TextView getToolbarTextView() {

        return actv_header_name;

    }

    private void manageFragments(Fragment fragmentName, String tag) {

        String fragment_name = fragmentName.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragment_popped = manager.popBackStackImmediate(fragment_name, 0);

        if (!fragment_popped) { //fragment not in back stack, create it.

            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.fl_content_layout, fragmentName, tag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(fragment_name);
            ft.commit();

        }
    }

    @Override
    public void onBackPressed() {


        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {

            finish();
            //  overridePendingTransition(R.anim.left_in, R.anim.right_out);
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

        } else {

            super.onBackPressed();
            // overridePendingTransition(R.anim.left_in, R.anim.right_out);
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

        }

    }


    @Override
    public void onBackStackChanged() {

        Object object = getSupportFragmentManager().findFragmentById(R.id.fl_content_layout);

        if (object != null) {

            ((Fragment) object).onResume();
            mDrawerlayout.closeDrawer(Recycler_right);
        }
    }

    @Override
    public void onFragmentChangeListener(Fragment fragment, String tag) {

        manageFragments(fragment, tag);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }
    }

    public void initDrawer() {

        if (mDrawerlayout.isDrawerOpen(Recycler_right)) {
            mDrawerlayout.closeDrawer(Recycler_right);
        } else {
            mDrawerlayout.openDrawer(Recycler_right);
        }
    }


    @Override
    public void setDrawerEnabled(boolean enabled) {


        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED :
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        mDrawerlayout.setDrawerLockMode(lockMode);
        //  toggle.setDrawerIndicatorEnabled(enabled);
    }

    private void setselectedmenu(String id) {

        FragmentManager manager = getSupportFragmentManager();


        for (int i = manager.getBackStackEntryCount() - 1; i > 0; i--) {
            if (!manager.getBackStackEntryAt(i).getName().equalsIgnoreCase(getString(R.string.dashboard))) {
                manager.popBackStack();
            } else {
                break;
            }
        }

        if (id == "1") {


            ChangeCurrentFragment.onFragmentChangeListener(new fragmentone(), "search");


        } else if (id == "2") {

            ChangeCurrentFragment.onFragmentChangeListener(new fragmentsecond(),"ExExEx");


        } else if (id == "3") {


            ChangeCurrentFragment.onFragmentChangeListener(new fragmentthired(), "Fcolorstone");
        }
        mDrawerlayout.closeDrawer(Recycler_right);
    }
}
