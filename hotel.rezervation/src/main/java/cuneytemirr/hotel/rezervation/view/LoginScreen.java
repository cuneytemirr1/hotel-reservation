package cuneytemirr.hotel.rezervation.view;

import cuneytemirr.hotel.rezervation.controller.AuthenticationController;
import cuneytemirr.hotel.rezervation.model.entity.User;
import cuneytemirr.hotel.rezervation.utils.ProcessUtils;

public class LoginScreen {
	
	private ProcessUtils processUtils = ProcessUtils.getProcessUtilsInstance();
	private AuthenticationController authenticationController = AuthenticationController.getAuthenticationControllerInstance();
	private MainViews mainViews = MainViews.getMainViewsInstance();
	
	// Singleton
	private static LoginScreen loginScreen;
	private LoginScreen() {}
	public static LoginScreen getLoginScreenInstance() {
		
		if (loginScreen == null) {
			loginScreen = new LoginScreen();
		}
		
		return loginScreen;
	}
	
	public void loginView() {
		System.out.println("Welcome to our Hotel Application!");
		
		do {
			System.out.print("Please enter your username: ");
			String username = processUtils.sc.nextLine();
			System.out.print("Please enter your password: ");
			String password = processUtils.sc.nextLine();
			User user = authenticationController.loginControl(username, password);
			
			if (user != null) {
				
				if (user.getRole().equals("Admin")) {
					
					mainViews.getAdminView(user);
					
				}else if (user.getRole().equals("Receptionist")) {
					
					mainViews.getReceptionistView(user);
					
				}else {
					
					System.out.println("Invalid Role");
					
				}
				
			}else {
				
				System.out.print("Username or Password is not correct!");
				do {
					System.out.println("\n (Do you want to exit (Y/N)) : ");
					String choice = processUtils.sc.nextLine().toUpperCase();
					if (choice.equals("Y")) {
						System.exit(0);
					}else if (choice.equals("N")) {
						break;
					}else {
						System.out.println("Invalid symbol!");
					}
				}while (true);
				
			}
		}while(true);
	}

}
