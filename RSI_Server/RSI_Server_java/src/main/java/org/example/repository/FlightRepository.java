package org.example.repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import org.example.reservations.Flight;
import org.example.utils.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class FlightRepository {
    
    private final List<Flight> flights = new ArrayList<>();
    private int nextId = 1;
    
    @PostConstruct
    public void initData() {
        Flight flight1 = new Flight();
        flight1.setId(nextId++);
        flight1.setFromCity("Warsaw");
        flight1.setToCity("Berlin");
        flight1.setTime("12:00");
        flight1.setDate("23.05.2024");
        flights.add(flight1);
        
        Flight flight2 = new Flight();
        flight2.setId(nextId++);
        flight2.setFromCity("Warsaw");
        flight2.setToCity("Berlin");
        flight2.setTime("8:00");
        flight2.setDate("24.05.2024");
        flights.add(flight2);
        
        Flight flight3 = new Flight();
        flight3.setId(nextId++);
        flight3.setFromCity("Warsaw");
        flight3.setToCity("Berlin");
        flight3.setTime("16:30");
        flight3.setDate("25.05.2024");
        flights.add(flight3);
    }
    
    public Flight addFlight(Flight flight) {
        flight.setId(nextId++);
        flights.add(flight);
        return flight;
    }
    
    public Flight getFlightById(Integer id) {
        Assert.notNull(id, "The id must not be null");
        return flights.stream()
                .filter(flight -> id.equals(flight.getId()))
                .findFirst()
                .orElse(null);
    }
    
    public List<Flight> getFlights() {
        return flights;
    }
    
    public List<Flight> getFlightsByFromCity(String fromCity) {
        Assert.notNull(fromCity, "The fromCity must not be null");
        return flights.stream()
                .filter(flight -> fromCity.equals(flight.getFromCity()))
                .collect(Collectors.toList());
    }
    
    public List<Flight> getFlightsByToCity(String toCity) {
        Assert.notNull(toCity, "The toCity must not be null");
        return flights.stream()
                .filter(flight -> toCity.equals(flight.getToCity()))
                .collect(Collectors.toList());
    }
    
    public List<Flight> getFlightsByDate(String date) {
        Assert.notNull(date, "The date must not be null");
        return flights.stream()
                .filter(flight -> date.equals(flight.getDate()))
                .collect(Collectors.toList());
    }
    
    public List<Flight> getFlightsByFromCityAndToCityAndDate(String fromCity, String toCity, String date) {
        Assert.notNull(fromCity, "The fromCity must not be null");
        Assert.notNull(toCity, "The toCity must not be null");
        Assert.notNull(date, "The date must not be null");
        return flights.stream()
                .filter(flight -> fromCity.equals(flight.getFromCity()))
                .filter(flight -> toCity.equals(flight.getToCity()))
                .filter(flight -> parseDates(date, flight.getDate()))
                .collect(Collectors.toList());
    }
    
    private boolean parseDates(String date1, String date2) {
        try {
            return DateUtils.SDF.parse(date1).before(DateUtils.SDF.parse(date2));
        } catch (ParseException ex) {
            // something went wrong
            return false;
        }
    }
    
    public List<Flight> getFlightsByTime(String time) {
        Assert.notNull(time, "The time must not be null");
        return flights.stream()
                .filter(flight -> time.equals(flight.getTime()))
                .collect(Collectors.toList());
    }
    
    public Flight updateFlight(Flight flight) {
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getId() == flight.getId()) {
                flights.set(i, flight);
                return flight;
            }
        }
        return null;
    }
    
    public Flight deleteFlight(Integer id) {
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getId() == id) {
                return flights.remove(i);
            }
        }
        return null;
    }
}
