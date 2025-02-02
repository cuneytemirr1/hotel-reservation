package cuneytemirr.hotel.rezervation.controller;

import cuneytemirr.hotel.rezervation.model.AnkaraModel;
import cuneytemirr.hotel.rezervation.model.entity.Room;

public class RoomController {

	private AnkaraModel ankaraModel = AnkaraModel.getAnkaraModelInstance();

	// Singleton
	private static RoomController roomController;

	private RoomController() {
	}

	public static RoomController getRoomControllerInstance() {
		if (roomController == null) {
			roomController = new RoomController();
		}
		return roomController;
	}

	public void add(String roomNumber, String category, int capacity, double price) {
		ankaraModel.getRoomRepository().getRoomList().add(new Room(
				ankaraModel.getRoomRepository().getHighestRoomId() + 1, roomNumber, category, capacity, price));
	}

	public void getAll() {
		for (Room room : ankaraModel.getRoomRepository().getRoomList()) {
			String reserved;
			if (room.isReserved()) {
				reserved = "Yes";
			} else {
				reserved = "No";
			}
			System.out.printf(
					"Room Number: %s, " + "Category: %s, " + "Capacity: %d, " + "Price: %.2f, " + "Reserved: %s" + "%n",
					room.getRoomNumber(), room.getCategory(), room.getCapacity(), room.getPrice(), reserved);
		}
	}

	public Room findRoomByRoomNumber(String roomNumber) {
		return ankaraModel.getRoomRepository().getRoomList().stream()
				.filter(room -> room.getRoomNumber().equals(roomNumber)).findFirst().orElse(null);
	}

	public void uptadeRoomNumber(Room room, String newRoomNumber) {

		room.setRoomNumber(newRoomNumber);

	}

	public void uptadeCategory(Room room, String newCategory) {
		room.setCategory(newCategory);
	}

	public void uptadeCapacity(Room room, int newCapacity) {
		room.setCapacity(newCapacity);
	}

	public void uptadePrice(Room room, double newPrice) {
		room.setPrice(newPrice);
	}

	public void uptadeReserve(Room room, String reserveChoice) {
		if (reserveChoice.equals("1")) {
			room.setReserved(true);
		} else {
			room.setReserved(false);
		}
	}

	public void deleteRoom(Room room) {
		ankaraModel.getRoomRepository().getRoomList().remove(room);
	}

	public void getRoom(Room room) {
		String reserved;
		if (room.isReserved()) {
			reserved = "Yes";
		} else {
			reserved = "No";
		}
		System.out.printf("Room Number: %s, " + "Category: %s, " + "Capacity: %d, " + "Price: %.2f, " + "Reserve: %s%n",
				room.getRoomNumber(), room.getCategory(), room.getCapacity(), room.getPrice(), reserved);
	}

	public void setReserve(Room room, boolean b) {
		room.setReserved(b);
	}

}
