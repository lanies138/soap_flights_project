package org.example.endpoint;

import org.example.reservations.AddFlightRequest;
import org.example.reservations.AddFlightResponse;
import org.example.reservations.DeleteFlightRequest;
import org.example.reservations.DeleteFlightResponse;
import org.example.reservations.GetFlightByIdRequest;
import org.example.reservations.GetFlightByIdResponse;
import org.example.reservations.GetFlightsByDateRequest;
import org.example.reservations.GetFlightsByDateResponse;
import org.example.reservations.GetFlightsByFromCityRequest;
import org.example.reservations.GetFlightsByFromCityResponse;
import org.example.reservations.GetFlightsByTimeRequest;
import org.example.reservations.GetFlightsByTimeResponse;
import org.example.reservations.GetFlightsByToCityRequest;
import org.example.reservations.GetFlightsByToCityResponse;
import org.example.reservations.GetFlightsResponse;
import org.example.reservations.UpdateFlightRequest;
import org.example.reservations.UpdateFlightResponse;
import org.example.service.FlightService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class FlightEndpoint {
    
    private static final String NAMESPACE_URI = "http://example.org/reservations";
    
    private final FlightService flightService;
    
    public FlightEndpoint(FlightService flightService) {
        this.flightService = flightService;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddFlightRequest")
    @ResponsePayload
    public AddFlightResponse addFlight(@RequestPayload AddFlightRequest request) {
        return flightService.addFlight(request.getFlight());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFlightByIdRequest")
    @ResponsePayload
    public GetFlightByIdResponse getFlightById(@RequestPayload GetFlightByIdRequest request) {
        return flightService.getFlightById(request.getId());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFlightsRequest")
    @ResponsePayload
    public GetFlightsResponse getFlights() {
        return flightService.getFlights();
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFlightsByFromCityRequest")
    @ResponsePayload
    public GetFlightsByFromCityResponse getFlightsByFromCity(@RequestPayload GetFlightsByFromCityRequest request) {
        return flightService.getFlightsByFromCity(request.getFromCity());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFlightsByToCityRequest")
    @ResponsePayload
    public GetFlightsByToCityResponse getFlightByToCity(@RequestPayload GetFlightsByToCityRequest request) {
        return flightService.getFlightsByToCity(request.getToCity());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFlightsByDateRequest")
    @ResponsePayload
    public GetFlightsByDateResponse getFlightByDate(@RequestPayload GetFlightsByDateRequest request) {
        return flightService.getFlightsByDate(request.getDate());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFlightsByTimeRequest")
    @ResponsePayload
    public GetFlightsByTimeResponse getFlightByTime(@RequestPayload GetFlightsByTimeRequest request) {
        return flightService.getFlightsByTime(request.getTime());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateFlightRequest")
    @ResponsePayload
    public UpdateFlightResponse updateFlight(@RequestPayload UpdateFlightRequest request) {
        return flightService.updateFlight(request.getId(), request.getFlight());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteFlightRequest")
    @ResponsePayload
    public DeleteFlightResponse deleteFlight(@RequestPayload DeleteFlightRequest request) {
        return flightService.deleteFlight(request.getId());
    }
}
