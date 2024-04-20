package org.example.service.impl;

import org.example.repository.TicketRepository;
import org.example.reservations.AddTicketResponse;
import org.example.reservations.DeleteTicketResponse;
import org.example.reservations.GetTicketByIdResponse;
import org.example.reservations.GetTicketsByPassengerNameResponse;
import org.example.reservations.GetTicketsByStatusResponse;
import org.example.reservations.Ticket;
import org.example.reservations.UpdateTicketResponse;
import org.example.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    
    private TicketRepository ticketRepository;
    
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    
    
    @Override
    public AddTicketResponse addTicket(Ticket ticket) {
        AddTicketResponse response = new AddTicketResponse();
        response.setTicket(ticketRepository.addTicket(ticket));
        return response;
    }
    
    @Override
    public GetTicketByIdResponse getTicketById(Integer id) {
        GetTicketByIdResponse response = new GetTicketByIdResponse();
        response.setTicket(ticketRepository.getTicketById(id));
        return response;
    }
    
    @Override
    public GetTicketsByPassengerNameResponse getTicketsByPassengerName(String passengerName) {
        GetTicketsByPassengerNameResponse response = new GetTicketsByPassengerNameResponse();
        response.getTickets().addAll(ticketRepository.getTicketsByPassengerName(passengerName));
        return response;
    }
    
    @Override
    public GetTicketsByStatusResponse getTicketsByStatus(String status) {
        GetTicketsByStatusResponse response = new GetTicketsByStatusResponse();
        response.getTickets().addAll(ticketRepository.getTicketsByStatus(status));
        return response;
    }
    
    @Override
    public UpdateTicketResponse updateTicket(Integer id, Ticket ticket) {
        UpdateTicketResponse response = new UpdateTicketResponse();
        response.setTicket(ticketRepository.updateTicket(id, ticket));
        return response;
    }
    
    @Override
    public DeleteTicketResponse deleteTicket(Integer id) {
        DeleteTicketResponse response = new DeleteTicketResponse();
        response.setTicket(ticketRepository.deleteTicket(id));
        return response;
    }
    
}