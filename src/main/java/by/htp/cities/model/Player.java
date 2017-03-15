package by.htp.cities.model;

import by.htp.cities.entity.PlayerOperationResponse;
import by.htp.cities.exception.IllegalCityNameException;

public interface Player {

	public PlayerOperationResponse askForCity(char letter) throws IllegalCityNameException;

}
