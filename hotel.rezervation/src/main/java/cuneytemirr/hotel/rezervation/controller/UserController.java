package cuneytemirr.hotel.rezervation.controller;

import cuneytemirr.hotel.rezervation.model.AnkaraModel;
import cuneytemirr.hotel.rezervation.model.entity.User;

public class UserController {
	
	private AnkaraModel ankaraModel = AnkaraModel.getAnkaraModelInstance();
	
	//Singleton
	private static UserController userController;
	private UserController() {}
	public static UserController getUserControllerInstance() {
		if (userController == null) {
			userController = new UserController();
		}
		
		return userController;
	}

	public void createUser(String username, String password, String role) {

		User user = new User(username,
				password,
				role,
				ankaraModel.getUserRepository().getHighestUserId() + 1);
		
		ankaraModel.getUserRepository().getUserList().add(user);
		
	}
	public void deleteUser(String username) {
		
		ankaraModel.getUserRepository().getUserList().removeIf(user -> user.getUsername().equals(username));
		
	}
	public boolean isUserExist(String username) {
		
		return ankaraModel.getUserRepository().getUserList().stream().anyMatch(user -> user.getUsername().equals(username));
		
	}
	public void uptadeUsername(String username, String newUsername) {
		User uptadeUser = findUser(username);
		
		uptadeUser.setUsername(newUsername);
		
	}
	public void uptadePassword(String username, String newPassword) {
		User uptadeUser = findUser(username);
		uptadeUser.setPassword(newPassword);
	}
	public void uptadeRole(String username, String newRole) {
		User uptadeUser = findUser(username);
		uptadeUser.setRole(newRole);
	}
	public User findUser(String username) {
		User uptadeUser = ankaraModel.getUserRepository().getUserList()
				.stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst()
				.orElse(null);
		return uptadeUser;
	}
	public void getAllUsers() {
		for (User user : ankaraModel.getUserRepository().getUserList()) {
			System.out.printf("Username: %s, Password: %s, Role: %s%n", user.getUsername(), user.getPassword(), user.getRole());
		}
	}
	
}
