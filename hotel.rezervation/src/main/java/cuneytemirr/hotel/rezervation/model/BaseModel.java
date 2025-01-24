package cuneytemirr.hotel.rezervation.model;

import cuneytemirr.hotel.rezervation.model.repository.ReservationRepository;
import cuneytemirr.hotel.rezervation.model.repository.RoomRepository;
import cuneytemirr.hotel.rezervation.model.repository.UserRepository;

public abstract class BaseModel {

	public abstract UserRepository getUserRepository();
	public abstract RoomRepository getRoomRepository();
	public abstract ReservationRepository getReservationRepository();
	
}
