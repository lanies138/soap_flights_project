package org.example.service.impl;

import org.example.flight.GetFlightByDateResponse;
import org.example.flight.GetFlightByFromCityResponse;
import org.example.flight.GetFlightByIdResponse;
import org.example.flight.GetFlightByTimeResponse;
import org.example.flight.GetFlightByToCityResponse;
import org.example.repository.FlightRepository;
import org.example.service.FlightService;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {
    
    private FlightRepository flightRepository;
    
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
    
    @Override
    public GetFlightByIdResponse getFlightById(Integer id) {
        GetFlightByIdResponse response = new GetFlightByIdResponse();
        response.setFlight(flightRepository.getFlightById(id));
        return response;
    }
    
    @Override
    public GetFlightByFromCityResponse getFlightByFromCity(String fromCity) {
        GetFlightByFromCityResponse response = new GetFlightByFromCityResponse();
        response.getFlights().addAll(flightRepository.getFlightByFromCity(fromCity));
        return response;
    }
    
    @Override
    public GetFlightByToCityResponse getFlightByToCity(String toCity) {
        GetFlightByToCityResponse response = new GetFlightByToCityResponse();
        response.getFlights().addAll(flightRepository.getFlightByToCity(toCity));
        return response;
    }
    
    @Override
    public GetFlightByDateResponse getFlightByDate(String date) {
        GetFlightByDateResponse response = new GetFlightByDateResponse();
        response.getFlights().addAll(flightRepository.getFlightByDate(date));
        return response;
    }
    
    @Override
    public GetFlightByTimeResponse getFlightByTime(String time) {
        GetFlightByTimeResponse response = new GetFlightByTimeResponse();
        response.getFlights().addAll(flightRepository.getFlightByTime(time));
        return response;
    }
}