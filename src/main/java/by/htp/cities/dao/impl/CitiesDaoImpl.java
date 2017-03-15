package by.htp.cities.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import by.htp.cities.dao.DaoException;
import by.htp.cities.entity.City;
import by.htp.cities.dao.CitiesDao;

public class CitiesDaoImpl implements CitiesDao {

	private ArrayList<String> result = new ArrayList<>();
	ArrayList<City> cities = new ArrayList<>();

	@Override
	public ArrayList<City> readInfo(String path) throws DaoException {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));

			String line;

			while ((line = br.readLine()) != null) {
				result.add(line);
			}

			for (String string : result) {
				City city = new City();
				city.setName(string);
				cities.add(city);
			}

		} catch (FileNotFoundException e) {

			new DaoException("problem  with", e);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			new DaoException("problem  with", e);
		}

		return cities;

	}

}
