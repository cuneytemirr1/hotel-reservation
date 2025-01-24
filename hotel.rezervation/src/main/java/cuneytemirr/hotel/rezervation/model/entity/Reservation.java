package cuneytemirr.hotel.rezervation.model.entity;

import java.time.LocalDate;

public class Reservation {

	private int reservationId;
	private String customerName;
	private Room room;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private double totalCost;

	public Reservation() {
	}

	public Reservation(int reservationId, String customerName, Room room, LocalDate checkIn, LocalDate checkOut,
			double totalCost) {
		super();
		this.reservationId = reservationId;
		this.customerName = customerName;
		this.room = room;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalCost = totalCost;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", customerName=" + customerName + ", room=" + room
				+ ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", totalCost=" + totalCost + "]";
	}

}
