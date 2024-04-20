package org.example.service;

import org.example.reservations.AddTicketResponse;
import org.example.reservations.DeleteTicketResponse;
import org.example.reservations.GetTicketByIdResponse;
import org.example.reservations.GetTicketByPassengerNameResponse;
import org.example.reservations.GetTicketByStatusResponse;
import org.example.reservations.Ticket;
import org.example.reservations.UpdateTicketResponse;

public interface TicketService {
    
    AddTicketResponse addTicket(Ticket ticket);
    GetTicketByIdResponse getTicketById(Integer id);
    GetTicketByPassengerNameResponse getTicketByPassengerName(String passengerName);
    GetTicketByStatusResponse getTicketByStatus(String status);
    UpdateTicketResponse updateTicket(Integer id, Ticket ticket);
    DeleteTicketResponse deleteTicket(Integer id);
}