package data;

public class TextPropertiesHolder {
	
	private int fSize = 11;
	private String fFamily = "Arial";

	private final static TextPropertiesHolder INSTANCE = new TextPropertiesHolder();
	
	public static TextPropertiesHolder getInstance() {
		return INSTANCE;
	}

	public int getfSize() {
		return fSize;
	}

	public void setfSize(int fSize) {
		this.fSize = fSize;
	}

	public String getfFamily() {
		return fFamily;
	}

	public void setfFamily(String fFamily) {
		this.fFamily = fFamily;
	}
	
	
}
