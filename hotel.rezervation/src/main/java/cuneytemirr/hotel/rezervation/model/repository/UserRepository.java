package cuneytemirr.hotel.rezervation.model.repository;

import java.util.ArrayList;
import java.util.List;

import cuneytemirr.hotel.rezervation.model.entity.User;

public class UserRepository {
	
	private List<User> userList = new ArrayList<User>();
	
	public UserRepository() {
		userList.add(new User("admin", "admin", "Admin", 1));
		userList.add(new User("recep", "recep", "Receptionist", 2));
	}

	public UserRepository(List<User> userList) {
		this.userList = userList;
		userList.add(new User("admin", "admin", "Admin", 1));
		userList.add(new User("recep", "recep", "Receptionist", 2));
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public int getHighestUserId() {
		
		int maxId = 1000;
		
		if (!userList.isEmpty()) {
			
			for (User user : userList) {
				maxId = Math.max(maxId, user.getUserId());
			}
		}
		
		return maxId;
		
	}

}
