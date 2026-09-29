package com.example.ticket;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	private final TicketService ticketService;
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	@PostMapping
	public Ticket createTicket(@RequestBody Ticket ticket) {
		return ticketService.createTicket(ticket);
	}
	@GetMapping
	public List<Ticket>
	getAllTickets() {
		return ticketService.getAllTickets();
	}

	@PutMapping("/{id}/status")
	publix Ticket updateStatus(@PathVariable int id,@RequestParam String status) {
		return tickeService.updateStatus(id, status);
	}
}

