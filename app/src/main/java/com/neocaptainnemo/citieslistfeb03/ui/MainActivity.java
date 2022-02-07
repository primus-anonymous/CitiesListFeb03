package com.neocaptainnemo.citieslistfeb03.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.os.Handler;

import com.neocaptainnemo.citieslistfeb03.R;
import com.neocaptainnemo.citieslistfeb03.domain.City;
import com.neocaptainnemo.citieslistfeb03.ui.details.CityDetailsFragment;
import com.neocaptainnemo.citieslistfeb03.ui.list.CitiesListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().setFragmentResultListener(CitiesListFragment.CITY_SELECTED, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                City city = result.getParcelable(CitiesListFragment.SELECTED_CITY_BUNDLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.container, CityDetailsFragment.newInstance(new City("1", R.drawable.nsk, "City One")), CityDetailsFragment.TAG)
                                //.replace(R.id.container, CityDetailsFragment.newInstance(city))
                                .addToBackStack("backstack1")
                                .commitAllowingStateLoss();

                    }
                }, 0L);

                      //  .commitNow()

//                Fragment fragment = getSupportFragmentManager().findFragmentByTag(CityDetailsFragment.TAG);
//
//                ((CityDetailsFragment) fragment).showHello();


             /*   getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, CityDetailsFragment.newInstance(new City("1", R.drawable.nsk, "City Two")), "2")
                        .add(R.id.container, CityDetailsFragment.newInstance(new City("1", R.drawable.nsk, "City Three")), "3")
//                        .replace(R.id.container, CityDetailsFragment.newInstance(city))
                        .addToBackStack("backstack2")
                        .commit();


                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, CityDetailsFragment.newInstance(new City("1", R.drawable.nsk, "City Four")), "4")
//                        .replace(R.id.container, CityDetailsFragment.newInstance(city))
                        .addToBackStack("backstack3")
                        .commit();*/


            }
        });
    }


}