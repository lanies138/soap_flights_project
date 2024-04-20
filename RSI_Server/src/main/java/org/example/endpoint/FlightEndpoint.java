package org.example.endpoint;

import org.example.reservations.AddFlightRequest;
import org.example.reservations.AddFlightResponse;
import org.example.reservations.DeleteFlightRequest;
import org.example.reservations.DeleteFlightResponse;
import org.example.reservations.GetFlightByDateRequest;
import org.example.reservations.GetFlightByDateResponse;
import org.example.reservations.GetFlightByFromCityRequest;
import org.example.reservations.GetFlightByFromCityResponse;
import org.example.reservations.GetFlightByIdRequest;
import org.example.reservations.GetFlightByIdResponse;
import org.example.reservations.GetFlightByTimeRequest;
import org.example.reservations.GetFlightByTimeResponse;
import org.example.reservations.GetFlightByToCityRequest;
import org.example.reservations.GetFlightByToCityResponse;
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
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFlightByFromCityRequest")
    @ResponsePayload
    public GetFlightByFromCityResponse getFlightByFromCity(@RequestPayload GetFlightByFromCityRequest request) {
        return flightService.getFlightByFromCity(request.getFromCity());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFlightByToCityRequest")
    @ResponsePayload
    public GetFlightByToCityResponse getFlightByToCity(@RequestPayload GetFlightByToCityRequest request) {
        return flightService.getFlightByToCity(request.getToCity());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFlightByDateRequest")
    @ResponsePayload
    public GetFlightByDateResponse getFlightByDate(@RequestPayload GetFlightByDateRequest request) {
        return flightService.getFlightByDate(request.getDate());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFlightByTimeRequest")
    @ResponsePayload
    public GetFlightByTimeResponse getFlightByTime(@RequestPayload GetFlightByTimeRequest request) {
        return flightService.getFlightByTime(request.getTime());
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
