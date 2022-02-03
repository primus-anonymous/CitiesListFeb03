package com.neocaptainnemo.citieslistfeb03.ui.list;

import com.neocaptainnemo.citieslistfeb03.domain.CitiesRepository;
import com.neocaptainnemo.citieslistfeb03.domain.City;

import java.util.List;

public class CitiesListPresenter {

    private final CitiesListView view;
    private final CitiesRepository repository;

    public CitiesListPresenter(CitiesListView view, CitiesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestCities() {
        List<City> cities = repository.getCities();

        view.showCities(cities);
    }
}
