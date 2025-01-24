package cuneytemirr.hotel.rezervation.model.repository;

import java.util.ArrayList;
import java.util.List;

import cuneytemirr.hotel.rezervation.model.entity.Room;

public class RoomRepository {

	private List<Room> roomList = new ArrayList<Room>();
	
	public RoomRepository() {
		
	}

	public RoomRepository(List<Room> roomList) {
		this.roomList = roomList;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}
	
	public int getHighestRoomId() {
		
		int maxId = 2000;
		
		if (!roomList.isEmpty()) {
			
			for (Room room : roomList) {
				maxId = Math.max(maxId, room.getRoomId());
			}
		}
		
		return maxId;
		
	}
	
}
