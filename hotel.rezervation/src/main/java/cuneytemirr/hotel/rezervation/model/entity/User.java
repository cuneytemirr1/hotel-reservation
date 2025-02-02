package cuneytemirr.hotel.rezervation.model.entity;


public class User {
	
	private int userId;
	private String username;
	private String password;
	private String role;
	
	
	
	public User() {
	}
	
	public User(String username, String password, String role, int userId) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.userId = userId;
		
	}

	public int getUserId() {
		return userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
	

}
