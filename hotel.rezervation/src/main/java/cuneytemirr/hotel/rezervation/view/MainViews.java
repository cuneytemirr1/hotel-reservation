package cuneytemirr.hotel.rezervation.view;

import cuneytemirr.hotel.rezervation.model.entity.User;
import cuneytemirr.hotel.rezervation.utils.ProcessUtils;

public class MainViews {
	
	private ProcessUtils processUtils = ProcessUtils.getProcessUtilsInstance();
	private UserView userView = UserView.getUserViewInstance();
	private RoomView roomView = RoomView.getRoomViewInstance();
	private ReservationView reservationView = ReservationView.getReservationViewInstance();
	
	//Singleton yapısı
	private static MainViews mainViews;
	private MainViews() {
	}
	public static MainViews getMainViewsInstance() {
		if (mainViews == null) {
			mainViews = new MainViews();
		}
		return mainViews;
	}
	
	public void getAdminView(User user) {
		System.out.printf("Welcome %s%n", user.getUsername());
		
		do {
			
			System.out.print("1- Manage Users\n"
					+ "2- Manage Rooms\n"
					+ "3- Manage Reservations\n"
					+ "0- Logout\n"
					+ "Select Operation : ");
			String choice = processUtils.sc.nextLine();
			
			switch (choice) {
			case "1":
				userView.showAdmin();
				break;
			case "2":
				roomView.showAdmin();
				break;
			case "3":
				reservationView.showAdmin();
				break;
			case "0":
				System.exit(0);;
			default:
				System.out.println("Invalid Operation!");
			}
			
		}while(true);
		
	}
	public void getReceptionistView(User user) {
		System.out.printf("Welcome %s\n", user.getUsername());
		
		do {
			
			System.out.print("1- Manage Rooms\n"
					+ "2- Manage Reservations\n"
					+ "0- Logout\n"
					+ "Select Operation: ");
			String choice = processUtils.sc.nextLine();
			
			switch(choice) {
			case "1":
				roomView.showReceptionist();
				break;
			case "2":
				reservationView.showReceptionist();
				break;
			case "0":
				System.exit(0);;
			default:
				System.out.println("Invalid Operation!");
			}
			
		}while(true);

		
	}

}
