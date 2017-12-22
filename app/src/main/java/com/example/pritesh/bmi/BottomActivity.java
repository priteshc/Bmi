package com.example.pritesh.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.roughike.bottombar.OnTabClickListener;
import com.roughike.bottombar.OnTabSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by yashwant on 6/25/2016.
 */
public class BottomActivity extends AppCompatActivity {

    private BottomBar mBottomBar;


    Toolbar t;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        t = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(t);
        //t.setTitle(R.string.app_name);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mBottomBar = BottomBar.attach(this, savedInstanceState);



       // mBottomBar.useFixedMode();
   //     mBottomBar.useDarkTheme();


        mBottomBar.setItems(R.menu.tab);

        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(1, "#FF9800");
        mBottomBar.mapColorForTab(2, "#7B1FA2");
        mBottomBar.mapColorForTab(3, "#FF5252");



       mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
           @Override
           public void onMenuTabSelected(@IdRes int menuItemId) {

               if (menuItemId == R.id.bottomBarItemOne) {
                   // The user selected item number one.

                   Toast.makeText(BottomActivity.this,"one",Toast.LENGTH_SHORT).show();


                   SpeedometerActivity someFragment = new SpeedometerActivity();
                   android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                   fragmentManager.beginTransaction().replace(R.id.My_Container_1_ID, someFragment).commit();

               }



               if (menuItemId == R.id.bottomBarItemOne2) {
                   // The user selected item number one.

                   Toast.makeText(BottomActivity.this,"one",Toast.LENGTH_SHORT).show();


                   BodyShape someFragment = new BodyShape();
                   android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                   fragmentManager.beginTransaction().replace(R.id.My_Container_1_ID, someFragment).commit();

               }

               if (menuItemId == R.id.bottomBarItemOne3) {
                   // The user selected item number one.

                   Toast.makeText(BottomActivity.this,"one",Toast.LENGTH_SHORT).show();


                   MyChart someFragment = new MyChart();
                   android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                   fragmentManager.beginTransaction().replace(R.id.My_Container_1_ID, someFragment).commit();


               }




           }

           @Override
           public void onMenuTabReSelected(@IdRes int menuItemId) {


           }
       });


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        mBottomBar.onSaveInstanceState(outState);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.addweight, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(BottomActivity.this,AddWeight.class);
        startActivity(intent);
        finish();

        return super.onOptionsItemSelected(item);
    }
}
