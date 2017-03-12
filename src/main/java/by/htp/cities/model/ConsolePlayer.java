package by.htp.cities.model;

import java.util.Scanner;

import by.htp.cities.exception.IllegalCityNameException;

public class ConsolePlayer implements Player {

    @Override
    public PlayerOperationResponse askForCity(char letter) throws IllegalCityNameException {
	System.out.println("Please input a city, that starts with the letter " + Character.toUpperCase(letter)
		+ ". If you don't know such city, input '-exit'");
	Scanner el = new Scanner(System.in);
	String city = el.nextLine();

	Player user = new ComputerPlayer();
	PlayerOperationResponse response = new PlayerOperationResponse();

	if ("-exit".equalsIgnoreCase(city))

	{
	    response.setOperationResult(PlayerOperation.GIVE_UP);

	} else if (city.length() == 0)

	{
	    throw new IllegalCityNameException("You haven't entered a thing!");

	} else {
	    response.setCity(city);
	    response.setOperationResult(PlayerOperation.SAY_CITY);
	}

	return response;

    }

    @Override
    public String toString() {
	return "Console Player";
    }

}
