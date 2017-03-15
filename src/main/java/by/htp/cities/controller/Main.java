package by.htp.cities.controller;

import java.util.ArrayList;
import by.htp.cities.dao.DaoException;
import by.htp.cities.dao.CitiesDao;
import by.htp.cities.dao.impl.CitiesDaoImpl;
import by.htp.cities.entity.City;
import by.htp.cities.entity.ComputerPlayer;
import by.htp.cities.entity.ConsolePlayer;
import by.htp.cities.entity.Game;
import by.htp.cities.entity.PlayerOperationResponse;
import by.htp.cities.exception.IllegalCityNameException;
import by.htp.cities.model.Player;
import by.htp.cities.model.GameStatus;

public class Main {

    public static void main(String[] args) {

	ArrayList<City> cities = null;
	CitiesDao dao = new CitiesDaoImpl();
	try {
	    cities = dao.readInfo("src/main/resources/allCapitals.txt");
	} catch (DaoException e) {
	    new DaoException("Cannot read from a file:", e);
	}

	Game newGame = new Game(cities);

	Player player1 = new ComputerPlayer();
	String cityFromPlayer1 = ((ComputerPlayer) player1).startCity(cities).getName();
	char firstLetterFromPlayer1 = cityFromPlayer1.charAt(cityFromPlayer1.length() - 1);

	Player player2 = new ConsolePlayer();
	String cityFromPlayer2 = new String();
	char firstLetterFromPlayer2 = 0;

	outer: while (true) {

	    PlayerOperationResponse responseFromPlayer2 = null;
	    try {
		responseFromPlayer2 = player2.askForCity(firstLetterFromPlayer1);
	    } catch (IllegalCityNameException e) {
		new IllegalCityNameException(e);
	    }

	    GameStatus status = newGame.checkGameStatus(player2, newGame, cities, responseFromPlayer2, cityFromPlayer1,
		    firstLetterFromPlayer1);
	    if (status == GameStatus.GAME_OVER) {
		return;
	    } else if (status == GameStatus.START) {
		continue outer;
	    } else if (status == GameStatus.NEXT_TURN)

		((ComputerPlayer) player1).setCities(cities);
	    ((ComputerPlayer) player1).setUsedCities(newGame.getUsedCities());

	    firstLetterFromPlayer2 = responseFromPlayer2.getCity().charAt(responseFromPlayer2.getCity().length() - 1);

	    PlayerOperationResponse responseFromPlayer1 = null;
	    try {
		responseFromPlayer1 = player1.askForCity(firstLetterFromPlayer2);
	    } catch (IllegalCityNameException e) {
		new IllegalCityNameException(e);
	    }

	    status = newGame.checkGameStatus(player1, newGame, cities, responseFromPlayer1, cityFromPlayer2,
		    firstLetterFromPlayer2);
	    if (status == GameStatus.GAME_OVER) {
		return;
	    } else if (status == GameStatus.START || status == GameStatus.NEXT_TURN) {
		firstLetterFromPlayer1 = responseFromPlayer1.getCity()
			.charAt(responseFromPlayer1.getCity().length() - 1);
		continue outer;
	    }

	}
    }

}
