package com.neocaptainnemo.citieslistfeb03.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.neocaptainnemo.citieslistfeb03.R;
import com.neocaptainnemo.citieslistfeb03.domain.City;
import com.neocaptainnemo.citieslistfeb03.ui.list.CitiesListFragment;

public class CityDetailsFragment extends Fragment {

    private static final String ARG_CITY = "ARG_CITY";
    private ImageView coatOfArms;
    private TextView title;

    public CityDetailsFragment() {
        super(R.layout.fragment_city_details);
    }

    public static CityDetailsFragment newInstance(City city) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_CITY, city);

        CityDetailsFragment fragment = new CityDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        coatOfArms = view.findViewById(R.id.coat_of_arms);

        title = view.findViewById(R.id.title);

        getParentFragmentManager()
                .setFragmentResultListener(CitiesListFragment.CITY_SELECTED, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        City city = result.getParcelable(CitiesListFragment.SELECTED_CITY_BUNDLE);

                        updateCity(city);
                    }
                });

        Bundle arguments = getArguments();

        if (arguments != null && arguments.containsKey(ARG_CITY)) {
            City city = arguments.getParcelable(ARG_CITY);

            updateCity(city);

        }
    }

    private void updateCity(City city) {
        coatOfArms.setImageResource(city.getImage());

        title.setText(city.getName());
    }
}
