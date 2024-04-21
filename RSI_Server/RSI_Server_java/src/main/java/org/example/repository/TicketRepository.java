package org.example.repository;

import java.util.ArrayList;
import java.util.Comparator;
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
        // Tickets for flight1
        Ticket firstTicketForFlight1 = new Ticket();
        firstTicketForFlight1.setFlight(flightRepository.getFlightById(1));
        firstTicketForFlight1.setPrice(59);
        firstTicketForFlight1.setStatus("NOT BOOKED");
        tickets.put(nextId++, firstTicketForFlight1);
        
        Ticket secondTicketForFlight1 = new Ticket();
        secondTicketForFlight1.setFlight(flightRepository.getFlightById(1));
        secondTicketForFlight1.setPrice(49);
        secondTicketForFlight1.setStatus("NOT BOOKED");
        tickets.put(nextId++, secondTicketForFlight1);
        
        Ticket thirdTicketForFlight1 = new Ticket();
        thirdTicketForFlight1.setFlight(flightRepository.getFlightById(1));
        secondTicketForFlight1.setPrice(69);
        thirdTicketForFlight1.setStatus("NOT BOOKED");
        tickets.put(nextId++, secondTicketForFlight1);
        
        // Tickets for flight2
        Ticket firstTicketForFlight2 = new Ticket();
        firstTicketForFlight2.setFlight(flightRepository.getFlightById(2));
        firstTicketForFlight2.setPrice(169);
        firstTicketForFlight2.setStatus("NOT BOOKED");
        tickets.put(nextId++, firstTicketForFlight2);
        
        Ticket secondTicketForFlight2 = new Ticket();
        secondTicketForFlight2.setFlight(flightRepository.getFlightById(2));
        secondTicketForFlight2.setPrice(129);
        secondTicketForFlight2.setStatus("NOT BOOKED");
        tickets.put(nextId++, secondTicketForFlight2);
        
        Ticket thirdTicketForFlight2 = new Ticket();
        thirdTicketForFlight2.setFlight(flightRepository.getFlightById(2));
        secondTicketForFlight1.setPrice(139);
        thirdTicketForFlight2.setStatus("NOT BOOKED");
        tickets.put(nextId++, secondTicketForFlight2);
        
        // Tickets for flight3
        Ticket firstTicketForFlight3 = new Ticket();
        firstTicketForFlight3.setFlight(flightRepository.getFlightById(3));
        firstTicketForFlight3.setPrice(219);
        firstTicketForFlight3.setStatus("NOT BOOKED");
        tickets.put(nextId++, firstTicketForFlight3);
        
        Ticket secondTicketForFlight3 = new Ticket();
        secondTicketForFlight3.setFlight(flightRepository.getFlightById(3));
        secondTicketForFlight3.setPrice(229);
        secondTicketForFlight3.setStatus("NOT BOOKED");
        tickets.put(nextId++, secondTicketForFlight3);
        
        Ticket thirdTicketForFlight3 = new Ticket();
        thirdTicketForFlight3.setFlight(flightRepository.getFlightById(3));
        secondTicketForFlight3.setPrice(239);
        thirdTicketForFlight3.setStatus("NOT BOOKED");
        tickets.put(nextId++, secondTicketForFlight3);
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
    
    // Available cheapest one
    public Ticket getTicketByFlight(Flight flight) {
        Assert.notNull(flight, "The flight must not be null");
        return tickets.values()
                .stream()
                .filter(ticket -> flight.getFromCity().equals(ticket.getFlight().getFromCity())
                       && flight.getToCity().equals(ticket.getFlight().getToCity())
                       && flight.getDate().equals(ticket.getFlight().getDate())
                       && flight.getTime().equals(ticket.getFlight().getTime()))
                .min(Comparator.comparingInt(Ticket::getPrice))
                .orElse(null);
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
