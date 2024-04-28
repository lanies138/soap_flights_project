package org.example.service.impl;

import java.io.File;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.example.repository.TicketRepository;
import org.example.reservations.AddTicketResponse;
import org.example.reservations.DeleteTicketResponse;
import org.example.reservations.Flight;
import org.example.reservations.GenerateTicketPDFResponse;
import org.example.reservations.GetTicketByFlightResponse;
import org.example.reservations.GetTicketByIdResponse;
import org.example.reservations.GetTicketsByPassengerNameResponse;
import org.example.reservations.GetTicketsByStatusResponse;
import org.example.reservations.GetTicketsResponse;
import org.example.reservations.Ticket;
import org.example.reservations.UpdateTicketResponse;
import org.example.service.PDFCreator;
import org.example.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    
    private final TicketRepository ticketRepository;
    private final PDFCreator pdfCreator;
    
    public TicketServiceImpl(TicketRepository ticketRepository, PDFCreator pdfCreator) {
        this.ticketRepository = ticketRepository;
        this.pdfCreator = pdfCreator;
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
    public GetTicketsResponse getTickets() {
        GetTicketsResponse response = new GetTicketsResponse();
        response.getTickets().addAll(ticketRepository.getTickets());
        return response;
    }
    
    @Override
    public GetTicketByFlightResponse getTicketByFlight(Flight flight) {
        GetTicketByFlightResponse response = new GetTicketByFlightResponse();
        response.setTicket(ticketRepository.getTicketByFlight(flight));
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
    public UpdateTicketResponse updateTicket(Ticket ticket) {
        UpdateTicketResponse response = new UpdateTicketResponse();
        response.setTicket(ticketRepository.updateTicket(ticket));
        return response;
    }
    
    @Override
    public DeleteTicketResponse deleteTicket(Integer id) {
        DeleteTicketResponse response = new DeleteTicketResponse();
        response.setTicket(ticketRepository.deleteTicket(id));
        return response;
    }
    
    @Override
    public GenerateTicketPDFResponse generateTicketPDF(Integer id) {
        File pdf = pdfCreator.create(ticketRepository.getTicketById(id));
        
        GenerateTicketPDFResponse response = new GenerateTicketPDFResponse();
        FileDataSource fileDataSource = new FileDataSource(pdf);
        DataHandler dataHandler = new DataHandler(fileDataSource);
        response.setContent(dataHandler);
        return response;
    }
    
}