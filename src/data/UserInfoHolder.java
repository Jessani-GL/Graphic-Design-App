package data;

public class UserInfoHolder {

	private String username;
	private String firstName;
	private String lastName;
	
	private final static UserInfoHolder INSTANCE = new UserInfoHolder();
	
	public static UserInfoHolder getInstance() {
		return INSTANCE;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
