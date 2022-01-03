package com.example.myapi.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myapi.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
{
    private FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;

    String country;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        fragmentManager=getSupportFragmentManager ();
        fragmentManager
                .beginTransaction ()
                .add ( R.id.container, new NewsFragment ( "sports", "eg" ) )
                .commit ();
        initBottom();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater ().inflate ( R.menu.country_menu,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.egypt:
                System.out.println("country");
                country = "eg";
                loadFragment ( new NewsFragment ( "sports", country ) );
                break;

            case R.id.germany:
                System.out.println("country");
                country="de";
                loadFragment ( new NewsFragment ( "sports", country ) );
                break;

            case R.id.ksa:
                System.out.println("country");
                country="sa";
                loadFragment ( new NewsFragment ( "sports", country ) );
                break;
        }

        return true;
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
                        loadFragment ( new NewsFragment ( "sports", country ) );
                        item.setChecked ( true );
                        break;

                    case R.id.science:
                        loadFragment ( new NewsFragment ( "science", country ) );
                        item.setChecked ( true );
                        break;

                    case R.id.technology:
                        loadFragment ( new NewsFragment ( "technology", country ) );
                        item.setChecked ( true );
                        break;
                }

                return false;
            }
        } );
    }

    public void loadFragment(Fragment fragment)
    {
        System.out.println(country);
        fragmentManager
                .beginTransaction ()
                .replace ( R.id.container,fragment )
                .commit ();
    }

}