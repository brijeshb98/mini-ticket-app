package com.example.ticket;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketServiceTest {

    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        ticketService = new TicketService();
    }

    @Test
    void shouldCreateTicket() {

        Ticket ticket = new Ticket(
                101,
                "Jenkins Build Failed",
                "Pipeline failed during deployment",
                "HIGH",
                "OPEN"
        );

        Ticket result = ticketService.createTicket(ticket);

        assertNotNull(result);
        assertEquals(101, result.getId());
        assertEquals("Jenkins Build Failed", result.getTitle());
        assertEquals("HIGH", result.getPriority());
        assertEquals("OPEN", result.getStatus());
    }

    @Test
    void shouldFindTicketById() {

        Ticket ticket = new Ticket(
                102,
                "Server Down",
                "Application server is not responding",
                "CRITICAL",
                "OPEN"
        );

        ticketService.createTicket(ticket);

        Ticket result = ticketService.getTicketById(102);

        assertNotNull(result);
        assertEquals(102, result.getId());
    }

    @Test
    void shouldUpdateTicketStatus() {

        Ticket ticket = new Ticket(
                103,
                "Database Issue",
                "Database connection failed",
                "HIGH",
                "OPEN"
        );

        ticketService.createTicket(ticket);

        Ticket result = ticketService.updateStatus(103, "CLOSED");

        assertNotNull(result);
        assertEquals("CLOSED", result.getStatus());
    }

    @Test
    void shouldReturnAllTickets() {

        Ticket ticket1 = new Ticket(
                104,
                "Network Issue",
                "Network is down",
                "HIGH",
                "OPEN"
        );

        Ticket ticket2 = new Ticket(
                105,
                "Email Issue",
                "Unable to send email",
                "MEDIUM",
                "OPEN"
        );

        ticketService.createTicket(ticket1);
        ticketService.createTicket(ticket2);

        List<Ticket> result = ticketService.getAllTickets();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(104, result.get(0).getId());
        assertEquals(105, result.get(1).getId());
    }

    @Test
    void shouldReturnNullForUnknownTicket() {

        Ticket result = ticketService.getTicketById(999);

        assertNull(result);
    }
}
