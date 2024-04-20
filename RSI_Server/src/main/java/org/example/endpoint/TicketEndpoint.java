package org.example.endpoint;

import org.example.reservations.AddTicketRequest;
import org.example.reservations.AddTicketResponse;
import org.example.reservations.DeleteTicketRequest;
import org.example.reservations.DeleteTicketResponse;
import org.example.reservations.GetTicketByIdRequest;
import org.example.reservations.GetTicketByIdResponse;
import org.example.reservations.GetTicketByPassengerNameRequest;
import org.example.reservations.GetTicketByPassengerNameResponse;
import org.example.reservations.GetTicketByStatusRequest;
import org.example.reservations.GetTicketByStatusResponse;
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
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetTicketByPassengerNameRequest")
    @ResponsePayload
    public GetTicketByPassengerNameResponse getTicketByPassengerName(@RequestPayload GetTicketByPassengerNameRequest request) {
        return ticketService.getTicketByPassengerName(request.getPassengerName());
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetTicketByStatusRequest")
    @ResponsePayload
    public GetTicketByStatusResponse getTicketByStatus(@RequestPayload GetTicketByStatusRequest request) {
        return ticketService.getTicketByStatus(request.getStatus());
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
