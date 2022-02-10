package com.neocaptainnemo.citieslistfeb03.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.neocaptainnemo.citieslistfeb03.R;
import com.neocaptainnemo.citieslistfeb03.domain.CitiesRepositoryImpl;
import com.neocaptainnemo.citieslistfeb03.domain.City;
import com.neocaptainnemo.citieslistfeb03.ui.MainActivity;
import com.neocaptainnemo.citieslistfeb03.ui.NavDrawable;

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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof NavDrawable) {
            ((NavDrawable)requireActivity()).setAppBar(toolbar);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_add) {
                    Toast.makeText(requireContext(), "Add", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.action_clear_all) {
                    Toast.makeText(requireContext(), "Clear All", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });

        container = view.findViewById(R.id.container);

        presenter.requestCities();
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
