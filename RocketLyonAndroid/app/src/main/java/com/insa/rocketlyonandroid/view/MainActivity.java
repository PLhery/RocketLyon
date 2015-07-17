package com.insa.rocketlyon.view;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.insa.rocketlyon.R;
import com.insa.rocketlyon.utils.SPManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import trikita.log.Log;

public class MainActivity extends AppCompatActivity {
    private static final long DRAWER_CLOSE_DELAY_MS = 250;
    private static final long DRAWER_CLOSE_FIRST_USER_DELAY_MS = 1000;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        setupNavDrawer();

        SPManager.clear(this);
        boolean oldUser = SPManager.load(this, "FIRST_USER");
        Log.d(oldUser);
        if(!oldUser) {
            oldUser = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }, DRAWER_CLOSE_FIRST_USER_DELAY_MS);
            SPManager.save(this, oldUser, "FIRST_USER");
        }

        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        }
    }

    private void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //toolbar.setNavigationIcon(R.drawable.ic_drawer);
        }
    }

    private void navigate(int itemId) {
        switch (itemId) {
            case R.id.navigation_item_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
                break;
            case R.id.navigation_item_2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FavoritesFragment()).commit();
                break;
            case R.id.navigation_item_3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new NearStationsFragment()).commit();
                break;
            default:
                Log.d("Clickiti.");
        }
    }

    private void setupNavDrawer() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem menuItem) {
                menuItem.setChecked(true);
                Log.d(menuItem.getItemId());
                drawerLayout.closeDrawer(GravityCompat.START);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navigate(menuItem.getItemId());
                    }
                }, DRAWER_CLOSE_DELAY_MS);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
