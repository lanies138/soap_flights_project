package org.example.endpoint;

import org.example.flight.GetFlightByDateRequest;
import org.example.flight.GetFlightByDateResponse;
import org.example.flight.GetFlightByFromCityRequest;
import org.example.flight.GetFlightByFromCityResponse;
import org.example.flight.GetFlightByIdRequest;
import org.example.flight.GetFlightByIdResponse;
import org.example.flight.GetFlightByTimeRequest;
import org.example.flight.GetFlightByTimeResponse;
import org.example.flight.GetFlightByToCityRequest;
import org.example.flight.GetFlightByToCityResponse;
import org.example.service.FlightService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class FlightEndpoint {
    
    private static final String NAMESPACE_URI = "http://example.org/flight";
    
    private final FlightService flightService;
    
    public FlightEndpoint(FlightService flightService) {
        this.flightService = flightService;
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
}
