package cuneytemirr.hotel.rezervation.view;

import java.util.InputMismatchException;

import cuneytemirr.hotel.rezervation.controller.RoomController;
import cuneytemirr.hotel.rezervation.model.entity.Room;
import cuneytemirr.hotel.rezervation.utils.ProcessUtils;

public class RoomView {

	private ProcessUtils processUtils = ProcessUtils.getProcessUtilsInstance();
	private RoomController roomController = RoomController.getRoomControllerInstance();

	// Singleton
	private static RoomView roomView;

	private RoomView() {
	}

	public static RoomView getRoomViewInstance() {
		if (roomView == null) {
			roomView = new RoomView();
		}
		return roomView;
	}

	public void showAdmin() {
		System.out.println("Welcome to Room Operations!");

		ROOMOPERATION: do {
			System.out.print("1- Add Room\n" + "2- Get All Rooms\n" + "3- Uptade Room\n" + "4- Delete Room\n"
					+ "5- Find Room\n" + "0- Back to Main Operations\n" + "Select Operation: ");
			String choice = processUtils.sc.nextLine();

			switch (choice) {
			case "1":
				add();
				break;
			case "2":
				getAll();
				break;
			case "3":
				uptadeByRoomNumber();
				break;
			case "4":
				deleteByRoomNumber();
				break;
			case "5":
				findRoom();
				break;
			case "0":
				break ROOMOPERATION;
			default:
				System.out.println("Invalid Operation!");
			}

		} while (true);

	}

	public void showReceptionist() {
		System.out.println("Welcome to Room Operations!");
		ROOMOPERATION: do {
			System.out.print("1- Find Room\n" + "0- Back to Main Operations\n" + "Select Operation: ");
			String choice = processUtils.sc.nextLine();

			switch (choice) {
			case "1":
				findRoom();
			case "0":
				break ROOMOPERATION;
			default:
				System.out.println("Invalid Operation!");
			}

		} while (true);
	}

	private void add() {
		System.out.print("Enter Room Number: ");
		String roomNumber = processUtils.sc.nextLine();
		System.out.print("Enter Room Category: ");
		String category = processUtils.sc.nextLine();
		System.out.print("Enter Room Capacity: ");
		int capacity;
		do {
			try {
				capacity = processUtils.sc.nextInt();
				processUtils.sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.print("Invalid Capacity! Please Enter Correct Capacity: ");
				processUtils.sc.nextLine();
			}

		} while (true);

		System.out.print("Enter Room Price: ");
		double price;
		do {
			try {
				price = processUtils.sc.nextDouble();
				processUtils.sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.print("Invalid Price! Please Enter Correct Price: ");
				processUtils.sc.nextLine();
			}

		} while (true);

		roomController.add(roomNumber, category, capacity, price);

		System.out.printf("%s Room is Successfully Added!%n", roomNumber);
	}

	private void getAll() {

		System.out.println("All Rooms\n" + "----------");
		roomController.getAll();

	}

