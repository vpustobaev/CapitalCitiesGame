package by.htp.cities.dao;

import java.util.ArrayList;

import by.htp.cities.entity.City;

public interface CitiesDao {

	public ArrayList<City> readInfo(String path) throws DaoException;

}