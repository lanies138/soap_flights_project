package org.example.repository;

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
    
    private final Map<Integer, Flight> flights = new HashMap<>();
    private int nextId = 1;
    
    @PostConstruct
    public void initData() {
        Flight flight1 = new Flight();
        flight1.setFromCity("Mońki");
        flight1.setToCity("Chicago");
        flight1.setTime("10:00");
        flight1.setDate("23.04.2024");
        flights.put(nextId++, flight1);
        
        Flight flight2 = new Flight();
        flight2.setFromCity("Warsaw");
        flight2.setToCity("Berlin");
        flight2.setTime("10:00");
        flight2.setDate("23.04.2024");
        flights.put(nextId++, flight2);
    }
    
    public Flight addFlight(Flight flight) {
        flights.put(nextId++, flight);
        return flight;
    }
    
    public Flight getFlightById(Integer id) {
        Assert.notNull(id, "The id must not be null");
        return flights.get(id);
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
