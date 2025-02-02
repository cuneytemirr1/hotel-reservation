package cuneytemirr.hotel.rezervation.view;

import java.time.LocalDate;
import java.util.InputMismatchException;

import cuneytemirr.hotel.rezervation.controller.ReservationController;
import cuneytemirr.hotel.rezervation.controller.RoomController;
import cuneytemirr.hotel.rezervation.model.entity.Reservation;
import cuneytemirr.hotel.rezervation.model.entity.Room;
import cuneytemirr.hotel.rezervation.utils.ProcessUtils;

public class ReservationView {

	private ProcessUtils processUtils = ProcessUtils.getProcessUtilsInstance();
	private RoomController roomController = RoomController.getRoomControllerInstance();
	private ReservationController reservationController = ReservationController.getReservationControllerInstance();

	// Singleton
	private static ReservationView reservationView;

	private ReservationView() {
	}

	public static ReservationView getReservationViewInstance() {
		if (reservationView == null) {
			reservationView = new ReservationView();
		}
		return reservationView;
	}

	public void showAdmin() {
		System.out.println("Welcome to Reservation Operations!\n--------------------------");

		RESERVATIONOPERATION: while (true) {
			System.out.print("1- Add Reservation\n" + "2- Get All Reservations\n" + "3- Uptade Reservation\n"
					+ "4- Cancel Reservation\n"
					+ "5- Find reservation\n" + "0- Back to Main Operations\n" + "Select Operation: ");
			String choice = processUtils.sc.nextLine();
			switch (choice) {
			case "1":
				addReservation();
				break;
			case "2":
				getAllReservations();
				break;
			case "3":
				uptadeReservation();
				break;
			case "4":
				cancelReservation();
				break;
			case "5":
				showReservation();
				break;
			case "0":
				break RESERVATIONOPERATION;
			default:
				System.out.println("Invalid Choice. Please Enter Valid Integer!");
				break;
			}

		}
	}


	public void showReceptionist() {
		System.out.println("Welcome to Reservation Operations!\n--------------------------");

		RESERVATIONOPERATION: while (true) {
			System.out.print("1- Add Reservation\n" + "2- Get All Reservations\n" + "3- Uptade Reservation\n"
					+ "4- Cancel Reservation\n"
					+ "5- Find reservation\n" + "0- Back to Main Operations\n" + "Select Operation: ");
			String choice = processUtils.sc.nextLine();
			switch (choice) {
			case "1":
				addReservation();
				break;
			case "2":
				getAllReservations();
				break;
			case "3":
				uptadeReservation();
				break;
			case "4":
				cancelReservation();
				break;
			case "5":
				showReservation();
				break;
			case "0":
				break RESERVATIONOPERATION;
			default:
				System.out.println("Invalid Choice. Please Enter Valid Integer!");
				break;
			}

		}
	}
	

