package by.htp.cities.model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer implements Player {

    private ArrayList<City> cities;
    private ArrayList<City> usedCities = new ArrayList<>();

    @Override
    public PlayerOperationResponse askForCity(char letter) {
	PlayerOperationResponse response = new PlayerOperationResponse();
	System.out.println("Now the Computer plays, starting with letter " + Character.toUpperCase(letter));

	cities = this.getCities();
	usedCities = this.getUsedCities();
	boolean noMoreCities = false;

	City cityFromComputerPlayer = new City();

	for (int i = 0; i < cities.size(); i++) {

	    if (cities.get(i).getName().startsWith(String.valueOf(letter).toUpperCase())
		    && !usedCities.contains(cities.get(i))) {

		cityFromComputerPlayer = cities.get(i);
		System.out.println("The computer names you a city - " + cities.get(i).getName());
		noMoreCities = false;
		break;
	    } else {

		noMoreCities = true;
	    }

	}

	if (noMoreCities) {
	    response.setOperationResult(PlayerOperation.GIVE_UP);
	}

	else {
	    response.setCity(cityFromComputerPlayer.getName());
	    response.setOperationResult(PlayerOperation.SAY_CITY);
	}

	return response;

    }

    @Override
    public String toString() {
	return "Computer Player";
    }

    public City startCity(ArrayList<City> cities) {

	int randomIndex = ThreadLocalRandom.current().nextInt(0, cities.size());

	City startCity = cities.get(randomIndex);

	usedCities.add(startCity);

	System.out.println("Computer Player names you a city - " + startCity.getName());
	return startCity;

    }

    public ArrayList<City> getCities() {
	return cities;
    }

    public void setCities(ArrayList<City> cities) {
	this.cities = cities;
    }

    public ArrayList<City> getUsedCities() {
	return usedCities;
    }

    public void setUsedCities(ArrayList<City> usedCities) {
	this.usedCities = usedCities;
    }

}
