package com.neocaptainnemo.citieslistfeb03.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.neocaptainnemo.citieslistfeb03.R;
import com.neocaptainnemo.citieslistfeb03.domain.City;

public class CityDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_CITY = "EXTRA_CITY";

    public static void show(Context context, City city) {
        Intent intent = new Intent(context, CityDetailsActivity.class);
        intent.putExtra(EXTRA_CITY, city);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citiy_details);

        if (savedInstanceState == null) {

            City city = getIntent().getParcelableExtra(EXTRA_CITY);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_list, CityDetailsFragment.newInstance(city))
                    .commit();
        }
    }
}