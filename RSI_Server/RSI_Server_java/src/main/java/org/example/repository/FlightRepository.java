package org.example.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import org.example.reservations.Flight;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class FlightRepository {
    
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");
    private final Map<Integer, Flight> flights = new HashMap<>();
    private int nextId = 1;
    
    @PostConstruct
    public void initData() {
        Flight flight1 = new Flight();
        flight1.setFromCity("Warsaw");
        flight1.setToCity("Berlin");
        flight1.setTime("12:00");
        flight1.setDate("23.05.2024");
        flights.put(nextId++, flight1);
        
        Flight flight2 = new Flight();
        flight2.setFromCity("Warsaw");
        flight2.setToCity("Berlin");
        flight2.setTime("8:00");
        flight2.setDate("24.05.2024");
        flights.put(nextId++, flight2);
        
        Flight flight3 = new Flight();
        flight3.setFromCity("Warsaw");
        flight3.setToCity("Berlin");
        flight3.setTime("16:30");
        flight3.setDate("25.05.2024");
        flights.put(nextId++, flight3);
    }
    
    public Flight addFlight(Flight flight) {
        flights.put(nextId++, flight);
        return flight;
    }
    
    public Flight getFlightById(Integer id) {
        Assert.notNull(id, "The id must not be null");
        return flights.get(id);
    }
    
    public List<Flight> getFlights() {
        return new ArrayList<>(flights.values());
    }
    
    public List<Flight> getFlightsByFromCity(String fromCity) {
        Assert.notNull(fromCity, "The fromCity must not be null");
        return flights.values()
                .stream()
                .filter(flight -> fromCity.equals(flight.getFromCity()))
                .collect(Collectors.toList());
    }
    
    public List<Flight> getFlightsByToCity(String toCity) {
        Assert.notNull(toCity, "The toCity must not be null");
        return flights.values()
                .stream()
                .filter(flight -> toCity.equals(flight.getToCity()))
                .collect(Collectors.toList());
    }
    
    public List<Flight> getFlightsByDate(String date) {
        Assert.notNull(date, "The date must not be null");
        return flights.values()
                .stream()
                .filter(flight -> date.equals(flight.getDate()))
                .collect(Collectors.toList());
    }
    
    public List<Flight> getFlightsByFromCityAndToCityAndDate(String fromCity, String toCity, String date) {
        Assert.notNull(fromCity, "The fromCity must not be null");
        Assert.notNull(toCity, "The toCity must not be null");
        Assert.notNull(date, "The date must not be null");
        return flights.values()
                .stream()
                .filter(flight -> fromCity.equals(flight.getFromCity()))
                .filter(flight -> toCity.equals(flight.getToCity()))
                .filter(flight -> parseDates(date, flight.getDate()))
                .collect(Collectors.toList());
    }
    
    private boolean parseDates(String date1, String date2) {
        try {
            return SDF.parse(date1).before(SDF.parse(date2));
        } catch (ParseException ex) {
            // something went wrong
            return false;
        }
    }
    
    public List<Flight> getFlightsByTime(String time) {
        Assert.notNull(time, "The time must not be null");
        return flights.values()
                .stream()
                .filter(flight -> time.equals(flight.getTime()))
                .collect(Collectors.toList());
    }
    
    public Flight updateFlight(Integer id, Flight flight) {
        if (flights.containsKey(id)) {
            flights.put(id, flight);
            return flight;
        }
        return null;
    }
    
    public Flight deleteFlight(Integer id) {
        if (flights.containsKey(id)) {
            return flights.remove(id);
        }
        return null;
    }
}
