package com.example.myapi.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
{
    private FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        fragmentManager=getSupportFragmentManager ();
        fragmentManager
                .beginTransaction ()
                .add ( R.id.container, new NewsFragment ( "sports" ) )
                .commit ();
        initBottom();
        initActionBar();
    }

    private void initActionBar()
    {
        ActionBar actionBar = getSupportActionBar ();
        actionBar.setDisplayHomeAsUpEnabled ( true );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId () == android.R.id.home)
        {
            onBackPressed ();
        }

        return super.onOptionsItemSelected ( item );
    }

    private void initBottom()
    {
        bottomNavigationView = findViewById ( R.id.bottom_nav );
        bottomNavigationView.setOnNavigationItemSelectedListener ( new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId ())
                {
                    case R.id.sports:
                        loadFragment ( new NewsFragment ( "sports" ) );
                        item.setChecked ( true );
                        break;

                    case R.id.science:
                        loadFragment ( new NewsFragment ( "science" ) );
                        item.setChecked ( true );
                        break;

                    case R.id.technology:
                        loadFragment ( new NewsFragment ( "technology" ) );
                        item.setChecked ( true );
                        break;
                }

                return false;
            }
        } );
    }

    public void loadFragment(Fragment fragment)
    {
        fragmentManager
                .beginTransaction ()
                .replace ( R.id.container,fragment )
                .commit ();
    }
}