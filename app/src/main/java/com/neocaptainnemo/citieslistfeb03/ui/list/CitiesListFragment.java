package com.neocaptainnemo.citieslistfeb03.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.neocaptainnemo.citieslistfeb03.R;
import com.neocaptainnemo.citieslistfeb03.domain.CitiesRepositoryImpl;
import com.neocaptainnemo.citieslistfeb03.domain.City;

import java.util.List;

public class CitiesListFragment extends Fragment implements CitiesListView {

    public static final String CITY_SELECTED = "CITY_SELECTED";
    public static final String SELECTED_CITY_BUNDLE = "SELECTED_CITY_BUNDLE";

    private LinearLayout container;

    private CitiesListPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new CitiesListPresenter(this, CitiesRepositoryImpl.getInstance());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        container = view.findViewById(R.id.container);

        presenter.requestCities();
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
    public void showCities(List<City> cities) {

        for (City city : cities) {
            View itemView = getLayoutInflater().inflate(R.layout.item_city, container, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(SELECTED_CITY_BUNDLE, city);

                    getParentFragmentManager()
                            .setFragmentResult(CITY_SELECTED, bundle);

                }
            });
            ImageView coatOfArms = itemView.findViewById(R.id.coat_of_arms);
            coatOfArms.setImageResource(city.getImage());

            TextView name = itemView.findViewById(R.id.name);
            name.setText(city.getName());

            container.addView(itemView);

        }
    }

    public void showHello() {

        Toast.makeText(requireContext(), "Hello, world!", Toast.LENGTH_SHORT).show();
    }
}
