package org.example.service;

import org.example.reservations.AddFlightResponse;
import org.example.reservations.DeleteFlightResponse;
import org.example.reservations.Flight;
import org.example.reservations.GetFlightsByDateResponse;
import org.example.reservations.GetFlightsByFromCityResponse;
import org.example.reservations.GetFlightByIdResponse;
import org.example.reservations.GetFlightsByTimeResponse;
import org.example.reservations.GetFlightsByToCityResponse;
import org.example.reservations.GetFlightsResponse;
import org.example.reservations.UpdateFlightResponse;

public interface FlightService {
    
    AddFlightResponse addFlight(Flight flight);
    GetFlightByIdResponse getFlightById(Integer id);
    GetFlightsResponse getFlights();
    GetFlightsByFromCityResponse getFlightsByFromCity(String fromCity);
    GetFlightsByToCityResponse getFlightsByToCity(String toCity);
    GetFlightsByDateResponse getFlightsByDate(String date);
    GetFlightsByTimeResponse getFlightsByTime(String time);
    UpdateFlightResponse updateFlight(Integer id, Flight flight);
    DeleteFlightResponse deleteFlight(Integer id);
}