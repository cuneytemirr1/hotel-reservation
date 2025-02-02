package cuneytemirr.hotel.rezervation.model.repository;

import java.util.ArrayList;
import java.util.List;

import cuneytemirr.hotel.rezervation.model.entity.Reservation;

public class ReservationRepository {
	
	private List<Reservation> reservationList = new ArrayList<Reservation>();
	
	public ReservationRepository() {
		
	}

	public ReservationRepository(List<Reservation> reservationList) {
		super();
		this.reservationList = reservationList;
	}

	public List<Reservation> getReservationList() {
		return reservationList;
	}

	public void setReservationList(List<Reservation> reservationList) {
		this.reservationList = reservationList;
	}
	
	public int getHighestReservationId() {
		
		int maxId = 3000;
		
		if (!reservationList.isEmpty()) {
			
			for (Reservation reservation : reservationList) {
				maxId = Math.max(maxId, reservation.getReservationId());
			}
		}
		
		return maxId;
		
	}

}
