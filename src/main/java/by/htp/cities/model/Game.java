package by.htp.cities.model;

import java.util.ArrayList;

public class Game {

    private ArrayList<City> cities;
    private ArrayList<City> usedCities = new ArrayList<City>();

    public Game(ArrayList<City> cities) {
	this.cities = cities;
    }

    public GameStatus checkGameStatus(Player player, Game game, ArrayList<City> cities,
	    PlayerOperationResponse responseFromPlayer, String cityFromPlayer, char firstLetterFromPlayer) {

	DataCorrectness respond = null;
	GameStatus status = null;

	if (responseFromPlayer.getOperationResult() == PlayerOperation.GIVE_UP) {
	    System.out.println(player.toString() + " gives up. The game is over. " + player.toString() + " lost.");
	    return status = GameStatus.GAME_OVER;
	}

	else {

	    respond = game.validation(responseFromPlayer.getCity(), cities, firstLetterFromPlayer);

	    switch (respond) {

	    case WRONG_FIRST_LETTER:
		status = GameStatus.START;
		break;
	    case EXISTS:
		status = GameStatus.START;
		break;
	    case INVALID:
		status = GameStatus.START;
		break;
	    case VALID:
		status = GameStatus.NEXT_TURN;
		break;

	    }
	}

	return status;
    }

    public DataCorrectness validation(String input, ArrayList<City> cities, char firstLetter) {

	boolean isValid = false;

	if (Character.toUpperCase(input.charAt(0)) != Character.toUpperCase(firstLetter)) {
	    System.out.println("You have entered a city name starting with a different letter that it was supposed to");
	    return DataCorrectness.WRONG_FIRST_LETTER;
	}

	if ("New York".equals(input)) {
	    System.out.println("Ha-Ha! New York is not the capital of the USA you dummy, Washington, D.C. is!");
	    return DataCorrectness.INVALID;

	}

	if (!usedCities.isEmpty()) {

	    for (City city : usedCities) {

		if (input.equalsIgnoreCase(city.getName())) {

		    System.out.println("This city has already been used, please choose another one");
		    return DataCorrectness.EXISTS;

		}

	    }

	}

	for (City city : cities) {

	    if (input.equalsIgnoreCase(city.getName())) {
		System.out.println("Yes, capital city " + city.getName() + " exists");
		usedCities.add(city);
		isValid = true;
		break;
	    }

	}

	if (!isValid) {
	    System.out.println("There is no such capital city. Have you misspelled it's name?");
	    return DataCorrectness.INVALID;
	}
	return DataCorrectness.VALID;
    }

    public ArrayList<City> getUsedCities() {
	return usedCities;
    }

    public void setUsedCities(ArrayList<City> usedCities) {
	this.usedCities = usedCities;
    }

    public ArrayList<City> getCities() {
	return cities;
    }

    public void setCities(ArrayList<City> cities) {
	this.cities = cities;
    }
}
