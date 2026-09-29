package com.example.ticket;

public class Ticket {
	private int id;
	private String title;
	private String description;
	private String priority;
	private String status;

	public Ticket(int id, String title, String description, String priority, String status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription(){
		return description;
	}
	public String getPriority() {
		return priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String satus) {
		this.status = status;
	}
}