	private void uptadeByRoomNumber() {
		System.out.print("Enter Room Number: ");
		String roomNumber = processUtils.sc.nextLine();
		Room room = roomController.findRoomByRoomNumber(roomNumber);
		VALIDROOM: do {
			if (room != null) {
				break VALIDROOM;
			}
			System.out.print("Room Not Found! Please Enter Exist Room ( X For Back): ");
			roomNumber = processUtils.sc.nextLine();
			if (roomNumber.toUpperCase().equals("X")) {
				return;
			}
			room = roomController.findRoomByRoomNumber(roomNumber);
		} while (true);

		System.out.print("1- Uptade Room Number\n" + "2- Uptade Room Category\n" + "3- Uptade Room Capacity\n"
				+ "4- Uptade Room Price\n" + "5- Uptade Reserve\n" + "0- Back\n" + "Select Operation: ");
		String operation = processUtils.sc.nextLine();

		OPERATION: do {
			switch (operation) {
			case "1":
				System.out.print("Enter New Room Number: ");
				String newRoomNumber = processUtils.sc.nextLine();
				roomController.uptadeRoomNumber(room, newRoomNumber);
				System.out.printf("Room Number Uptaded To %s%n", newRoomNumber);
				break OPERATION;
			case "2":
				System.out.print("Enter New Room Category: ");
				String newCategory = processUtils.sc.nextLine();
				roomController.uptadeCategory(room, newCategory);
				System.out.printf("Room category Uptaded To %s%n", newCategory);
				break OPERATION;
			case "3":
				int newCapacity;
				while (true) {
					System.out.printf("Enter New Room Capacity: ");
					try {
						newCapacity = processUtils.sc.nextInt();
						processUtils.sc.nextLine();
						if (newCapacity <= 0) {
							System.out.println("Capacity must be a positive number. Please try again.");
							continue;
						}
						break;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter a valid integer!");
						processUtils.sc.nextLine();
					}
				}
				roomController.uptadeCapacity(room, newCapacity);
				System.out.printf("Room Capacity Updated To %d%n", newCapacity);
				break OPERATION;
			case "4":
				double newPrice;
				while (true) {
					System.out.print("Enter New Room Price: ");
					try {
						newPrice = processUtils.sc.nextDouble();
						processUtils.sc.nextLine();
						if (newPrice <= 0) {
							System.out.println("Price must be a positive number. Please try again.");
							continue;
						}
						break;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter a valid integer!");
						processUtils.sc.nextLine();
					}
				}
				roomController.uptadePrice(room, newPrice);
				System.out.printf("Room Price Uptaded To %.2f%n", newPrice);
				break OPERATION;
			case "5":
				System.out.print("1- Reserved\n" + "2- Not Reserved\n" + "Reserve Choice: ");
				String reserveChoice = processUtils.sc.nextLine();
				RESERVCECHOICE: while (true) {
					if (reserveChoice.equals("1") || reserveChoice.equals("2")) {
						roomController.uptadeReserve(room, reserveChoice);
						break RESERVCECHOICE;
					} else {
						System.out.print("Invalid Choice! Please Enter Valid Number: ");
						reserveChoice = processUtils.sc.nextLine();
					}
				}
				break OPERATION;
			case "0":
				break OPERATION;
			default:
				System.out.print("Invalid Operation! Please Select Valid Operation: ");
				operation = processUtils.sc.nextLine();
			}
		} while (true);

	}

	private void deleteByRoomNumber() {
		System.out.print("Which Room Do You Want Delete: ");
		String deleteRoom = processUtils.sc.nextLine();
		Room room = roomController.findRoomByRoomNumber(deleteRoom);
		VALIDROOM: do {
			if (room != null) {
				break VALIDROOM;
			}
			System.out.print("Room Not Found! Please Enter Exist Room ( X For Back): ");
			deleteRoom = processUtils.sc.nextLine();
			if (deleteRoom.toUpperCase().equals("X")) {
				return;
			}
			room = roomController.findRoomByRoomNumber(deleteRoom);
		} while (true);
		roomController.deleteRoom(room);
		System.out.printf("%s Room Successfully Removed!%n", deleteRoom);
	}

	private void findRoom() {
		System.out.print("Enter Room Number: ");
		String roomNumber = processUtils.sc.nextLine();
		Room room = roomController.findRoomByRoomNumber(roomNumber);
		VALIDROOM: do {
			if (room != null) {
				break VALIDROOM;
			}
			System.out.print("Room Not Found! Please Enter Exist Room ( X For Back): ");
			roomNumber = processUtils.sc.nextLine();
			if (roomNumber.toUpperCase().equals("X")) {
				return;
			}
			room = roomController.findRoomByRoomNumber(roomNumber);
		} while (true);
		roomController.getRoom(room);
	}

}
