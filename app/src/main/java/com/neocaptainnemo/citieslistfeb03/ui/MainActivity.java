package com.neocaptainnemo.citieslistfeb03.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.google.android.material.navigation.NavigationView;
import com.neocaptainnemo.citieslistfeb03.R;
import com.neocaptainnemo.citieslistfeb03.domain.City;
import com.neocaptainnemo.citieslistfeb03.ui.details.CityDetailsFragment;
import com.neocaptainnemo.citieslistfeb03.ui.list.CitiesListFragment;

public class MainActivity extends AppCompatActivity implements NavDrawable{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawer);

        NavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_cities) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new CitiesListFragment())
                            .commit();

                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                if (item.getItemId() == R.id.action_2) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new Fragment())
                            .commit();

                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return false;
            }
        });


        getSupportFragmentManager().setFragmentResultListener(CitiesListFragment.CITY_SELECTED, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                City city = result.getParcelable(CitiesListFragment.SELECTED_CITY_BUNDLE);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, CityDetailsFragment.newInstance(city), CityDetailsFragment.TAG)
                        .addToBackStack("backstack1")
                        .commit();


            }
        });
    }


    @Override
    public void setAppBar(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer
        );

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

    }
}