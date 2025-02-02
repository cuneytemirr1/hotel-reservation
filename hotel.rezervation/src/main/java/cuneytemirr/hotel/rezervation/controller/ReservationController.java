package cuneytemirr.hotel.rezervation.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cuneytemirr.hotel.rezervation.model.AnkaraModel;
import cuneytemirr.hotel.rezervation.model.entity.Reservation;
import cuneytemirr.hotel.rezervation.model.entity.Room;

public class ReservationController {

	private AnkaraModel ankaraModel = AnkaraModel.getAnkaraModelInstance();

	// Singleton
	private static ReservationController reservationController;

	private ReservationController() {
	}

	public static ReservationController getReservationControllerInstance() {
		if (reservationController == null) {
			reservationController = new ReservationController();
		}
		return reservationController;
	}

	public boolean isValidDate(String date) {
		try {
			LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public LocalDate dateFormatter(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(date, formatter);
	}

	public void getAllReservations() {
		for (Reservation reservation : ankaraModel.getReservationRepository().getReservationList()) {
			System.out.printf("Customer Name: %s, Room Number: %s, Check-In: %s, Check-Out: %s, Cost: %.2f%n",
					reservation.getCustomerName(), reservation.getRoom().getRoomNumber(), reservation.getCheckIn(),
					reservation.getCheckOut(), reservation.getTotalCost());
		}
	}

	public void uptadeCustomerName(Reservation reservation, String newCustomerName) {
		reservation.setCustomerName(newCustomerName);
	}

	public Reservation getReservationByCustomerName(String customerName) {
		return ankaraModel.getReservationRepository().getReservationList().stream()
				.filter(reservation -> reservation.getCustomerName().equals(customerName)).findFirst().orElse(null);
	}

	public void uptadeRoom(Reservation reservation, Room room) {
		reservation.setRoom(room);
	}

	public void addReservation(String customerName, Room room, LocalDate checkIn, LocalDate checkOut,
			double totalCost) {
		Reservation reservation = new Reservation(ankaraModel.getReservationRepository().getHighestReservationId() + 1,
				customerName, room, checkIn, checkOut, totalCost);
		ankaraModel.getReservationRepository().getReservationList().add(reservation);
	}

	public void setCheckIn(Reservation reservation, LocalDate newCheckIn) {
		reservation.setCheckIn(newCheckIn);
	}

	public void setTotalCost(Reservation reservation, double newCost) {
		reservation.setTotalCost(newCost);
	}

	public void setCheckOut(Reservation reservation, LocalDate newCheckOut) {
		reservation.setCheckOut(newCheckOut);
	}

	public void cancelReservation(String customerName) {
		ankaraModel.getReservationRepository().getReservationList()
				.removeIf(reservation -> reservation.getCustomerName().equals(customerName));
	}

	public void showReservation(Reservation reservation) {
		System.out.printf("Customer Name: %s, Room Number: %s, Check-In: %s, Check-Out: %s, Total Cost: %s\n",
				reservation.getCustomerName(), reservation.getRoom().getRoomNumber(), reservation.getCheckIn(),
				reservation.getCheckOut(), reservation.getTotalCost());
	}

	public Room getRoom(Reservation reservation) {
		return reservation.getRoom();
	}

}
