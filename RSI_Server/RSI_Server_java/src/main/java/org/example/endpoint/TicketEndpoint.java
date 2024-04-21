package org.example.endpoint;

import org.example.reservations.AddTicketRequest;
import org.example.reservations.AddTicketResponse;
import org.example.reservations.DeleteTicketRequest;
import org.example.reservations.DeleteTicketResponse;
import org.example.reservations.GetTicketByFlightRequest;
import org.example.reservations.GetTicketByFlightResponse;
import org.example.reservations.GetTicketByIdRequest;
import org.example.reservations.GetTicketByIdResponse;
import org.example.reservations.GetTicketsByPassengerNameRequest;
import org.example.reservations.GetTicketsByPassengerNameResponse;
import org.example.reservations.GetTicketsByStatusRequest;
import org.example.reservations.GetTicketsByStatusResponse;
import org.example.reservations.GetTicketsResponse;
import org.example.reservations.UpdateTicketRequest;
import org.example.reservations.UpdateTicketResponse;
import org.example.service.TicketService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class TicketEndpoint {
    
    private static final String NAMESPACE_URI = "http://example.org/reservations";
    
    private final TicketService ticketService;
    
    public TicketEndpoint(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddTicketRequest")
    @ResponsePayload
    public AddTicketResponse addTicket(@RequestPayload AddTicketRequest request) {
        return ticketService.addTicket(request.getTicket());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetTicketByIdRequest")
    @ResponsePayload
    public GetTicketByIdResponse getTicketById(@RequestPayload GetTicketByIdRequest request) {
        return ticketService.getTicketById(request.getId());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetTicketsRequest")
    @ResponsePayload
    public GetTicketsResponse getTickets() {
        return ticketService.getTickets();
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetTicketByFlightRequest")
    @ResponsePayload
    public GetTicketByFlightResponse getTicketByFlight(@RequestPayload GetTicketByFlightRequest request) {
        return ticketService.getTicketByFlight(request.getFlight());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetTicketsByPassengerNameRequest")
    @ResponsePayload
    public GetTicketsByPassengerNameResponse getTicketsByPassengerName(@RequestPayload GetTicketsByPassengerNameRequest request) {
        return ticketService.getTicketsByPassengerName(request.getPassengerName());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetTicketsByStatusRequest")
    @ResponsePayload
    public GetTicketsByStatusResponse getTicketsByStatus(@RequestPayload GetTicketsByStatusRequest request) {
        return ticketService.getTicketsByStatus(request.getStatus());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateTicketRequest")
    @ResponsePayload
    public UpdateTicketResponse updateTicket(@RequestPayload UpdateTicketRequest request) {
        return ticketService.updateTicket(request.getId(), request.getTicket());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteTicketRequest")
    @ResponsePayload
    public DeleteTicketResponse deleteTicket(@RequestPayload DeleteTicketRequest request) {
        return ticketService.deleteTicket(request.getId());
    }
}
