package by.htp.cities.model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import by.htp.cities.dao.CitiesDao;
import by.htp.cities.dao.DaoException;
import by.htp.cities.dao.impl.CitiesDaoImpl;

public class ComputerPlayerTest {

    private Player computerPlayer = new ComputerPlayer();
    private City city1;
    private City city2;
    private City city3;
    private ArrayList<City> cities = new ArrayList<>();

    @Before
    public void getCityDBFromFile() {
	CitiesDao dao = new CitiesDaoImpl();
	try {
	    cities = dao.readInfo("src/main/resources/allCapitals.txt");
	} catch (DaoException e) {
	    new DaoException("Cannot read from a file:", e);
	}

    }

    @Test
    public void testRandomCityIsInDB() {
	city1 = ((ComputerPlayer) computerPlayer).startCity(cities);
	assertTrue(cities.contains(city1));
    }

    @Test
    public void testRandomCitiesAreNotEqual() {
	city1 = ((ComputerPlayer) computerPlayer).startCity(cities);
	city2 = ((ComputerPlayer) computerPlayer).startCity(cities);
	city3 = ((ComputerPlayer) computerPlayer).startCity(cities);
	assertFalse(city1.equals(city2) && city2.equals(city3));
    }

    @Test
    public void testAskForCitiesResponse() {

    }

}