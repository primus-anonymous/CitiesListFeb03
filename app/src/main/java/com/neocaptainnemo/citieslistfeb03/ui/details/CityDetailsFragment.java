package com.neocaptainnemo.citieslistfeb03.ui.details;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.neocaptainnemo.citieslistfeb03.R;
import com.neocaptainnemo.citieslistfeb03.domain.City;
import com.neocaptainnemo.citieslistfeb03.ui.MainActivity;
import com.neocaptainnemo.citieslistfeb03.ui.NavDrawable;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof NavDrawable) {
            ((NavDrawable)requireActivity()).setAppBar(toolbar);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_share) {
                    Toast.makeText(requireContext(), "Shared", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });

        coatOfArms = view.findViewById(R.id.coat_of_arms);

        title = view.findViewById(R.id.title);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(requireContext(), view);

                popupMenu.getMenuInflater().inflate(R.menu.menu_title_pop_up, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_search) {
                            Toast.makeText(requireContext(), "Search", Toast.LENGTH_SHORT).show();
                            return true;
                        }

                        if (item.getItemId() == R.id.action_copy_to_clipboard) {
                            Toast.makeText(requireContext(), "Clipboard", Toast.LENGTH_SHORT).show();
                            return true;
                        }

                        return false;
                    }
                });

                popupMenu.show();
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

    public void showHello() {
        Toast.makeText(requireContext(), "City Details Hello!", Toast.LENGTH_SHORT).show();
    }
}
