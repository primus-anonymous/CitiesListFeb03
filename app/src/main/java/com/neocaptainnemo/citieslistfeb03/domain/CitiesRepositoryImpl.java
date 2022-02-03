package com.neocaptainnemo.citieslistfeb03.domain;

import com.neocaptainnemo.citieslistfeb03.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CitiesRepositoryImpl implements CitiesRepository {

    private static final CitiesRepository INSTANCE = new CitiesRepositoryImpl();

    public static CitiesRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public List<City> getCities() {
        ArrayList<City> cities = new ArrayList<>();

        cities.add(new City(UUID.randomUUID().toString(), R.drawable.ebrg, "Екатеринбург"));
        cities.add(new City(UUID.randomUUID().toString(), R.drawable.msc, "Москва"));
        cities.add(new City(UUID.randomUUID().toString(), R.drawable.nsk, "Новосибирск"));
        cities.add(new City(UUID.randomUUID().toString(), R.drawable.sam, "Самара"));
        cities.add(new City(UUID.randomUUID().toString(), R.drawable.spb, "Санкт-Петербург"));

        return cities;
    }
}
