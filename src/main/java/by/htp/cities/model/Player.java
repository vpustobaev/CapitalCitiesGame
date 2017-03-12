package by.htp.cities.model;

import by.htp.cities.exception.IllegalCityNameException;

public interface Player {

	public PlayerOperationResponse askForCity(char letter) throws IllegalCityNameException;

}
