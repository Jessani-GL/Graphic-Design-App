package data;

public class TextPropertiesHolder {
	
	// This class saves the data font size and font family for the text element/s. 
	// This is because it allows multiple changes when applying italics and bold onto text element/s.
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
