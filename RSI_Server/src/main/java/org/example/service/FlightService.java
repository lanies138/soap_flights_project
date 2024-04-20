package org.example.service;

import org.example.reservations.AddFlightResponse;
import org.example.reservations.DeleteFlightResponse;
import org.example.reservations.Flight;
import org.example.reservations.GetFlightByDateResponse;
import org.example.reservations.GetFlightByFromCityResponse;
import org.example.reservations.GetFlightByIdResponse;
import org.example.reservations.GetFlightByTimeResponse;
import org.example.reservations.GetFlightByToCityResponse;
import org.example.reservations.UpdateFlightResponse;

public interface FlightService {
    
    AddFlightResponse addFlight(Flight flight);
    GetFlightByIdResponse getFlightById(Integer id);
    GetFlightByFromCityResponse getFlightByFromCity(String fromCity);
    GetFlightByToCityResponse getFlightByToCity(String toCity);
    GetFlightByDateResponse getFlightByDate(String date);
    GetFlightByTimeResponse getFlightByTime(String time);
    UpdateFlightResponse updateFlight(Integer id, Flight flight);
    DeleteFlightResponse deleteFlight(Integer id);
}