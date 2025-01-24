package cuneytemirr.hotel.rezervation.model;

import cuneytemirr.hotel.rezervation.model.repository.ReservationRepository;
import cuneytemirr.hotel.rezervation.model.repository.RoomRepository;
import cuneytemirr.hotel.rezervation.model.repository.UserRepository;

public class AnkaraModel extends BaseModel{
	
	private UserRepository userRepository = new UserRepository();
	private RoomRepository roomRepository = new RoomRepository();
	private ReservationRepository reservationRepository = new ReservationRepository();
	
	// Singleton
	private static AnkaraModel ankaraModel;
	private AnkaraModel() {}
	public static AnkaraModel getAnkaraModelInstance() {
		if (ankaraModel == null) {
			ankaraModel = new AnkaraModel();
		}
		
		return ankaraModel;
	}

	@Override
	public UserRepository getUserRepository() {
		return userRepository;
	}

	@Override
	public RoomRepository getRoomRepository() {
		return roomRepository;
	}

	@Override
	public ReservationRepository getReservationRepository() {
		return reservationRepository;
	}

}
