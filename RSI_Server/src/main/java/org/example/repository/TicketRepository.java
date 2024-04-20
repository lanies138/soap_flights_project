package org.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

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
        ticket1.setPassengerName("Andrew Tate");
        ticket1.setPrice(54);
        ticket1.setStatus("Waiting for check-in");
        tickets.put(nextId++, ticket1);
        
        Ticket ticket2 = new Ticket();
        ticket2.setFlight(flightRepository.getFlightById(2));
        ticket2.setPassengerName("Elon Musk");
        ticket2.setPrice(64);
        ticket2.setStatus("Booked");
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
