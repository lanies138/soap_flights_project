package org.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import org.example.reservations.Flight;
import org.example.reservations.Ticket;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class TicketRepository {
    
    private final Map<Integer, Ticket> tickets = new HashMap<>();
    private int nextId = 1;
    private final FlightRepository flightRepository;
    
    public TicketRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
    
    @PostConstruct
    public void initData() {
        Ticket ticket1 = new Ticket();
        ticket1.setFlight(flightRepository.getFlightById(1));
        ticket1.setPrice(59);
        ticket1.setStatus("NOT BOOKED");
        tickets.put(nextId++, ticket1);
        
        Ticket ticket2 = new Ticket();
        ticket2.setFlight(flightRepository.getFlightById(1));
        ticket2.setPrice(49);
        ticket1.setStatus("NOT BOOKED");
        tickets.put(nextId++, ticket2);
        
        Ticket ticket3 = new Ticket();
        ticket3.setFlight(flightRepository.getFlightById(1));
        ticket1.setStatus("NOT BOOKED");
        tickets.put(nextId++, ticket2);
    }
    
    public Ticket addTicket(Ticket ticket) {
        tickets.put(nextId++, ticket);
        return ticket;
    }
    
    public Ticket getTicketById(Integer id) {
        Assert.notNull(id, "The id must not be null");
        return tickets.get(id);
    }
    
    public List<Ticket> getTickets() {
        return new ArrayList<>(tickets.values());
    }
    
    public List<Ticket> getTicketsByFlight(Flight flight) {
        Assert.notNull(flight, "The flight must not be null");
        return tickets.values()
                .stream()
                .filter(ticket -> flight.getFromCity().equals(ticket.getFlight().getFromCity())
                       && flight.getToCity().equals(ticket.getFlight().getToCity())
                       && flight.getDate().equals(ticket.getFlight().getDate())
                       && flight.getTime().equals(ticket.getFlight().getTime()))
                .collect(Collectors.toList());
    }
    
    public List<Ticket> getTicketsByPassengerName(String passengerName) {
        Assert.notNull(passengerName, "The passengerName must not be null");
        return tickets.values()
                .stream()
                .filter(ticket -> passengerName.equals(ticket.getPassengerName()))
                .collect(Collectors.toList());
    }
    
    public List<Ticket> getTicketsByStatus(String status) {
        Assert.notNull(status, "The status must not be null");
        return tickets.values()
                .stream()
                .filter(ticket -> status.equals(ticket.getStatus()))
                .collect(Collectors.toList());
    }
    
    public Ticket updateTicket(Integer id, Ticket ticket) {
        if (tickets.containsKey(id)) {
            tickets.put(id, ticket);
            return ticket;
        }
        return null;
    }
    
    public Ticket deleteTicket(Integer id) {
        if (tickets.containsKey(id)) {
            return tickets.remove(id);
        }
        return null;
    }
}
