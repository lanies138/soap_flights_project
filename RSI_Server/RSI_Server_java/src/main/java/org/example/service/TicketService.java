package org.example.service;

import org.example.reservations.AddTicketResponse;
import org.example.reservations.DeleteTicketResponse;
import org.example.reservations.Flight;
import org.example.reservations.GetTicketByIdResponse;
import org.example.reservations.GetTicketByFlightResponse;
import org.example.reservations.GetTicketsByPassengerNameResponse;
import org.example.reservations.GetTicketsByStatusResponse;
import org.example.reservations.GetTicketsResponse;
import org.example.reservations.Ticket;
import org.example.reservations.UpdateTicketResponse;

public interface TicketService {
    
    AddTicketResponse addTicket(Ticket ticket);
    GetTicketByIdResponse getTicketById(Integer id);
    GetTicketsResponse getTickets();
    GetTicketByFlightResponse getTicketByFlight(Flight flight);
    GetTicketsByPassengerNameResponse getTicketsByPassengerName(String passengerName);
    GetTicketsByStatusResponse getTicketsByStatus(String status);
    UpdateTicketResponse updateTicket(Ticket ticket);
    DeleteTicketResponse deleteTicket(Integer id);
}