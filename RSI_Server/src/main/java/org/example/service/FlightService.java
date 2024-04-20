package org.example.service;

import org.example.flight.GetFlightByDateResponse;
import org.example.flight.GetFlightByFromCityResponse;
import org.example.flight.GetFlightByIdResponse;
import org.example.flight.GetFlightByTimeResponse;
import org.example.flight.GetFlightByToCityResponse;

public interface FlightService {
    
    GetFlightByIdResponse getFlightById(Integer id);
    GetFlightByFromCityResponse getFlightByFromCity(String fromCity);
    GetFlightByToCityResponse getFlightByToCity(String toCity);
    GetFlightByDateResponse getFlightByDate(String date);
    GetFlightByTimeResponse getFlightByTime(String time);
}