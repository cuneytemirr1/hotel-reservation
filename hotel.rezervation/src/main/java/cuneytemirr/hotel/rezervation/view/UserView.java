package cuneytemirr.hotel.rezervation.view;

import cuneytemirr.hotel.rezervation.controller.UserController;
import cuneytemirr.hotel.rezervation.utils.ProcessUtils;

public class UserView {
	
	private ProcessUtils processUtils = ProcessUtils.getProcessUtilsInstance();
	private UserController userController = UserController.getUserControllerInstance();
	
	// Singleton
	private static UserView userView;
	private UserView() {
		
	}
	public static UserView getUserViewInstance( ) {
		if (userView == null) {
			userView = new UserView();
		}
		return userView;
	}
	
	public void showAdmin() {
		
		System.out.println("Welcome to User Operations!");
		
		USERVIEW:
		while(true) {
			
			System.out.print("1- Add User\n"
					+ "2- Get All Users\n"
					+ "3- Uptade User\n"
					+ "4- Delete User\n"
					+ "5- Find User\n"
					+ "0- Back to Main Operations\n"
					+ "Select Operation: ");
			String choice = processUtils.sc.nextLine();
			
			switch(choice) {
			case "1":
				addUser();
				break;
			case "2":
				getUsers();
				break;
			case "3":
				uptadeUser();
				break;
			case "4":
				deleteUser();
				break;
			case "5":
				findUser();
				break;
			case "0":
				break USERVIEW;
			default:
				System.out.println("Invalid Operation!");
			}
			
		}
		
	}
	private void findUser() {
		
		FINDUSER:
		do {
			System.out.print("Enter Username: ");
			String username = processUtils.sc.nextLine();
			
			if (userController.isUserExist(username)) {
				System.out.printf("Username: %s, Password: %s, Role: %s%n",
					userController.findUser(username).getUsername(),
					userController.findUser(username).getPassword(),
					userController.findUser(username).getRole()
					);
				break FINDUSER;
			}else {
				System.out.println("Invalid Username");
			}
		}while(true);
		
		
		
	}
	private void deleteUser() {
		
		String username;
		
		VALIDUSERNAME:
		do {
			System.out.print("Please Enter Username for Deletion: ");
			username = processUtils.sc.nextLine();
			if (userController.isUserExist(username)) {
				break VALIDUSERNAME;
			}else {
				System.out.println("Invalid Username");
			}
		}while(true);
		
		userController.deleteUser(username);
		System.out.printf("%s Deleted Successfully!%n", username);
		
	}
	
	private void uptadeUser() {
		
		String username;
		System.out.print("Please Enter Username for Uptade: ");
		
		VALIDUSER:
		do {
			username = processUtils.sc.nextLine();
			if (username.toUpperCase().equals("X")) {
				return;
			}
			if (userController.isUserExist(username)) {
				break VALIDUSER;
			}else {
				System.out.print("Invalid Username! Please Enter Valid Username ( X For Exit): ");
			}
		}while(true);
		
		
		System.out.print("1- Uptade Username\n"
				+ "2- Uptade Password\n"
				+ "3- Uptade Role\n"
				+ "0- Back"
				+ "Select Operation: ");
		String choice = processUtils.sc.nextLine();
		UPTADECHOICE:
		do {
			switch(choice) {
			case "1":
				System.out.print("Enter New Username: ");
				String newUsername = processUtils.sc.nextLine();
				userController.uptadeUsername(username, newUsername);
				System.out.printf("%s username is uptaded to %s%n", username, newUsername);
				break UPTADECHOICE;
			case "2":
				System.out.print("Enter New Password: ");
				String newPassword = processUtils.sc.nextLine();
				userController.uptadePassword(username, newPassword);
				System.out.printf("Password is uptaded to %s%n", newPassword);
				break UPTADECHOICE;
			case "3":
				System.out.print("1- ADMIN\n"
						+ "2- RECEPTIONIST\n"
						+ "Enter New Role: ");
				String newRole = processUtils.sc.nextLine();
				
				ROLECHOİCE:
				do {
					if (newRole.equals("1")) {
						newRole = "Admin";
						break ROLECHOİCE;
					}else if(newRole.equals("2")) {
						newRole = "Receptionist";
						break ROLECHOİCE;
					}else {
						System.out.print("Invalid Choice! Please Enter 1 or 2: ");
					}
				}while(true);
				
				userController.uptadeRole(username, newRole);
				System.out.printf("Role is uptaded to %s%n", newRole);
				break UPTADECHOICE;
			case "0":
				break UPTADECHOICE;
			default:
				System.out.println("Invalid Operation!");
			}
		}while(true);
		
		
	}
	private void getUsers() {
		System.out.println("All Users:");
		userController.getAllUsers();
	}
	
	private void addUser() {
		
		System.out.print("Please Enter Username: ");
		String username = processUtils.sc.nextLine();
		
		System.out.print("Please Enter Password: ");
		String password = processUtils.sc.nextLine();
		
		System.out.print("1- ADMIN\n"
				+ "2- RECEPTIONIST\n"
				+ "Select Role: ");
		String role = processUtils.sc.nextLine();
		ROLECHOICE:
		do {
			if (role.equals("1")) {
				role = "Admin";
				break ROLECHOICE;
			}else if(role.equals("2")) {
				role = "Receptionist";
				break ROLECHOICE;
			}else {
				System.out.print("Invalid Number!\n"
						+ "Please select 1 or 2: ");
				role = processUtils.sc.nextLine();
			}
		}while(true);
		
		userController.createUser(username, password, role);
		
		System.out.println("User is added!");
		
	}

}
