package com.neocaptainnemo.citieslistfeb03.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.neocaptainnemo.citieslistfeb03.R;
import com.neocaptainnemo.citieslistfeb03.domain.City;
import com.neocaptainnemo.citieslistfeb03.ui.list.CitiesListFragment;

public class CityDetailsFragment extends Fragment {

    public static final String TAG = "CityDetailsFragment";

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        coatOfArms = view.findViewById(R.id.coat_of_arms);

        title = view.findViewById(R.id.title);

        view.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                getParentFragmentManager()
//                        .popBackStack("backstack1", 0);

                Fragment fragment = getParentFragmentManager().findFragmentByTag(CityDetailsFragment.TAG);

                if (fragment instanceof CityDetailsFragment) {

                }

                if (fragment != null) {
                    getParentFragmentManager()
                            .beginTransaction()
                            .remove(fragment)
                            .commit();
                }

            }
        });

        Bundle arguments = getArguments();

        if (arguments != null && arguments.containsKey(ARG_CITY)) {
            City city = arguments.getParcelable(ARG_CITY);

            updateCity(city);

        }

        getChildFragmentManager()
                .setFragmentResultListener(CitiesListFragment.CITY_SELECTED, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Toast.makeText(requireContext(), "Result", Toast.LENGTH_SHORT).show();
                    }
                });

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.child_container, new CitiesListFragment())
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();

        Toast.makeText(requireContext(), "Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();

        Toast.makeText(requireContext(), "Stopped", Toast.LENGTH_SHORT).show();
    }

    private void updateCity(City city) {
        coatOfArms.setImageResource(city.getImage());

        title.setText(city.getName());
    }

    public void showHello() {
        Toast.makeText(requireContext(), "City Details Hello!", Toast.LENGTH_SHORT).show();
    }
}
