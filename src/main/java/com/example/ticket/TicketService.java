package com.example.ticket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final List<Ticket> tickets = new ArrayList<>();

    public Ticket createTicket(Ticket ticket) {
        tickets.add(ticket);
        return ticket;
    }

    public List<Ticket> getAllTickets() {
        return tickets;
    }

    public Ticket getTicketById(int id) {

        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }

        return null;
    }

    public Ticket updateStatus(int id, String status) {

        Ticket ticket = getTicketById(id);

        if (ticket != null) {
            ticket.setStatus(status);
        }

        return ticket;
    }
}
