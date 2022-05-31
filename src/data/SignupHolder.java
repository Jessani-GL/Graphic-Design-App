package data;

public class SignupHolder {
	
	private byte [] profileImg;
	private byte [] defaultprofileImg;
	
	private final static SignupHolder INSTANCE = new SignupHolder();
	
	public static SignupHolder getInstance() {
		return INSTANCE;
	}

	public byte[] getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(byte[] profileImg) {
		this.profileImg = profileImg;
	}

	public byte[] getDefaultprofileImg() {
		return defaultprofileImg;
	}

	public void setDefaultprofileImg(byte[] defaultprofileImg) {
		this.defaultprofileImg = defaultprofileImg;
	}

	
}
