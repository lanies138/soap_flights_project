package org.example.service.impl;

import org.example.repository.FlightRepository;
import org.example.reservations.AddFlightResponse;
import org.example.reservations.DeleteFlightResponse;
import org.example.reservations.Flight;
import org.example.reservations.GetFlightByDateResponse;
import org.example.reservations.GetFlightByFromCityResponse;
import org.example.reservations.GetFlightByIdResponse;
import org.example.reservations.GetFlightByTimeResponse;
import org.example.reservations.GetFlightByToCityResponse;
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