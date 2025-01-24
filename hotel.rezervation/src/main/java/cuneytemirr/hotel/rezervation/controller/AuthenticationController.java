package cuneytemirr.hotel.rezervation.controller;

import cuneytemirr.hotel.rezervation.model.entity.User;

public class AuthenticationController {
	
	private UserController userController = UserController.getUserControllerInstance();
	
	// Singleton yapısı
	private static AuthenticationController authenticationController;
	private AuthenticationController() {
		
	}
	public static AuthenticationController getAuthenticationControllerInstance() {
		if (authenticationController == null) {
			authenticationController = new AuthenticationController();
		}
		return authenticationController;
	}
	
	public User loginControl(String username, String password) {
		User user = userController.findUser(username);
		
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	

}
