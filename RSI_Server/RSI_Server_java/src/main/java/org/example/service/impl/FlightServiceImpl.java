package org.example.service.impl;

import org.example.repository.FlightRepository;
import org.example.reservations.AddFlightResponse;
import org.example.reservations.DeleteFlightResponse;
import org.example.reservations.Flight;
import org.example.reservations.GetFlightsByDateResponse;
import org.example.reservations.GetFlightsByFromCityAndToCityAndDateResponse;
import org.example.reservations.GetFlightsByFromCityResponse;
import org.example.reservations.GetFlightByIdResponse;
import org.example.reservations.GetFlightsByTimeResponse;
import org.example.reservations.GetFlightsByToCityResponse;
import org.example.reservations.GetFlightsResponse;
import org.example.reservations.UpdateFlightResponse;
import org.example.service.FlightService;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {
    
    private FlightRepository flightRepository;
    
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
    
    @Override
    public AddFlightResponse addFlight(Flight flight) {
        AddFlightResponse response = new AddFlightResponse();
        response.setFlight(flightRepository.addFlight(flight));
        return response;
    }
    
    @Override
    public GetFlightByIdResponse getFlightById(Integer id) {
        GetFlightByIdResponse response = new GetFlightByIdResponse();
        response.setFlight(flightRepository.getFlightById(id));
        return response;
    }
    
    @Override
    public GetFlightsResponse getFlights() {
        GetFlightsResponse response = new GetFlightsResponse();
        response.getFlights().addAll(flightRepository.getFlights());
        return response;
    }
    
    @Override
    public GetFlightsByFromCityResponse getFlightsByFromCity(String fromCity) {
        GetFlightsByFromCityResponse response = new GetFlightsByFromCityResponse();
        response.getFlights().addAll(flightRepository.getFlightsByFromCity(fromCity));
        return response;
    }
    
    @Override
    public GetFlightsByToCityResponse getFlightsByToCity(String toCity) {
        GetFlightsByToCityResponse response = new GetFlightsByToCityResponse();
        response.getFlights().addAll(flightRepository.getFlightsByToCity(toCity));
        return response;
    }
    
    @Override
    public GetFlightsByDateResponse getFlightsByDate(String date) {
        GetFlightsByDateResponse response = new GetFlightsByDateResponse();
        response.getFlights().addAll(flightRepository.getFlightsByDate(date));
        return response;
    }
    
    @Override
    public GetFlightsByTimeResponse getFlightsByTime(String time) {
        GetFlightsByTimeResponse response = new GetFlightsByTimeResponse();
        response.getFlights().addAll(flightRepository.getFlightsByTime(time));
        return response;
    }
    
    @Override
    public GetFlightsByFromCityAndToCityAndDateResponse getFlightsByFromCityAndToCityAndDate(String fromCity, String toCity, String date) {
        GetFlightsByFromCityAndToCityAndDateResponse response = new GetFlightsByFromCityAndToCityAndDateResponse();
        response.getFlights().addAll(flightRepository.getFlightsByFromCityAndToCityAndDate(fromCity, toCity, date));
        return response;
    }
    
    @Override
    public UpdateFlightResponse updateFlight(Integer id, Flight flight) {
        UpdateFlightResponse response = new UpdateFlightResponse();
        response.setFlight(flightRepository.updateFlight(id, flight));
        return response;
    }
    
    @Override
    public DeleteFlightResponse deleteFlight(Integer id) {
        DeleteFlightResponse response = new DeleteFlightResponse();
        response.setFlight(flightRepository.deleteFlight(id));
        return response;
    }
}