	private void addReservation() {
		System.out.print("Please Enter Customer Name: ");
		String customerName = processUtils.sc.nextLine();

		Room room;
		VALIDROOM: while (true) {
			System.out.print("Which Room Number Will Be Reserved: ");
			String roomNumber = processUtils.sc.nextLine();
			room = roomController.findRoomByRoomNumber(roomNumber);

			if (room == null) {
				System.out.println("Room Not Found. Try Again!");
			} else if (room.isReserved()) {
				System.out.println("Room has Already Been Reserved. Please Enter Another Room!");
			} else {
				roomController.setReserve(room, true);
				break VALIDROOM;
			}
		}

		LocalDate checkIn;
		VALIDCHECKIN: while (true) {
			System.out.print("Check-in Date (day-month-year): ");
			String date = processUtils.sc.nextLine();
			if (reservationController.isValidDate(date)) {
				checkIn = reservationController.dateFormatter(date);
				break VALIDCHECKIN;
			} else {
				System.out.println("Invalid Date. Try Again!");
			}
		}

		LocalDate checkOut = null;
		VALIDCHECKOUT: while (true) {
			System.out.print("Check-out Date (day-month-year | for skip press X) : ");
			String date = processUtils.sc.nextLine();
			if (date.toUpperCase().equals("X")) {
				break VALIDCHECKOUT;
			} else if (reservationController.isValidDate(date)) {
				checkOut = reservationController.dateFormatter(date);
				break VALIDCHECKOUT;
			} else {
				System.out.println("Invalid Date. Try Again!");
			}
		}

		double totalCost;
		VALIDCOST: while (true) {
			System.out.print("Total Cost: ");
			try {
				totalCost = processUtils.sc.nextDouble();
				processUtils.sc.nextLine();
				if (totalCost <= 0) {
					System.out.println("Cost Cannot Be 0 or Negative Integer!");
				} else {
					break VALIDCOST;
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid Cost. Please Enter Correct Cost!");
				processUtils.sc.nextLine();
			}
		}

		reservationController.addReservation(customerName, room, checkIn, checkOut, totalCost);

		System.out.printf(
				"Reservation Created!\n"
						+ "Customer Name: %s, Room Number: %s, Check-In: %s, Check-Out: %s, Cost: %.2f%n",
				customerName, room.getRoomNumber(), checkIn.toString(), checkOut, totalCost);
	}

	private void getAllReservations() {
		System.out.println("All Reservations\n-----------------");
		reservationController.getAllReservations();
	}

	private void uptadeReservation() {
		VALIDCUSTOMER: while (true) {
			System.out.print("Which Customer's Reservation Do You Want To Uptade: ");
			String customerName = processUtils.sc.nextLine();
			Reservation reservation = reservationController.getReservationByCustomerName(customerName);
			if (reservation != null) {
				while (true) {
					System.out.print("1- Uptade Customer Name\n" + "2- Uptade Room Number\n" + "3- Uptade Check-In\n"
							+ "4- Uptade Check-Out\n" + "5- Uptade Cost\n" + "0- Back\n" + "Choice: ");
					String choice = processUtils.sc.nextLine();

					switch (choice) {
					case "1":
						System.out.print("Enter New Customer Name: ");
						String newCustomerName = processUtils.sc.nextLine();
						reservationController.uptadeCustomerName(reservation, newCustomerName);
						System.out.printf("Customer Name Uptaded To %s%n", newCustomerName);
						break;
					case "2":
						VALIDROOMNUMBER: do {
							System.out.print("Enter New Room Number: ");
							String newRoomNumber = processUtils.sc.nextLine();
							Room room = roomController.findRoomByRoomNumber(newRoomNumber);
							if (room == null) {
								System.out.println("Room Number Does Not Exist. Please Enter Another Room Number!");
							} else if (room.isReserved()) {
								System.out.println("Room has Already Been Reserved. Please Enter Another Room Number!");
							} else {
								reservationController.uptadeRoom(reservation, room);
								roomController.setReserve(room, true);
								roomController.setReserve(reservation.getRoom(), false);
								System.out.printf("Room Number Uptaded To %s%n", newRoomNumber);
								break VALIDROOMNUMBER;
							}
						} while (true);
						break;
					case "3":
						VALIDDATE: while (true) {
							System.out.print("Enter New Check-In (day-month-year): ");
							String newCheckInDate = processUtils.sc.nextLine().trim();

							if (newCheckInDate.isEmpty()) {
								System.out.println("Date cannot be empty. Please enter a valid date.");
								continue;
							}

							LocalDate newCheckIn;
							if (reservationController.isValidDate(newCheckInDate)) {
								newCheckIn = reservationController.dateFormatter(newCheckInDate);
								reservationController.setCheckIn(reservation, newCheckIn);
								System.out.printf("Check-In Updated To %s\n", newCheckIn);
								break VALIDDATE;
							} else {
								System.out.println("Invalid Date. Please Enter DAY-MONTH-YEAR (e.g., 12-04-2025)!");
							}
						}
						break;
					case "4":
						VALIDDATE: while (true) {
							System.out.print("Enter New Check-Out (day-month-year): ");
							String newCheckOutDate = processUtils.sc.nextLine().trim();

							if (newCheckOutDate.isEmpty()) {
								System.out.println("Date cannot be empty. Please enter a valid date.");
								continue;
							}

							LocalDate newCheckOut;
							if (reservationController.isValidDate(newCheckOutDate)) {
								newCheckOut = reservationController.dateFormatter(newCheckOutDate);
								reservationController.setCheckOut(reservation, newCheckOut);
								System.out.printf("Check-Out Updated To %s\n", newCheckOut);
								break VALIDDATE;
							} else {
								System.out.println("Invalid Date. Please Enter DAY-MONTH-YEAR (e.g., 12-04-2025)!");
							}
						}
						break;

					case "5":
						VALIDCOST: do {
							System.out.print("Enter New Cost: ");
							try {
								double newCost = processUtils.sc.nextDouble();
								processUtils.sc.nextLine();
								if (newCost <= 0) {
									System.out.println("Cost Cannot Be 0 or Negative Number!");
								} else {
									reservationController.setTotalCost(reservation, newCost);
									System.out.printf("Total Cost Uptaded To %s\n", newCost);
									break VALIDCOST;
								}
							} catch (InputMismatchException e) {
								System.out.println("Please Enter Number!");
								processUtils.sc.nextLine();
							}
						} while (true);
						break;
					case "0":
						break VALIDCUSTOMER;
					default:
						System.out.println("Invalid Operation. Please Enter Valid Integer!");
					}
				}
			} else {
				System.out.println("Customer Not Found. Please Enter Valid Customer Name!");
			}
		}
	}


	private void cancelReservation() {
		String customerName;
		FINDRESERVATION:
		while (true) {
			System.out.print("Which Customer's Reservation Would You Like to Cancel (Press X for Exit): ");
			customerName = processUtils.sc.nextLine();
			Reservation reservation = reservationController.getReservationByCustomerName(customerName);
			
			if (customerName.toUpperCase().equals("X")) {
				break FINDRESERVATION;
			} else if (reservation != null) {
				reservationController.cancelReservation(customerName);
				Room room = reservationController.getRoom(reservation);
				roomController.setReserve(room, false);
				System.out.printf("%s Customer's Reservation Has Been Canceled\n", customerName);
				break FINDRESERVATION;
			}else {
				System.out.println("Customer Not Found!");
			}
		}
	}
	
	private void showReservation() {
		FINDRESERVATION:
		while(true) {
			System.out.print("Which Customer's Reservation Would You Like to Find: ");
			String customerName = processUtils.sc.nextLine();
			Reservation reservation = reservationController.getReservationByCustomerName(customerName);
			if (customerName.toUpperCase().equals("X")){
				break FINDRESERVATION;
			} else if (reservation != null) {
				reservationController.showReservation(reservation);
				break FINDRESERVATION;
			}else {
				System.out.println("Customer Not Found (X for Exit)");
			}
		}
	}

}
